package vn.com.telsoft.websheet.api.wdf.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.com.telsoft.websheet.api.wdf.model.ApParam;
import vn.com.telsoft.websheet.api.wdf.repository.json.ApParamRepository;
import vn.com.telsoft.websheet.api.wdf.service.ApParamService;

import java.util.List;

/**
 * Project: WDF
 * Author: LAMLT
 * Created on: 12/3/25
 */

@Service
@Slf4j
public class ApParamServiceImpl implements ApParamService {

    @Autowired
    private ApParamRepository apParamRepository;

    @Override
    public List<ApParam> findAll() {
        return apParamRepository.findAll();
    }

    @Override
    public List<ApParam> findAllByType(String type) {
        return apParamRepository.findAllByTypeOrderByIdDesc(type);
    }

    @Override
    public ApParam getApParamByValue(String value) {
        return apParamRepository.findByValue(value).get();
    }

    @Override
    public ApParam getApParamById(Long id) {
        return apParamRepository.findById(id).get();
    }

    @Override
    public void saveApParam(ApParam apParam) {
        apParamRepository.save(apParam);
    }

    @Override
    public void deleteApParamById(Long id) {
        apParamRepository.deleteById(id);
    }
}
