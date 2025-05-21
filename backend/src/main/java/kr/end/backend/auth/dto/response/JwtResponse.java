package kr.end.backend.auth.dto.response;

public record JwtResponse(
    String accessToken,
    String refreshToken
) {

}
