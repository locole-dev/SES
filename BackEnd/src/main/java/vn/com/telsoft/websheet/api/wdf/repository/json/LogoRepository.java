package vn.com.telsoft.websheet.api.wdf.repository.json;

import com.fasterxml.jackson.core.type.TypeReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import vn.com.telsoft.websheet.api.wdf.abs.BaseJsonRepository;
import vn.com.telsoft.websheet.api.wdf.model.Logo;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Project: WDF
 * Author: LAMLT
 * Created on: 10/4/25
 */

@Repository
public class LogoRepository extends BaseJsonRepository<Logo, Long> {
    @Autowired
    public LogoRepository(@Value("${file.upload-data}") String dataDir) {
        super(dataDir + "/logo.json", new TypeReference<List<Logo>>() {
        }, Logo::getId, Logo::setId);
    }

    public List<Logo> findAllByOrderByIdDesc() {
        return findAll().stream()
                .sorted(Comparator.comparing(Logo::getId).reversed())
                .collect(Collectors.toList());
    }

    public List<Logo> findAllByTypeOrderByIdDesc(int type) {
        return findAll().stream()
                .filter(p -> Objects.equals(p.getType(), type))
                .sorted(Comparator.comparing(Logo::getId).reversed())
                .collect(Collectors.toList());
    }
}
