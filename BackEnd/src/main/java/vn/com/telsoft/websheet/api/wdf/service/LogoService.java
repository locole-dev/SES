package vn.com.telsoft.websheet.api.wdf.service;

import org.json.JSONObject;
import vn.com.telsoft.websheet.api.wdf.model.Logo;

import java.util.List;

/**
 * Project: WDF
 * Author: LAMLT
 * Created on: 28/2/25
 */

public interface LogoService {
    void uploadLogo(Logo logo);
    void deleteLogo(Long id);
    List<Logo> findAllLogos(Long type);
    JSONObject findLogoById(Long id);
    void deleteFileById(Long id);
}
