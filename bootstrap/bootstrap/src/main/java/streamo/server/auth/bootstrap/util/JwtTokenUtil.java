package streamo.server.auth.bootstrap.util;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import streamo.server.auth.bootstrap.configurations.properties.CustomProperties;
import streamo.server.auth.bootstrap.exceptions.TokenExpiredException;
import streamo.server.auth.bootstrap.model.entity.AuthEntity;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
public class JwtTokenUtil implements Serializable {
    private final String STATUS = "token generated";
    public Map<String, String> generateToken(AuthEntity authEntity, CustomProperties properties) {
        String jwtToken;
    jwtToken =
        Jwts.builder()
            .setSubject(authEntity.getId()).setIssuedAt(new Date())
            .setExpiration(new Date(System.currentTimeMillis() + TimeUnit.MINUTES.toMillis(20)))
            .signWith(SignatureAlgorithm.HS256, properties.getEncryptionKey())
            .compact();
        Map<String, String> jwtTokenGen = new HashMap<>();
        jwtTokenGen.put("jwt-token", jwtToken);
        jwtTokenGen.put("status", STATUS);
        return jwtTokenGen;
    }

    public String readToken (String token, CustomProperties properties){
        Claims claims = Jwts.parser().setSigningKey(properties.getEncryptionKey()).parseClaimsJws(token).getBody();
        log.info(" SUBJECT : {}" , claims.getSubject());
        final Date expirationAt = claims.getExpiration();
        log.info(" EXPIRATION-DATE : {}", expirationAt);
        final Date issuedAt = claims.getIssuedAt();
        log.info(" ISSUED-DATE : {}", issuedAt);
        if(Boolean.TRUE.equals(expirationAt.after(new Date()))){
            return claims.getSubject();
        }
        else{
            throw new TokenExpiredException();
        }


    }
}