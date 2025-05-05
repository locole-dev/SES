package vn.com.telsoft.websheet.api.wdf.model;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "password")
public class AuthenticationRequest {
    private String username;
    private String password;
}
