package vn.com.telsoft.websheet.api.wdf.service;

import org.json.JSONObject;
import vn.com.telsoft.websheet.api.wdf.dto.SmartWatchDTO;
import vn.com.telsoft.websheet.api.wdf.dto.SmartWatchDtoV2;
import vn.com.telsoft.websheet.api.wdf.model.SmartWatch;

import java.util.List;
import java.util.Objects;

/**
 * Project: WDF
 * Author: LAMLT
 * Created on: 28/2/25
 */

public interface SmartWatchService {
    List<SmartWatchDTO> findAllSmartWatches();
    SmartWatchDTO findSmartWatchById(Long id);
    void saveSmartWatch(JSONObject payload, String method);
    List<SmartWatchDtoV2> findAllSmartWatchesForDictionaryForm();
    SmartWatchDTO findSmartWatchBySmartWatchName(String smartWatchName);
}
