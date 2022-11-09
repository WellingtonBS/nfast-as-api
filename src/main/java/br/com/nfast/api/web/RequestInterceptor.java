package br.com.nfast.api.web;

import br.com.nfast.api.config.auth.Bearer;
import br.com.nfast.api.utils.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RequestInterceptor implements HandlerInterceptor {
    private static final Logger log = LoggerFactory.getLogger(RequestInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("token");
        String clientId = request.getHeader("clientId");

        String path = request.getServletPath();
        if (Strings.containsAny(path, "/api/")) {
            //log.info(path);
            Bearer.validate(token, clientId);
        }

        TenantContext.set(clientId);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        //super.postHandle(request, response, handler, modelAndView);
        TenantContext.clear();
    }

}
