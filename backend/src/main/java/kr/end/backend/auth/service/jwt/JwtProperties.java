package kr.end.backend.auth.service.jwt;

import io.jsonwebtoken.security.Keys;
import javax.crypto.SecretKey;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JwtProperties {

  protected static final String TOKEN_TYPE = "type";

  private final String secretKey;
  private final long accessTokenExpirationMillis;
  private final long refreshTokenExpirationMillis;

  public JwtProperties(
      @Value("${jwt.secret-key}") String secretKey,
      @Value("${jwt.accessToken-expiration-millis}") long accessTokenExpirationMillis,
      @Value("${jwt.refreshToken-expiration-millis}") long refreshTokenExpirationMillis) {
    this.secretKey = secretKey;
    this.accessTokenExpirationMillis = accessTokenExpirationMillis;
    this.refreshTokenExpirationMillis = refreshTokenExpirationMillis;
  }

  public long getAccessTokenExpirationMillis() {
    return accessTokenExpirationMillis;
  }

  public long getRefreshTokenExpirationMillis() {
    return refreshTokenExpirationMillis;
  }

  public SecretKey getSecretKey() {
    return Keys.hmacShaKeyFor(secretKey.getBytes());
  }

}
