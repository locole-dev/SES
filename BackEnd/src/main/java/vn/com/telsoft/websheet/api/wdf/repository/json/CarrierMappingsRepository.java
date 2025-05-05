package vn.com.telsoft.websheet.api.wdf.repository.json;

import com.fasterxml.jackson.core.type.TypeReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import vn.com.telsoft.websheet.api.wdf.abs.BaseJsonRepository;
import vn.com.telsoft.websheet.api.wdf.dto.CarrierMappingsDTO;
import vn.com.telsoft.websheet.api.wdf.dto.ThemeDTO;
import vn.com.telsoft.websheet.api.wdf.model.Carrier;
import vn.com.telsoft.websheet.api.wdf.model.Logo;
import vn.com.telsoft.websheet.api.wdf.model.CarrierMappings;
import vn.com.telsoft.websheet.api.wdf.model.SmartWatch;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Project: WDF
 * Author: LAMLT
 * Created on: 10/4/25
 */

@Repository
public class CarrierMappingsRepository extends BaseJsonRepository<CarrierMappings, Long> {
    @Autowired
    public CarrierMappingsRepository(@Value("${file.upload-data}") String dataDir) {
        super(dataDir + "/carrier_mappings.json", new TypeReference<List<CarrierMappings>>() {
        }, CarrierMappings::getId, CarrierMappings::setId);
    }

    @Autowired
    private LogoRepository logoRepository;

    @Autowired
    private SmartWatchRepository smartWatchRepository;

    @Autowired
    private CarrierRepository carrierRepository;
    public List<CarrierMappingsDTO> findAllByCarrierId(Long carrierId) {

        List<CarrierMappings> mappings = findAll().stream()
                .filter(cm -> carrierId == null || Objects.equals(cm.getCarrierId(), carrierId))
                .sorted(Comparator.comparing(CarrierMappings::getId).reversed())
                .collect(Collectors.toList());

        if (mappings.isEmpty()) {
            return new ArrayList<>();
        }

        Map<Long, SmartWatch> watchMap = smartWatchRepository.findAll().stream()
                .collect(Collectors.toMap(SmartWatch::getId, Function.identity()));

        Map<Long, Logo> logoMap = logoRepository.findAll().stream()
                .collect(Collectors.toMap(Logo::getId, Function.identity()));

        Map<Long, Carrier> carrierMap = carrierRepository.findAll().stream()
                .collect(Collectors.toMap(Carrier::getId, Function.identity()));

        return mappings.stream().map(cm -> {
            SmartWatch watch = watchMap.get(cm.getSmartWatchId());
            Logo logo = (watch != null) ? logoMap.get(watch.getLogoId()) : null;
            Logo background = (cm.getBackgroundId() != null) ? logoMap.get(cm.getBackgroundId()) : null;
            Carrier carrier = carrierMap.get(cm.getCarrierId());

            return new CarrierMappingsDTO(
                    cm.getId(),
                    cm.getCarrierId(),
                    cm.getSmartWatchId(),
                    cm.getLogoId(),
                    carrier != null ? carrier.getLogoId() : null,
                    cm.getBackgroundId() != null ? background.getId() : null,
                    cm.getStatus(),
                    cm.getStyle(),
                    cm.getDescription(),
                    logo != null ? logo.getData() : null,
                    watch != null ? watch.getName() : null,
                    cm.getBackgroundId() != null ? background.getData() : null
            );
        }).collect(Collectors.toList());
    }

    public CarrierMappingsDTO findByCarrierId(Long id) {
        return findAllByCarrierId(null).stream()
                .filter(dto -> Objects.equals(dto.getId(), id))
                .findFirst()
                .orElse(null);
    }

    public List<CarrierMappings> findAllCarrierMappingBySmartWatchId(Long smartWatchId, Long carrierId) {
        return findAll().stream().filter(p -> Objects.equals(p.getSmartWatchId(), smartWatchId) && Objects.equals(p.getCarrierId(), carrierId)).collect(Collectors.toList());
    }

    public ThemeDTO findBySmartWatchNameAndCarrierName(String watchName, String carrierName) {
        // Lấy toàn bộ danh sách mappings
        List<CarrierMappings> mappings = findAll();
        if (mappings.isEmpty()) return null;
        // Tạo map từ ID sang entity
        Map<Long, SmartWatch> watchMap = smartWatchRepository.findAll().stream()
                .collect(Collectors.toMap(SmartWatch::getId, Function.identity()));
        if (watchMap.isEmpty()) return null;
        Map<Long, Logo> logoMap = logoRepository.findAll().stream()
                .collect(Collectors.toMap(Logo::getId, Function.identity()));
        if (logoMap.isEmpty()) return null;
        Map<Long, Carrier> carrierMap = carrierRepository.findAll().stream()
                .collect(Collectors.toMap(Carrier::getId, Function.identity()));
        if (carrierMap.isEmpty()) return null;
        return mappings.stream()
                .filter(cm -> {
                    SmartWatch watch = watchMap.get(cm.getSmartWatchId());
                    Carrier carrier = carrierMap.get(cm.getCarrierId());

                    boolean watchMatch = watch != null && watch.getName() != null &&
                            watch.getName().toUpperCase().contains(watchName);
                    boolean carrierMatch = carrier != null && carrier.getName() != null &&
                            carrier.getName().toUpperCase().contains(carrierName);

                    return watchMatch && carrierMatch && cm.getStatus() == 0;
                })
                .sorted(Comparator.comparing(CarrierMappings::getId))
                .findFirst()
                .map(cm -> {
                    Logo logoCarrier = logoMap.get(cm.getLogoId());
                    Logo background = cm.getBackgroundId() != null ? logoMap.get(cm.getBackgroundId()) : null;
                    return new ThemeDTO(
                            cm.getStatus(),
                            cm.getStyle(),
                            logoCarrier.getData(),
                            background != null ? background.getData() : null
                    );
                }).orElse(null);
    }
}
