package com.ssafy.findyourhome.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssafy.findyourhome.dto.user.LoginReq;
import com.ssafy.findyourhome.security.AjaxAuthenticationToken;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.util.StringUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
public class AjaxLoginProcessingFilter extends AbstractAuthenticationProcessingFilter {

    private ObjectMapper objectMapper = new ObjectMapper();

//    public AjaxLoginProcessingFilter(String defaultFilterProcessesUrl) {
//        super(defaultFilterProcessesUrl);
//    }


    public AjaxLoginProcessingFilter() {
        super(new AntPathRequestMatcher("/api/user/login"));
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
        if (!isAjax(request)) {
            throw new IllegalStateException("Authentication is not supported");
        }
        if (!HttpMethod.POST.name().equals(request.getMethod())) {
            throw new IllegalStateException("Authentication is not supported");
        }
//        log.info("attemptAuthentication {}", request.getParameter("username"));
//        log.info("attemptAuthentication {}", objectMapper.readValue(request.getReader(), LoginReq.class).getUsername());

        LoginReq req = objectMapper.readValue(request.getReader(), LoginReq.class);
        if (StringUtils.isEmpty(req.getUsername()) || StringUtils.isEmpty(req.getPassword())) {
            throw new IllegalStateException("Username or password is empty");
        }
        AjaxAuthenticationToken ajaxAuthenticationToken = new AjaxAuthenticationToken(req.getUsername(), req.getPassword());

        return getAuthenticationManager().authenticate(ajaxAuthenticationToken);
    }

    private boolean isAjax(HttpServletRequest request) {
        return "XMLHttpRequest".equals(request.getHeader("X-Requested-With"));
    }
}
