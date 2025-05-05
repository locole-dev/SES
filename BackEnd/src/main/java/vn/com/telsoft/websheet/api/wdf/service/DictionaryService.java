package vn.com.telsoft.websheet.api.wdf.service;

import org.json.JSONObject;
import vn.com.telsoft.websheet.api.wdf.dto.DictionaryDataDto;
import vn.com.telsoft.websheet.api.wdf.model.Dictionary;

import java.util.List;

/**
 * Project: WDF
 * Author: LAMLT
 * Created on: 28/2/25
 */

public interface DictionaryService {
    List<Dictionary> findAllDictionaries();
    Dictionary findDictionaryById(Long id);
    void saveDictionary(JSONObject payload, String methodÏ);
    void deleteDictionaryById(Long id);
    DictionaryDataDto findDictionaryDataBySmartWatchKey(Long smartWatchKey);
}
