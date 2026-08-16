package com.library.libraryspringjpa.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.jspecify.annotations.NonNull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtUtils {

    @Value("${jwt.secret}") //pega o valor do .properties e coloca aqui na classe
    private String jwtSecret;

    @Value("${jwt.expirationMs}") //expiração do token
    private int jwtExpirationMs;

    private @NonNull SecretKey getSigningKey(){ //transforma a senha em uma chave hash que só o spring conhece
        return Keys.hmacShaKeyFor(jwtSecret.getBytes());
    }

    public String generateToken(@NonNull UserDetails userDetails){ //recebe um usuario
        return Jwts.builder()
                .subject(userDetails.getUsername()) //coloca o email do usuario dentro do payload do token
                .issuedAt(new Date()) //marca quando o token foi criado
                .expiration(new Date(System.currentTimeMillis() + jwtExpirationMs)) //marca quando o token vai expirar
                .signWith(getSigningKey()) //assina o token com a chave hash
                .compact();   //finaliza e devolve o token pronto
    }

    public String extractUsername(String token){ //pega o token e abre ele para ler o email
        return Jwts.parser()
                .verifyWith(getSigningKey())
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }

    public boolean isTokenExpired(String token){ //só compara a data de expiração com a data/hora atual
        Date expirationDate = Jwts.parser()
                .verifyWith(getSigningKey())
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getExpiration();
        return expirationDate.before(new Date());
    }

    public boolean isTokenValid(String token, UserDetails userDetails){ //pega o token e valida se ele bate com o email esperado e se não está expirado, se não ele lança uma expection
        try{
            String username = extractUsername(token);
            return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
        }catch(Exception e){
            return false;
        }
    }
}
