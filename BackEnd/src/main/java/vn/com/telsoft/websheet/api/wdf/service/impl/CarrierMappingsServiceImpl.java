package vn.com.telsoft.websheet.api.wdf.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.com.telsoft.websheet.api.wdf.dto.*;
import vn.com.telsoft.websheet.api.wdf.model.CarrierMappings;
import vn.com.telsoft.websheet.api.wdf.repository.json.CarrierMappingsRepository;
import vn.com.telsoft.websheet.api.wdf.service.CarrierMappingsService;
import vn.com.telsoft.websheet.api.wdf.service.DictionaryService;
import vn.com.telsoft.websheet.api.wdf.service.SmartWatchService;
import vn.com.telsoft.websheet.api.wdf.util.Utils;

import java.util.List;

/**
 * Project: WDF
 * Author: LAMLT
 * Created on: 5/3/25
 */

@Service
@Slf4j
public class CarrierMappingsServiceImpl implements CarrierMappingsService {

    @Autowired
    CarrierMappingsRepository carrierMappingsRepository;

    @Autowired
    SmartWatchService smartWatchService;

    @Autowired
    DictionaryService dictionaryService;

    @Override
    public List<CarrierMappingsDTO> findAllCarrierMappingsByCarrierId(Long carrierId) {
        return carrierMappingsRepository.findAllByCarrierId(carrierId);
    }

    @Override
    public CarrierMappingsDTO findCarrierMappingsById(Long id) {
        return carrierMappingsRepository.findByCarrierId(id);
    }

    @Override
    public ThemeDTO findBySmartWatchNameAndCarrierName(String smartWatchName, String carrierName) {
        ThemeDTO themeDTO =  carrierMappingsRepository.findBySmartWatchNameAndCarrierName(smartWatchName.toUpperCase(), carrierName.toUpperCase());
        return themeDTO;
    }

    @Override
    public void saveCarrierMappings(JSONObject payload, String method) {
        CarrierMappings carrierMappings = new CarrierMappings();
        switch (method) {
            case "POST":
                carrierMappings.setCarrierId(payload.getLong("carrierId"));
                carrierMappings.setSmartWatchId(payload.getLong("smartWatchId"));
                carrierMappings.setStatus(payload.getInt("status"));
                carrierMappings.setStyle(payload.getString("style"));
                carrierMappings.setLogoId(payload.getLong("logoId"));
                carrierMappings.setDescription(payload.getString("description"));
                if (payload.has("backgroundId")) {
                    carrierMappings.setBackgroundId(payload.getLong("backgroundId"));
                }
                break;
            case "PUT":
                carrierMappings = Utils.getGson().fromJson(payload.toString(), CarrierMappings.class);
                break;
            case "DELETE":
                carrierMappingsRepository.deleteById(payload.getLong("id"));
                return;
        }
        carrierMappingsRepository.save(carrierMappings);
    }
}
