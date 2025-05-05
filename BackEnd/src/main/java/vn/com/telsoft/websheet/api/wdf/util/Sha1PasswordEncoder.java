package vn.com.telsoft.websheet.api.wdf.util;

import org.springframework.security.crypto.password.PasswordEncoder;

import java.security.MessageDigest;
import java.util.Base64;

public class Sha1PasswordEncoder implements PasswordEncoder {
    @Override
    public String encode(CharSequence rawPassword) {
        try {
            // Create SHA-1 digest
            MessageDigest sha1 = MessageDigest.getInstance("SHA-1");
            byte[] hashedBytes = sha1.digest(rawPassword.toString().getBytes());
            // Encode the SHA-1 output to Base64
            return Base64.getEncoder().encodeToString(hashedBytes);
        } catch (Exception e) {
            throw new RuntimeException("Error encoding password", e);
        }
    }

    @Override
    public boolean matches(CharSequence requestPassword, String password) {
        return requestPassword.toString().equals(password);
    }
}
