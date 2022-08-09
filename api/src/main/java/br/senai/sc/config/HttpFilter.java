package br.senai.sc.config;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.DependsOn;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
@DependsOn("serviceConfig")
public class HttpFilter implements Filter {

    public static final String HEADER_ZONE_ID = "X-ZoneId-Header";

    private static final String ALLOW_ORIGINS = "http://localhost:4200";

    @Override
    public void doFilter(final ServletRequest req, final ServletResponse resp, final FilterChain chain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;

        // You can change your IP and PORT address for CORs here.
        response.setHeader("Access-Control-Allow-Origin", ALLOW_ORIGINS);

        response.setHeader("Access-Control-Allow-Credentials", "true");

        if ("OPTIONS".equals(request.getMethod()) && ALLOW_ORIGINS.equals(request.getHeader("Origin"))) {
            response.setHeader("Access-Control-Allow-Methods", "POST, GET, DELETE, PUT, OPTIONS");
            response.setHeader("Access-Control-Max-Age", "3600");

            var allowHeaders = new String[] {"Authorization", "Content-Type", "Accept", HEADER_ZONE_ID};
            response.setHeader("Access-Control-Allow-Headers", String.join(", ", allowHeaders));

            response.setStatus(HttpServletResponse.SC_OK);
        } else {
            chain.doFilter(req, resp);
        }
    }

    @Override
    public void init(final FilterConfig filterConfig) {
    }


    @Override
    public void destroy() {
    }
}
