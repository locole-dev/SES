package vn.com.telsoft.websheet.api.wdf.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import vn.com.telsoft.websheet.api.wdf.model.Logo;
import vn.com.telsoft.websheet.api.wdf.repository.json.LogoRepository;
import vn.com.telsoft.websheet.api.wdf.service.LogoService;

import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;

/**
 * Project: WDF
 * Author: LAMLT
 * Created on: 28/2/25
 */

@Service
@Slf4j
public class LogoServiceImpl implements LogoService {

    @Autowired
    private LogoRepository logoRepository;

    @Value("${file.upload-dir}")
    private String uploadDir;

    @Override
    public void uploadLogo(Logo logo) {
        logoRepository.save(logo);
    }

    @Override
    public void deleteLogo(Long id) {
        logoRepository.deleteById(id);
    }

    @Override
    public List<Logo> findAllLogos(Long type) {
        if (type != null) {
            return logoRepository.findAllByTypeOrderByIdDesc(Integer.parseInt(type.toString()));
        } else {
            return logoRepository.findAllByOrderByIdDesc();
        }
    }

    @Override
    public JSONObject findLogoById(Long id) {
        Logo logo = logoRepository.findById(id).get();
        return new JSONObject().put("id", logo.getId()).put("data", logo.getData()).put("type", logo.getType());
    }

    @Override
    public void deleteFileById(Long id) {
        try {
            JSONObject jsoLogo = findLogoById(id);
            String filePath = jsoLogo.getString("data");
            String fileName = Paths.get(new URI(filePath).getPath()).getFileName().toString();

            Path path = Paths.get(uploadDir).resolve(fileName);
            if (Files.exists(path)) {
                Files.delete(path);
            }
        } catch (Exception ignored) {
        }
    }
}
