package vn.com.telsoft.websheet.api.wdf.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.com.telsoft.websheet.api.wdf.model.Dictionary;
import vn.com.telsoft.websheet.api.wdf.repository.json.DictionaryRepository;
import vn.com.telsoft.websheet.api.wdf.service.DictionaryService;
import vn.com.telsoft.websheet.api.wdf.dto.DictionaryDataDto;
import org.json.JSONArray;
import java.util.*;

/**
 * Project: WDF
 * Author: LAMLT
 * Created on: 28/2/25
 */

@Service
@Slf4j
public class DictionaryServiceImpl implements DictionaryService {

    @Autowired
    DictionaryRepository dictionaryRepository;

    @Override
    public List<Dictionary> findAllDictionaries() {
        return dictionaryRepository.findAllByOrderById();
    }

    @Override
    public Dictionary findDictionaryById(Long id) {
        return dictionaryRepository.findById(id).get();
    }

    @Override
    public void saveDictionary(JSONObject payload, String method) {
        Dictionary dictionary = new Dictionary();
        if (method.equals("PUT")) {
            dictionary.setId(payload.getLong("id"));
            dictionary.setKey(payload.getString("key"));
            dictionary.setEng(payload.getString("eng"));
            dictionary.setVi(payload.getString("vi"));
            dictionaryRepository.save(dictionary);
            return;
        }
        dictionary.setKey(payload.getString("key"));
        dictionary.setEng(payload.getString("eng"));
        dictionary.setVi(payload.getString("vi"));
        JSONArray smartWatchKeysArray = payload.getJSONArray("smartWatchKeys");
        List<Long> smartWatchKeys = new ArrayList<>();
        for (int i = 0; i < smartWatchKeysArray.length(); i++) {
            if (smartWatchKeysArray.isNull(i)) {
                dictionaryRepository.save(dictionary);
                return;
            }
            smartWatchKeys.add(smartWatchKeysArray.getLong(i));
        }
            for( Long smartWatchKey : smartWatchKeys) {
                Dictionary d = new Dictionary();
                d.setEng(payload.getString("eng"));
                d.setVi(payload.getString("vi"));
                d.setKey(payload.getString("key") +'_'+ smartWatchKey.toString());
                dictionaryRepository.save(d);
            }

    }

    @Override
    public void deleteDictionaryById(Long id) {
        dictionaryRepository.deleteById(id);
    }

    @Override
    public DictionaryDataDto findDictionaryDataBySmartWatchKey(Long smartWatchKey) {
        List<Dictionary> dictionaries = findAllDictionaries();
        DictionaryDataDto dto = new DictionaryDataDto();

        for (Dictionary dictionary : dictionaries) {
            int lastUnderscoreIndex = dictionary.getKey().lastIndexOf("_");
            if (!dictionary.getKey().substring(lastUnderscoreIndex + 1).isEmpty() && dictionary.getKey().substring(lastUnderscoreIndex + 1 ).contains(smartWatchKey.toString())
            ) {
                dto.addVi(dictionary.getKey().substring(0, lastUnderscoreIndex ), dictionary.getVi());
                dto.addEng(dictionary.getKey().substring(0, lastUnderscoreIndex ), dictionary.getEng());
            }
            if (lastUnderscoreIndex == -1 && !dto.getVi().containsKey(dictionary.getKey()) ) {
                dto.addVi(dictionary.getKey(), dictionary.getVi());
                dto.addEng(dictionary.getKey(), dictionary.getEng());
            }
        }
        return dto;
    }
}
