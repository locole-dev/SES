/*package vn.com.telsoft.websheet.api.filter;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import vn.com.telsoft.websheet.api.service.TokenAuthenticationService;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;

public class JWTLoginFilter extends AbstractAuthenticationProcessingFilter {

    public JWTLoginFilter(String url, AuthenticationManager authManager) {
        super(new AntPathRequestMatcher(url));
        setAuthenticationManager(authManager);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException, IOException, ServletException {
        if (request.getMethod().equals("POST")) {
            String username = request.getParameter("username");
            String password = request.getParameter("password");

            System.out.printf("JWTLoginFilter.attemptAuthentication: username/password= %s,%s", username, password);

            return getAuthenticationManager()
                    .authenticate(new UsernamePasswordAuthenticationToken(username, password, Collections.emptyList()));
        }
        return null;
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {

        System.out.println("JWTLoginFilter.successfulAuthentication:");

        // Write Authorization to Headers of Response.
        TokenAuthenticationService.addAuthentication(response, authResult.getName());

        String authorizationString = response.getHeader("Authorization");

        System.out.println("Authorization String=" + authorizationString);
    }

}*/
