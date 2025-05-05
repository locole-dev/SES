package vn.com.telsoft.websheet.api.wdf.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.com.telsoft.websheet.api.wdf.dto.SmartWatchDTO;
import vn.com.telsoft.websheet.api.wdf.dto.SmartWatchDtoV2;
import vn.com.telsoft.websheet.api.wdf.model.Dictionary;
import vn.com.telsoft.websheet.api.wdf.model.SmartWatch;
import vn.com.telsoft.websheet.api.wdf.repository.json.DictionaryRepository;
import vn.com.telsoft.websheet.api.wdf.repository.json.SmartWatchRepository;
import vn.com.telsoft.websheet.api.wdf.service.SmartWatchService;
import vn.com.telsoft.websheet.api.wdf.util.Utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * Project: WDF
 * Author: LAMLT
 * Created on: 28/2/25
 */

@Service
@Slf4j
public class SmartWatchServiceImpl implements SmartWatchService {

    @Autowired
    SmartWatchRepository smartWatchRepository;

    @Autowired
    DictionaryRepository dictionaryRepository;

    @Override
    public List<SmartWatchDTO> findAllSmartWatches() {
        return smartWatchRepository.findAllSmartWatches();
    }

    @Override
    public SmartWatchDTO findSmartWatchById(Long id) {
        SmartWatch smartWatch = smartWatchRepository.findById(id).get();
        return new SmartWatchDTO(smartWatch);
    }

    @Override
    public void saveSmartWatch(JSONObject payload, String method) {
        SmartWatch smartWatch = new SmartWatch();

        switch (method) {
            case "POST":
                smartWatch.setName(payload.getString("name"));
                smartWatch.setLogoId(payload.getLong("logoId"));
                smartWatch.setStatus(payload.getInt("status"));
                smartWatch.setDictionaryKey(System.currentTimeMillis());
                break;
            case "PUT":
                smartWatch = Utils.getGson().fromJson(payload.toString(), SmartWatch.class);
                break;
            case "DELETE":
                smartWatch = smartWatchRepository.findById(payload.getLong("id")).get();
                smartWatch.setStatus(-1);
                break;
        }
        smartWatchRepository.save(smartWatch);
    }

    @Override
    public List<SmartWatchDtoV2> findAllSmartWatchesForDictionaryForm() {
        List<SmartWatchDTO> smartWatches = findAllSmartWatches();
        List<SmartWatchDtoV2> dtos = new ArrayList<>();
        List<Dictionary>  dictionaries = dictionaryRepository.findAllByOrderById();
        for (SmartWatchDTO smartWatchDTO : smartWatches) {
            SmartWatchDtoV2 smartWatchDtoV2 = new SmartWatchDtoV2();
            smartWatchDtoV2.setId(smartWatchDTO.getId());
            smartWatchDtoV2.setName(smartWatchDTO.getName());
            smartWatchDtoV2.setLogoId(smartWatchDTO.getLogoId());
            smartWatchDtoV2.setLogoUrl(smartWatchDTO.getLogoUrl());
            smartWatchDtoV2.setStatus(smartWatchDTO.getStatus());
            smartWatchDtoV2.setDictionaryKey(smartWatchDTO.getDictionaryKey());
            smartWatchDtoV2.setIsDisabled(false);
            for (Dictionary dictionary : dictionaries) {

                if (dictionary.getKey().contains(smartWatchDTO.getDictionaryKey().toString())) {
                    smartWatchDtoV2.setIsDisabled(true);
                }
            }
            dtos.add(smartWatchDtoV2);
        }
        return dtos;
    }

    @Override
    public SmartWatchDTO findSmartWatchBySmartWatchName(String smartWatchName) {
        List<SmartWatchDTO> smartWatches = findAllSmartWatches();
        for (SmartWatchDTO smartWatchDTO : smartWatches) {
            if (smartWatchDTO.getName().toUpperCase().equals(smartWatchName)) {
                return smartWatchDTO;
            }
        }
        return null;
    }
}
