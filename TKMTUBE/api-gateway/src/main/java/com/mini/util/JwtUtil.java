package com.mini.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtUtil {


    public static final String SECRET = "a4c9d3c0b042bddb825c29cabe65c7080f42a75870a8b5069a7cf7bfb76b0a23";


    public void validateToken(final String token) {
        Jws<Claims> newWord =Jwts.parserBuilder().setSigningKey(getSignKey()).build().parseClaimsJws(token);
        System.out.println(newWord);
    }



    private Key getSignKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
