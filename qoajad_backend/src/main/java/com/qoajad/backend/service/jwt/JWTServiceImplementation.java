package com.qoajad.backend.service.jwt;

import com.qoajad.backend.constants.AuthenticationConstants;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

@Service
@Qualifier("defaultJwtService")
public class JWTServiceImplementation implements JWTService {

    private static final long JWT_TOKEN_TIME_VALIDITY = TimeUnit.HOURS.toMillis(1);

    @Value("${security.jwt.secret}")
    private String secret;

    @Override
    public String getUsernameByClaims(Claims claims) {
        return getClaimByClaims(claims, Claims::getSubject);
    }

    @Override
    public <T> T getClaimByClaims(Claims claims, Function<Claims, T> claimsResolver) {
        return claimsResolver.apply(claims);
    }

    @Override
    public Claims getClaimsFromToken(String token) throws ExpiredJwtException, UnsupportedJwtException, MalformedJwtException, SignatureException, IllegalArgumentException {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
    }

    @Override
    public String generateJWT(String username) {
        final String content = Jwts.builder().setClaims(new HashMap<>()).setSubject(username).setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_TIME_VALIDITY))
                .signWith(SignatureAlgorithm.HS512, secret).compact();

        return content;
    }
}
