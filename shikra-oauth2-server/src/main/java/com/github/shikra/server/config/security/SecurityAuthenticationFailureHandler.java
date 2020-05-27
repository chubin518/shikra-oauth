package com.github.shikra.server.config.security;

import com.github.shikra.common.ResponseResult;
import org.springframework.security.authentication.*;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class SecurityAuthenticationFailureHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        String result;
        if (e instanceof UsernameNotFoundException || e instanceof BadCredentialsException) {
            result = e.getMessage();
        } else if (e instanceof LockedException) {
            result = "账户被锁定，请联系管理员!";
        } else if (e instanceof CredentialsExpiredException) {
            result = "证书过期，请联系管理员!";
        } else if (e instanceof AccountExpiredException) {
            result = "账户过期，请联系管理员!";
        } else if (e instanceof DisabledException) {
            result = "账户被禁用，请联系管理员!";
        } else {
            result = "登录失败!";
        }
        HttpResponseUtils.jsonWriter(httpServletResponse, ResponseResult.badrequest(result));
    }
}
