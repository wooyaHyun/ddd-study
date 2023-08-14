package com.example.dddstudy.config.security;
/*
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.DeferredSecurityContext;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.context.HttpRequestResponseHolder;
import org.springframework.security.web.context.SecurityContextRepository;

import java.net.URLDecoder;
import java.util.function.Supplier;

import static com.example.dddstudy.config.security.SecurityConfig.AUTHCOOKIENAME;


@Slf4j
@RequiredArgsConstructor
public class CookieSecurityContextRepository implements SecurityContextRepository {//

    private final UserDetailsService userDetailsService;

    @Override
    public SecurityContext loadContext(HttpRequestResponseHolder requestResponseHolder) {
        return null;
    }

    @Override
    public DeferredSecurityContext loadDeferredContext(HttpServletRequest request) {
        SecurityContext sc = SecurityContextHolder.createEmptyContext();
        Cookie cookie = findAuthCookie(request);
        if (cookie != null) {
            String id = getUserId(cookie);
            if (id != null) {
                populateAuthentication(sc, id);
            }
        }
        return (DeferredSecurityContext)sc;
    }

    private void populateAuthentication(SecurityContext sc, String id) {
        try {
            UserDetails userDetails = userDetailsService.loadUserByUsername(id);
            sc.setAuthentication(new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities()));
        } catch (UsernameNotFoundException e) {
            log.debug("user name not found: " + id, e);
        }
    }

    private String getUserId(Cookie cookie) {
        try {
            return URLDecoder.decode(cookie.getValue(), "utf-8");
        } catch (Exception ex) {
            return null;
        }
    }

    private Cookie findAuthCookie(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies == null || cookies.length == 0) return null;
        for (Cookie c : cookies) {
            if (c.getName().equals(AUTHCOOKIENAME)) {
                return c;
            }
        }
        return null;
    }



    @Override
    public void saveContext(SecurityContext context, HttpServletRequest request, HttpServletResponse response) {

    }

    @Override
    public boolean containsContext(HttpServletRequest request) {
        return false;
    }
}
*/
