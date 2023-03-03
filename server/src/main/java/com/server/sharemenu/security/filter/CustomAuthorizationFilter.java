package com.server.sharemenu.security.filter;

import com.server.sharemenu.security.JwtProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CustomAuthorizationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtProvider jwtProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        try {
            String requestStr = request.getServletPath();

            if (requestStr.equals("/")
                    || requestStr.equals("/api/login")
                    || requestStr.equals("/api/auth/register")
                    || requestStr.equals("/api/auth/verify")
                    || requestStr.endsWith(".css")
                    || requestStr.endsWith(".js")
                    || requestStr.endsWith(".png")
                    || requestStr.endsWith(".ico")
                    || requestStr.endsWith(".jpg")
                    || requestStr.endsWith(".ttf")
                    || requestStr.endsWith(".woff")) {
                filterChain.doFilter(request, response);
            }

        } catch (AccessDeniedException e) {
            System.out.println("Access denied!");
        }

        jwtProvider.validateToken(response, request, filterChain);
    }
}
