package vn.com.telsoft.websheet.api.wdf.repository.json;

import com.fasterxml.jackson.core.type.TypeReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import vn.com.telsoft.websheet.api.wdf.abs.BaseJsonRepository;
import vn.com.telsoft.websheet.api.wdf.dto.SmartWatchDTO;
import vn.com.telsoft.websheet.api.wdf.model.Logo;
import vn.com.telsoft.websheet.api.wdf.model.SmartWatch;
import vn.com.telsoft.websheet.api.wdf.model.User;

import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Project: WDF
 * Author: LAMLT
 * Created on: 10/4/25
 */

@Repository
public class UserRepository extends BaseJsonRepository<User, Long> {
    @Autowired
    public UserRepository(@Value("${file.upload-data}") String dataDir) {
        super(dataDir + "/user.json", new TypeReference<List<User>>() {
        }, User::getId, User::setId);
    }

    public User findByUsername(String username) {
        return findAll().stream().filter(user -> user.getUsername().equals(username)).findFirst().orElse(null);
    }
}
