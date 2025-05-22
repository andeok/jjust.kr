package kr.end.backend.auth.config;

import jakarta.servlet.http.HttpServletRequest;
import kr.end.backend.auth.controller.cookie.CookieResolver;
import kr.end.backend.auth.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

@RequiredArgsConstructor
@Component
public class AuthRequiredPrincipalArgumentResolver implements HandlerMethodArgumentResolver {

  private final CookieResolver cookieResolver;
  private final AuthService authService;

  @Override
  public boolean supportsParameter(MethodParameter parameter) {
    return parameter.hasParameterAnnotation(AuthRequiredPrincipal.class);
  }

  @Override
  public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
      NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {

    HttpServletRequest request = (HttpServletRequest) webRequest.getNativeRequest();

    cookieResolver.checkLoginRequired(request);
    String token = cookieResolver.extractAccessToken(request);

    return authService.getAuthUser(token);
  }


}
