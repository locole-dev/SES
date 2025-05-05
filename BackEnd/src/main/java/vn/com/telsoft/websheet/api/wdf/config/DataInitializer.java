package vn.com.telsoft.websheet.api.wdf.config;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Value;
import vn.com.telsoft.websheet.api.wdf.util.Sha1PasswordEncoder;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Project: WDF
 * Author: LAMLT
 * Created on: 10/4/25
 */

@Component
public class DataInitializer implements ApplicationRunner {

    @Value("${file.upload-data}")
    private String dataDir;

    @Value("${file.upload-dir}")
    private String uploadDir;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        File upload = new File(uploadDir);
        File data = new File(dataDir);
        if (!data.exists()) {
            boolean created = data.mkdirs();
            System.out.println("📁 Created data directory: " + dataDir + " => " + created);
        }

        createJsonFileIfNotExists("ap_param.json");
        createJsonFileIfNotExists("carrier.json");
        createJsonFileIfNotExists("carrier_mappings.json");
        createJsonFileIfNotExists("dictionary.json");
        createJsonFileIfNotExists("logo.json");
        createJsonFileIfNotExists("smart_watch.json");
        createJsonFileIfNotExists("user.json");
        createJsonFileIfNotExists("token.json");

        if (!upload.exists()) {
            boolean created = upload.mkdirs();
            System.out.println("📁 Created data directory: " + upload + " => " + created);
        }
    }


    private void createJsonFileIfNotExists(String fileName) {
        try {
            File file = new File(dataDir, fileName);
            if (!file.exists()) {
                file.createNewFile();
                // Ghi mảng rỗng mặc định để tránh lỗi khi đọc
                try (FileWriter writer = new FileWriter(file)) {
                    if ("user.json".equals(fileName)) {
                        String encodePassword = new Sha1PasswordEncoder().encode("Admin@135792468");
                        writer.write("[{\"id\":\"1\",\"username\":\"ADMIN\",\"password\": \"" + encodePassword + "\"}]");
                    } else {
                        writer.write("[]");
                    }
                }
                System.out.println("📄 Created empty file: " + file.getAbsolutePath());
            }
        } catch (IOException e) {
            System.err.println("❌ Error creating JSON file: " + fileName);
            e.printStackTrace();
        }
    }
}
