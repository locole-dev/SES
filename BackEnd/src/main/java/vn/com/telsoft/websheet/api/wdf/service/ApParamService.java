package vn.com.telsoft.websheet.api.wdf.service;

import vn.com.telsoft.websheet.api.wdf.model.ApParam;

import java.util.List;

/**
 * Project: WDF
 * Author: LAMLT
 * Created on: 12/3/25
 */

public interface ApParamService {
    List<ApParam> findAll();
    List<ApParam> findAllByType(String type);
    ApParam getApParamByValue(String value);
    ApParam getApParamById(Long id);
    void saveApParam(ApParam apParam);
    void deleteApParamById(Long id);
}
