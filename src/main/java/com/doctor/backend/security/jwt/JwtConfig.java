package com.doctor.backend.security.jwt;

import com.google.common.net.HttpHeaders;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
@NoArgsConstructor
public class JwtConfig {

    private String secretKey = "securesecuresecuresecuresecuresecuresecuresecuresecuresecuresecure";
    private String tokenPrefix = "Bearer ";
    private Integer tokenExpirationAfterDays = 10;

    public String getAuthorizationHeader() {
        return HttpHeaders.AUTHORIZATION;
    }
}
