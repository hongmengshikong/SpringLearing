package cn.edu.xcu.experiment_4.utils;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.UUID;

@Data
@Component
public class TokenManager {
    @Value("${token.expiration}")
    private Long expiration;
    @Value("${token.signkey}")
    private String signkey;
    private String getUUID(){
        String loginUserKey= UUID.randomUUID().toString();
        return loginUserKey;
    }

    public String createToken(String subject,Long ttlMillis){
        String uuid = getUUID();
        JwtBuilder builder=getJwtBuilder(subject,ttlMillis,uuid);
        return builder.compact();
    }
    public String createToken(String subject){
        String uuid = getUUID();
        JwtBuilder builder=getJwtBuilder(subject,null,uuid);
        return builder.compact();
    }

    private JwtBuilder getJwtBuilder(String subject, Long ttlMillis, String uuid) {
        SecretKey secretKey=generalKey();
        long nowMillis=System.currentTimeMillis();
        if (ttlMillis==null){
            ttlMillis=this.expiration;
        }
        long expMillis=nowMillis+ttlMillis;
        Date expDate=new Date(expMillis);

        return Jwts.builder()
                .id(uuid)
                .subject(subject)
                .issuer("xcu")
                .signWith(secretKey)
                .setExpiration(expDate);
    }

    private SecretKey generalKey() {
        byte[] encodeKey= Decoders.BASE64.decode(this.signkey);
        SecretKey key= Keys.hmacShaKeyFor(encodeKey);
        return key;
    }

    public String getUsernameFromToken(String jwt)throws RuntimeException{
        SecretKey secretKey=generalKey();

        return Jwts.parser()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(jwt)
                .getBody().getSubject();
    }
    public String getIdFromToken(String jwt)throws RuntimeException{
        SecretKey secretKey=generalKey();

        String id = Jwts.parser().verifyWith(secretKey)
                .build()
                .parseSignedClaims(jwt)
                .getPayload().getId();
        System.out.println(id);

        return Jwts.parser()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(jwt)
                .getBody().getId();
    }
    public String createObjToken(String key,Object obj,Long ttlMillis){
        SecretKey secretKey=generalKey();
        long nowMillis=System.currentTimeMillis();
        if (ttlMillis==null){
            ttlMillis=this.expiration;
        }
        long expMillis=nowMillis+ttlMillis;
        Date expDate=new Date(expMillis);
        return Jwts.builder()
                .signWith(secretKey)
                .claim(key,obj)
                .expiration(expDate)
                .compact();
    }
    public Object getObjectFromToken(String jwt,String key)throws ExpiredJwtException, SignatureException {
        SecretKey secretKey=generalKey();
        return Jwts.parser()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(jwt)
                .getBody().get(key);
    }
}