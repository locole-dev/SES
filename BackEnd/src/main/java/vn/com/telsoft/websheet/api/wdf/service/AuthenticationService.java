package vn.com.telsoft.websheet.api.wdf.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.var;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import vn.com.telsoft.websheet.api.wdf.config.JwtService;
import vn.com.telsoft.websheet.api.wdf.model.AuthenticationRequest;
import vn.com.telsoft.websheet.api.wdf.model.AuthenticationResponse;
import vn.com.telsoft.websheet.api.wdf.model.Token;
import vn.com.telsoft.websheet.api.wdf.model.User;
import vn.com.telsoft.websheet.api.wdf.repository.json.TokenRepository;
import vn.com.telsoft.websheet.api.wdf.repository.json.UserRepository;
import vn.com.telsoft.websheet.api.wdf.util.Sha1PasswordEncoder;

import javax.servlet.http.HttpServletRequest;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthenticationService {
    private final UserRepository userRepository;
    private final TokenRepository tokenRepository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public ResponseEntity authenticate(AuthenticationRequest request, HttpServletRequest httpServletRequest) {
        var user = userRepository.findByUsername(request.getUsername());
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Sha1PasswordEncoder encoder = new Sha1PasswordEncoder();
        if (!encoder.matches(request.getPassword(), user.getPassword())) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );
        var jwtToken = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(user);
        revokeAllUserTokens(user);
        saveUserToken(user, jwtToken);

        AuthenticationResponse authenticationResponse = AuthenticationResponse.builder()
                .accessToken(jwtToken)
                .refreshToken(refreshToken)
                .username(user.getUsername())
                .build();

        return new ResponseEntity<>(authenticationResponse, HttpStatus.OK);

    }

    private void saveUserToken(User user, String jwtToken) {
        var token = Token.builder()
                .userId(user.getId())
                .token(jwtToken)
                .build();
        tokenRepository.save(token);
    }

    private void revokeAllUserTokens(User user) {
        var validUserTokens = tokenRepository.findAllByUser(user);
        if (validUserTokens.isEmpty())
            return;
        tokenRepository.deleteById(validUserTokens.get(0).getId());
    }
}
