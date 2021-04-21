package com.libraryreact.libraryspringboot.config;

import java.util.Date;

import com.libraryreact.libraryspringboot.service.UserDetailService;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtUtils {
  private String jwtSecret = "secretKey";
  private Integer jwtExpirationMS = 9000000;

  public String generateJwtToken(Authentication authentication) {
    UserDetailService userPrincipal = (UserDetailService) authentication.getPrincipal();

    return Jwts.builder().setSubject(userPrincipal.getUsername())
        // .setPayload(userPrincipal.getAuthorities().stream().toString())
        .setIssuedAt(new Date()).setExpiration(new Date(new Date().getTime() + jwtExpirationMS))
        .signWith(SignatureAlgorithm.HS512, jwtSecret).compact();
  }

  public String getUsernameFromToken(String token) {
    return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject();
  }

  public boolean validateToken(String authToken) {
    try {
      Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);

      return true;
    } catch (Exception e) {
      e.printStackTrace();
    }
    return false;
  }
}
