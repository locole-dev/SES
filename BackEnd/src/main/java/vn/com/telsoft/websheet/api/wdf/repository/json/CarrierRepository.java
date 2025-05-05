package vn.com.telsoft.websheet.api.wdf.repository.json;

import com.fasterxml.jackson.core.type.TypeReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import vn.com.telsoft.websheet.api.wdf.abs.BaseJsonRepository;
import vn.com.telsoft.websheet.api.wdf.dto.CarrierDTO;
import vn.com.telsoft.websheet.api.wdf.model.Logo;
import vn.com.telsoft.websheet.api.wdf.model.Carrier;

import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Project: WDF
 * Author: LAMLT
 * Created on: 10/4/25
 */

@Repository
public class CarrierRepository extends BaseJsonRepository<Carrier, Long> {
    @Autowired
    public CarrierRepository(@Value("${file.upload-data}") String dataDir) {
        super(dataDir + "/carrier.json", new TypeReference<List<Carrier>>() {
        }, Carrier::getId, Carrier::setId);
    }

    @Autowired
    private LogoRepository logoRepository;

    public List<CarrierDTO> getAllCarriers() {
        List<Logo> logos = logoRepository.findAll();
        Map<Long, String> logoMap = logos.stream()
                .collect(Collectors.toMap(Logo::getId, Logo::getData));

        return findAll().stream()
                .sorted(Comparator.comparing(Carrier::getId).reversed())
                .map(sw -> {
                    String logoData = logoMap.get(sw.getLogoId());
                    String formattedDate = sw.getCreatedDate() != null ?
                            new SimpleDateFormat("MMM dd, yyyy").format(sw.getCreatedDate()) : null;

                    return new CarrierDTO(
                            sw.getId(),
                            sw.getName(),
                            sw.getLogoId(),
                            logoData,
                            sw.getStatus(),
                            formattedDate
                    );
                })
                .collect(Collectors.toList());
    }
}
