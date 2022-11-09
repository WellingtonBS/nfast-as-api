package br.com.nfast.api.web.util;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ApiOriginFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletResponse resp = (HttpServletResponse) response;
        resp.addHeader("Access-Control-Allow-Headers", "Content-Type");
        resp.addHeader("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT");
        resp.addHeader("Access-Control-Allow-Origin", "*");

        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
    }

    @Override
    public void init(FilterConfig config) throws ServletException {
    }

}
