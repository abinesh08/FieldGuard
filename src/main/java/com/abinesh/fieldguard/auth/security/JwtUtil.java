package com.abinesh.fieldguard.auth.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtUtil {
    private final SecretKey SECRET= Keys.secretKeyFor(SignatureAlgorithm.HS256);

    public String generateToken(Authentication auth){
        return Jwts.builder()
                .setSubject(auth.getName())
                .claim("role", auth.getAuthorities().iterator().next().getAuthority())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 86400000))
                .signWith(SignatureAlgorithm.HS256,SECRET)
                .compact();
    }

    public String extractUsername(String token){
        return Jwts.parser().setSigningKey(SECRET).parseClaimsJwt(token).getBody()
                .getSubject();
    }

    public Boolean validateToken(String token){
        try{
            Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token);
                    return true;

        }catch (Exception e){
            return  false;
        }
    }
}
