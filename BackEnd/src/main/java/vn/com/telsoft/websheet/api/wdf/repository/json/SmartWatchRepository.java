package vn.com.telsoft.websheet.api.wdf.repository.json;

import com.fasterxml.jackson.core.type.TypeReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import vn.com.telsoft.websheet.api.wdf.abs.BaseJsonRepository;
import vn.com.telsoft.websheet.api.wdf.dto.SmartWatchDTO;
import vn.com.telsoft.websheet.api.wdf.model.Logo;
import vn.com.telsoft.websheet.api.wdf.model.SmartWatch;

import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Project: WDF
 * Author: LAMLT
 * Created on: 10/4/25
 */

@Repository
public class SmartWatchRepository extends BaseJsonRepository<SmartWatch, Long> {
    @Autowired
    public SmartWatchRepository(@Value("${file.upload-data}") String dataDir) {
        super(dataDir + "/smart_watch.json", new TypeReference<List<SmartWatch>>() {
        }, SmartWatch::getId, SmartWatch::setId);
    }

    @Autowired
    private LogoRepository logoRepository;

    public List<SmartWatchDTO> findAllSmartWatches() {
        List<Logo> logos = logoRepository.findAll();
        Map<Long, String> logoMap = logos.stream()
                .collect(Collectors.toMap(Logo::getId, Logo::getData));

        return findAll().stream()
                .sorted(Comparator.comparing(SmartWatch::getId).reversed())
                .map(sw -> {
                    String logoData = logoMap.get(sw.getLogoId());
                    String formattedDate = sw.getCreatedDate() != null ?
                            new SimpleDateFormat("MMM dd, yyyy").format(sw.getCreatedDate()) : null;

                    return new SmartWatchDTO(
                            sw.getId(),
                            sw.getName(),
                            sw.getLogoId(),
                            logoData,
                            sw.getStatus(),
                            formattedDate,
                            sw.getDictionaryKey()
                    );
                })
                .collect(Collectors.toList());
    }
}
