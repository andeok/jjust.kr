package kr.end.backend.auth.service.jwt;

import io.jsonwebtoken.Jwts;
import java.util.Date;
import kr.end.backend.auth.dto.response.CustomUserDetails;
import kr.end.backend.auth.service.TokenType;
import kr.end.backend.member.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class JwtProvider {

  private final JwtProperties jwtProperties;

  public String createAccessToken(CustomUserDetails user) {
    long accessTokenExpirationMillis = jwtProperties.getAccessTokenExpirationMillis();
    return createToken(user, accessTokenExpirationMillis, TokenType.ACCESS_TOKEN);
  }

  public String createRefreshToken(CustomUserDetails user) {
    long refreshTokenExpirationMillis = jwtProperties.getRefreshTokenExpirationMillis();
    return createToken(user, refreshTokenExpirationMillis, TokenType.REFRESH_TOKEN);
  }

  private String createToken(CustomUserDetails user, long expirationMillis, TokenType tokenType) {
    Date now = new Date();
    Date expiredDate = new Date(now.getTime() + expirationMillis);

    return Jwts.builder()
        .setSubject(user.getId().toString())
        .setIssuedAt(now)
        .setExpiration(expiredDate)
        .claim(jwtProperties.TOKEN_TYPE, tokenType.name())
        .signWith(jwtProperties.getSecretKey())
        .compact();
  }


}
