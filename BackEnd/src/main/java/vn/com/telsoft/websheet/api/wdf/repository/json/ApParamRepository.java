package vn.com.telsoft.websheet.api.wdf.repository.json;

import com.fasterxml.jackson.core.type.TypeReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import vn.com.telsoft.websheet.api.wdf.abs.BaseJsonRepository;
import vn.com.telsoft.websheet.api.wdf.model.ApParam;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Project: WDF
 * Author: LAMLT
 * Created on: 10/4/25
 */

@Repository
public class ApParamRepository extends BaseJsonRepository<ApParam, Long> {
    @Autowired
    public ApParamRepository(@Value("${file.upload-data}") String dataDir) {
        super(dataDir + "/ap_param.json", new TypeReference<List<ApParam>>() {
        }, ApParam::getId, ApParam::setId);
    }

    public Optional<ApParam> findByValue(String value) {
        return findAll().stream()
                .filter(item -> Objects.equals(item.getValue(), value))
                .findFirst();
    }


    public List<ApParam> findAllByTypeOrderByIdDesc(String type) {
        return findAll().stream()
                .filter(p -> Objects.equals(p.getType(), type))
                .sorted(Comparator.comparing(ApParam::getId).reversed())
                .collect(Collectors.toList());
    }
}
