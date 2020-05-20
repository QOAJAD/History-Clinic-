package com.qoajad.backend.service.internal.jwt;

import io.jsonwebtoken.*;

import java.util.function.Function;

public interface JWTService {

    String getUsernameByClaims(final Claims claims);
    <T> T getClaimByClaims(final Claims claims, Function<Claims, T> claimsResolver);
    Claims getClaimsFromToken(String token) throws ExpiredJwtException, UnsupportedJwtException, MalformedJwtException, SignatureException, IllegalArgumentException;

    String generateJWT(final String username);
}
