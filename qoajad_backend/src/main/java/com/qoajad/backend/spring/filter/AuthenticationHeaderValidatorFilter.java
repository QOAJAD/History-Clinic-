package com.qoajad.backend.spring.filter;

import com.qoajad.backend.constants.AuthenticationConstants;
import com.qoajad.backend.service.jwt.JWTService;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.security.core.userdetails.User;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.Objects;

@Service
@Qualifier("defaultAuthenticationHeaderValidator")
public class AuthenticationHeaderValidatorFilter extends OncePerRequestFilter {

    private static final String [] VALIDATE_WHEN_URL_DOESNT_CONTAINS = new String [] {AuthenticationConstants.AUTHENTICATION_ROOT_URI};

    private final JWTService jwtService;

    @Autowired
    public AuthenticationHeaderValidatorFilter(@Qualifier("defaultJwtService") final JWTService jwtService) {
        this.jwtService = jwtService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        boolean isInvalidUrlForThisFilter = false;
        for (String url : VALIDATE_WHEN_URL_DOESNT_CONTAINS) {
            if (request.getRequestURI().contains(url)) {
                isInvalidUrlForThisFilter = true;
                break;
            }
        }

        if (!isInvalidUrlForThisFilter) {
            attemptToAuthenticate(request);
        }

        filterChain.doFilter(request, response);
    }

    private void attemptToAuthenticate(final HttpServletRequest request) {
        final String authenticatedJwt = findJWTInHeader(request);
        if (authenticatedJwt == null) {
            return;
        }

        UsernamePasswordAuthenticationToken authenticationToken;
        try {
            authenticationToken = buildAuthenticationToken(authenticatedJwt);
        } catch (ExpiredJwtException | UnsupportedJwtException | MalformedJwtException | SignatureException | IllegalArgumentException e) {
            e.printStackTrace();
            return;
        }

        authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
    }

    private String findJWTInHeader(final HttpServletRequest request) {
        String jwtContent = request.getHeader(AuthenticationConstants.AUTHENTICATION_COOKIE_NAME);
        if (jwtContent == null) {
            return null;
        }
        return jwtContent;
    }

    private UsernamePasswordAuthenticationToken buildAuthenticationToken(final String authenticatedJwt) throws ExpiredJwtException, UnsupportedJwtException, MalformedJwtException, SignatureException, IllegalArgumentException {
        Objects.requireNonNull(authenticatedJwt, "Authentication header must not be null in order to generate the authentication token.");
        final String jwtContent = authenticatedJwt;
        // This function may throw multiple exceptions that help us identify malformed JWts.
        final Claims claims = jwtService.getClaimsFromToken(jwtContent);
        final String username = jwtService.getUsernameByClaims(claims);
        final Collection<SimpleGrantedAuthority> authorities = Collections.emptyList();
        return new UsernamePasswordAuthenticationToken(new User(username, "", authorities), "", authorities);
    }
}
