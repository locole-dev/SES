package vn.com.telsoft.websheet.api.wdf.abs;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.sql.Timestamp;
import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Project: WDF
 * Author: LAMLT
 * Created on: 10/4/25
 */

public abstract class BaseJsonRepository<T, ID> {
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final File file;
    private final TypeReference<List<T>> typeRef;
    private final Function<T, ID> idGetter;
    private final BiConsumer<T, ID> idSetter;

    protected BaseJsonRepository(String filePath, TypeReference<List<T>> typeRef, Function<T, ID> idGetter, BiConsumer<T, ID> idSetter) {
        this.file = new File(filePath);
        this.typeRef = typeRef;
        this.idGetter = idGetter;
        this.idSetter = idSetter;
    }

    public List<T> findAll() {
        try {
            if (!file.exists()) return new ArrayList<>();
            return objectMapper.readValue(file, typeRef);
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public Optional<T> findById(ID id) {
        return findAll().stream()
                .filter(item -> Objects.equals(idGetter.apply(item), id))
                .findFirst();
    }

    public void save(T entity) {
        List<T> all = findAll();
        ID id = idGetter.apply(entity);
        Timestamp created = new Timestamp(System.currentTimeMillis());
        if (id == null) {
            id = generateNextId(all);
            idSetter.accept(entity, id);

            try {
                Method setter = entity.getClass().getMethod("setCreatedDate", Timestamp.class);
                setter.invoke(entity, created);
            } catch (NoSuchMethodException e) {
            } catch (Exception e) {
                e.printStackTrace();
            }

        } else {
            try {
                T oldE = findById(id).get();
                Method getter = oldE.getClass().getMethod("getCreatedDate");
                created = (Timestamp) getter.invoke(oldE);
            } catch (NoSuchMethodException e) {
            } catch (Exception e) {
                e.printStackTrace();
            }
            // remove old if exists
            ID finalId = id;
            all = all.stream()
                    .filter(item -> !Objects.equals(idGetter.apply(item), finalId))
                    .collect(Collectors.toList());
            try {
                Method setter = entity.getClass().getMethod("setCreatedDate", Timestamp.class);
                setter.invoke(entity, created);
            } catch (NoSuchMethodException e) {
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        all.add(entity);
        writeToFile(all);
    }

    public void saveAll(List<T> entities) {
        Map<ID, T> map = findAll().stream()
                .collect(Collectors.toMap(idGetter, Function.identity()));

        for (T entity : entities) {
            map.put(idGetter.apply(entity), entity);
        }

        writeToFile(new ArrayList<>(map.values()));
    }

    public void deleteById(ID id) {
        List<T> all = findAll().stream()
                .filter(item -> !Objects.equals(idGetter.apply(item), id))
                .collect(Collectors.toList());
        writeToFile(all);
    }

    public void deleteAll() {
        writeToFile(new ArrayList<>());
    }

    private void writeToFile(List<T> list) {
        try {
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(file, list);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    private ID generateNextId(List<T> all) {
        long nextId = all.stream()
                .map(idGetter)
                .filter(Objects::nonNull)
                .mapToLong(id -> Long.parseLong(id.toString()))
                .max()
                .orElse(0L) + 1L;

        return (ID) Long.valueOf(nextId);
    }
}