package vn.com.telsoft.websheet.api.wdf.service;

import org.json.JSONObject;
import vn.com.telsoft.websheet.api.wdf.dto.CarrierDTO;
import vn.com.telsoft.websheet.api.wdf.model.Carrier;

import java.util.List;

/**
 * Project: WDF
 * Author: LAMLT
 * Created on: 28/2/25
 */

public interface CarrierService {
    List<CarrierDTO> findAllCarriers();
    CarrierDTO findCarrierById(Long id);
    void saveCarrier(JSONObject payload, String method);
}
