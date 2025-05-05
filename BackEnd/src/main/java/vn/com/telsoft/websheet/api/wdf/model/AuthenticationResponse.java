package vn.com.telsoft.websheet.api.wdf.model;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class AuthenticationResponse{
    private String accessToken;
    private String refreshToken;
    private String username;
}
