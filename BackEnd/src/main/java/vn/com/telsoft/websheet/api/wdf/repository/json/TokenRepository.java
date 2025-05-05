package vn.com.telsoft.websheet.api.wdf.repository.json;

import com.fasterxml.jackson.core.type.TypeReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import vn.com.telsoft.websheet.api.wdf.abs.BaseJsonRepository;
import vn.com.telsoft.websheet.api.wdf.dto.SmartWatchDTO;
import vn.com.telsoft.websheet.api.wdf.model.Logo;
import vn.com.telsoft.websheet.api.wdf.model.SmartWatch;
import vn.com.telsoft.websheet.api.wdf.model.Token;
import vn.com.telsoft.websheet.api.wdf.model.User;

import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Project: WDF
 * Author: LAMLT
 * Created on: 10/4/25
 */

@Repository
public class TokenRepository extends BaseJsonRepository<Token, Long> {
    @Autowired
    public TokenRepository(@Value("${file.upload-data}") String dataDir) {
        super(dataDir + "/token.json", new TypeReference<List<Token>>() {
        }, Token::getId, Token::setId);
    }

    public List<Token> findAllByUser(User user) {
        return findAll().stream().filter(p -> Objects.equals(p.getUserId(), user.getId())).collect(Collectors.toList());
    }

    public Token findByToken(String token) {
        return findAll().stream().filter(p -> Objects.equals(p.getToken(), token)).findFirst().orElse(null);
    }
}
