package com.ssafy.findyourhome.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssafy.findyourhome.dto.user.LoginReq;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.util.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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
        LoginReq req = objectMapper.readValue(request.getReader(), LoginReq.class);
        if (StringUtils.isEmpty(req.getUsername()) || StringUtils.isEmpty(req.getPassword())) {
            throw new IllegalStateException("Username or password is empty");
        }
        return null;
    }

    private boolean isAjax(HttpServletRequest request) {
        return "XMLHttpRequest".equals(request.getHeader("X-Requested-With"));
    }
}
