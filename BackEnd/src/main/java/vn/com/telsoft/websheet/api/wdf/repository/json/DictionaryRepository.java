package vn.com.telsoft.websheet.api.wdf.repository.json;

import com.fasterxml.jackson.core.type.TypeReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import vn.com.telsoft.websheet.api.wdf.abs.BaseJsonRepository;
import vn.com.telsoft.websheet.api.wdf.model.Dictionary;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Project: WDF
 * Author: LAMLT
 * Created on: 10/4/25
 */

@Repository
public class DictionaryRepository extends BaseJsonRepository<Dictionary, Long> {
    @Autowired
    public DictionaryRepository(@Value("${file.upload-data}") String dataDir) {
        super(dataDir + "/dictionary.json", new TypeReference<List<Dictionary>>() {
        }, Dictionary::getId, Dictionary::setId);
    }

    public List<Dictionary> findAllByOrderById() {
        return findAll().stream()
                .sorted(Comparator.comparing(Dictionary::getId).reversed())
                .collect(Collectors.toList());
    }
}
