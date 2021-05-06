package com.libraryreact.libraryspringboot.config;

import java.util.Date;

import com.libraryreact.libraryspringboot.models.entity.Users;
import com.libraryreact.libraryspringboot.repository.UsersRepository;
import com.libraryreact.libraryspringboot.service.UserDetailService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtUtils {
  @Autowired
  private UsersRepository usersRepository;
  private String jwtSecret = "secretKey";
  // private Integer jwtExpirationMS = 9000;
  private Integer jwtExpirationMS = 90000000;

  public String generateJwtToken(Authentication authentication) {

    UserDetailService userPrincipal = (UserDetailService) authentication.getPrincipal();
    Users user = usersRepository.findByUsername(userPrincipal.getUsername());
    return Jwts.builder().claim("username", userPrincipal.getUsername()).claim("id", user.getId())
        // .setPayload(userPrincipal.getAuthorities().stream().toString())
        .setIssuedAt(new Date()).setExpiration(new Date(new Date().getTime() + jwtExpirationMS))
        .signWith(SignatureAlgorithm.HS512, jwtSecret).compact();
  }

  public String getUsernameFromToken(String token) {
    return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().get("username", String.class);
  }

  public Integer getIdFromToken(String token) {
    return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().get("id", Integer.class);
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
