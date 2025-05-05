package vn.com.telsoft.websheet.api.wdf.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.com.telsoft.websheet.api.wdf.dto.CarrierDTO;
import vn.com.telsoft.websheet.api.wdf.model.Carrier;
import vn.com.telsoft.websheet.api.wdf.repository.json.CarrierRepository;
import vn.com.telsoft.websheet.api.wdf.service.CarrierService;
import vn.com.telsoft.websheet.api.wdf.util.Utils;

import java.util.List;

/**
 * Project: WDF
 * Author: LAMLT
 * Created on: 28/2/25
 */

@Service
@Slf4j
public class CarrierServiceImpl implements CarrierService {
    @Autowired
    CarrierRepository carrierRepository;

    @Override
    public List<CarrierDTO> findAllCarriers() {
        return carrierRepository.getAllCarriers();
    }

    public CarrierDTO findCarrierById(Long id) {
        Carrier carrier = carrierRepository.findById(id).get();
        return new CarrierDTO(carrier);
    }

    @Override
    public void saveCarrier(JSONObject payload, String method) {
        Carrier carrier = new Carrier();

        switch (method) {
            case "POST":
                carrier.setName(payload.getString("name"));
                carrier.setLogoId(payload.getLong("logoId"));
                carrier.setStatus(payload.getInt("status"));
                break;
            case "PUT":
                carrier = Utils.getGson().fromJson(payload.toString(), Carrier.class);
                break;
            case "DELETE":
                carrier = carrierRepository.findById(payload.getLong("id")).get();
                carrier.setStatus(-1);
                break;
        }
        carrierRepository.save(carrier);
    }
}
