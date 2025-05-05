package vn.com.telsoft.websheet.api.wdf.service;

import org.json.JSONObject;
import vn.com.telsoft.websheet.api.wdf.dto.CarrierMappingsDTO;
import vn.com.telsoft.websheet.api.wdf.dto.ThemeDTO;
import vn.com.telsoft.websheet.api.wdf.model.CarrierMappings;

import java.util.List;

/**
 * Project: WDF
 * Author: LAMLT
 * Created on: 28/2/25
 */

public interface CarrierMappingsService {
    List<CarrierMappingsDTO> findAllCarrierMappingsByCarrierId(Long carrierId);
    CarrierMappingsDTO findCarrierMappingsById(Long id);
    ThemeDTO findBySmartWatchNameAndCarrierName(String smartWatchName, String carrierName);
    void saveCarrierMappings(JSONObject payload, String method);
}
