package vn.com.telsoft.websheet.api.wdf.controller;

import org.apache.commons.io.IOUtils;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.com.telsoft.websheet.api.wdf.model.Logo;
import vn.com.telsoft.websheet.api.wdf.service.LogoService;
import vn.com.telsoft.websheet.api.wdf.util.Const;
import vn.com.telsoft.websheet.api.wdf.util.Utils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("${wdf.request-path-prefix}")
public class LogoController {
    @Autowired
    private LogoService logoService;

    @Value("${file.upload-dir}")
    private String uploadDir;

    public static final Logger logger = LoggerFactory.getLogger(LogoController.class);

    @RequestMapping(value = "/upload",
            method = {RequestMethod.POST, RequestMethod.PUT},
            produces = {MediaType.APPLICATION_JSON_VALUE,
                    MediaType.APPLICATION_XML_VALUE})
    @ResponseBody
    public ResponseEntity uploadLogo(HttpServletRequest request) throws IOException, ServletException {
        int type = Integer.parseInt(request.getParameter("type"));
        Logo logo = Logo.builder()
                .type(type)
                .build();
        String fileUrl = null;
        Path uploadPath = Paths.get(uploadDir);
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }
        if (request.getMethod().equals("PUT")) {
            logo.setId(Long.parseLong(request.getParameter("id")));
        }
        if (request.getPart("file") != null) {
            if (request.getMethod().equals("PUT")) {
                logoService.deleteFileById(logo.getId());
            }
            Part file = request.getPart("file");
            logger.info("LogoController upload: " + new JSONObject().put("type", type).put("file", file.getSubmittedFileName()));
            // Lưu ảnh
            String fileName = UUID.randomUUID() + "_" + file.getSubmittedFileName();
            Path filePath = uploadPath.resolve(fileName);
            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
            fileUrl = fileName;
        } else {
            logger.info("LogoController upload update: " + new JSONObject().put("type", type).put("id", request.getParameter("id")));
            fileUrl = request.getParameter("data");
        }

        logo.setData(fileUrl);

        logoService.uploadLogo(logo);
        return new ResponseEntity<>(Const.ResponseFieldName.SUCCESS, HttpStatus.OK);
    }

    @RequestMapping(value = "/uploadBase64",
            method = RequestMethod.POST,
            produces = {MediaType.APPLICATION_JSON_VALUE,
                    MediaType.APPLICATION_XML_VALUE})
    @ResponseBody
    public ResponseEntity uploadLogoBase64(HttpServletRequest request) throws IOException, ServletException {
        String name = request.getParameter("name");
        int type = Integer.parseInt(request.getParameter("type"));
        Part filePart = request.getPart("file");

        logger.info("LogoController upload: " + new JSONObject().put("name", name).put("type", type).put("file", filePart.getSubmittedFileName()));

        InputStream fileContent = filePart.getInputStream();
        byte[] fileBytes = IOUtils.toByteArray(fileContent);
        String base64String = Base64.getEncoder().encodeToString(fileBytes);

        Logo logo = Logo.builder()
                .data(base64String)
                .type(type)
                .build();
        logoService.uploadLogo(logo);
        return new ResponseEntity<>(Const.ResponseFieldName.SUCCESS, HttpStatus.OK);
    }

    @RequestMapping(value = "/deleteLogo/{id}",
            method = RequestMethod.DELETE,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity deleteLogo(@PathVariable("id") Long id) throws IOException {
        logger.info("LogoController deleteLogo: " + id);
        try {
            logoService.deleteFileById(id);
            logoService.deleteLogo(id);
            return new ResponseEntity<>(Const.ResponseFieldName.SUCCESS, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error deleting file.");
        }
    }

    @RequestMapping(value = "/listLogos",
            method = RequestMethod.GET,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity listLogos(@RequestParam(required = false) Long type) throws IOException {
        logger.info("LogoController listLogos: ");
        List<Logo> list = logoService.findAllLogos(type);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @RequestMapping(value = "/getLogo/{id}",
            method = RequestMethod.GET,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity getLogos(@PathVariable("id") Long id) throws IOException {
        logger.info("LogoController getLogos: " + id);
        JSONObject logo = logoService.findLogoById(id);
        return new ResponseEntity<>(Utils.getGson().fromJson(logo.toString(), HashMap.class), HttpStatus.OK);
    }

    @GetMapping("/{filename:.+}")
    public ResponseEntity<Resource> getImage(@PathVariable String filename) {
        try {
            Path imagePath = Paths.get(uploadDir, filename);
            Resource image = new UrlResource(imagePath.toUri());

            if (image.exists() && image.isReadable()) {

                String contentType = Files.probeContentType(imagePath);
                MediaType mediaType = contentType != null ? MediaType.parseMediaType(contentType) : MediaType.APPLICATION_OCTET_STREAM;

                return ResponseEntity.ok()
                        .contentType(mediaType)
                        .body(image);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (MalformedURLException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
