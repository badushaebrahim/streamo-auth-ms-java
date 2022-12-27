package streamo.server.auth.bootstrap.util;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import streamo.server.auth.bootstrap.model.entity.AuthEntity;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

@Slf4j
@Service
public class JwtTokenUtil implements Serializable {



    @Value("${token.secret}")
    private String secret;
    private String message = "login success";
    public Map<String, String> generateToken(AuthEntity user) {
        String jwtToken="";
//        jwtToken = Jwts.builder().setSubject(user.getUserName()).setIssuedAt(new Date()).signWith(SignatureAlgorithm.HS256, "secret").compact();
        jwtToken =Jwts.builder().setSubject(user.getId())).setExpiration(new Date(System.currentTimeMillis() + TimeUnit.MINUTES.toMillis(5))).setIssuedAt(new Date()).signWith(SignatureAlgorithm.HS256,"secret").compact();
        Map<String, String> jwtTokenGen = new HashMap<>();
        jwtTokenGen.put("token", jwtToken);
        jwtTokenGen.put("message", message);
        return jwtTokenGen;
    }

    public Map<String,String > readToken (String token){
        Map<String,String > resp = new HashMap<>();
        Date test = Jwts.parser().setSigningKey("secret").parseClaimsJws(token).getBody().getExpiration();
        log.info(" hello {}" , test);
        if(Boolean.TRUE.equals(isTokenExpired(token))){
//        Date test = Jwts.parser().setSigningKey("secret").parseClaimsJws(token).getBody().getExpiration();
//       log.info(" hello {}" , test);
        Claims claims = Jwts.parser().setSigningKey("secret").parseClaimsJws(token).getBody();
        log.info(claims.getSubject());
        resp.put("test", claims.getSubject());

        }
        return  resp;

    }

//    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
//        final Claims claims = Jwts.parser().setSigningKey("secret").parseClaimsJws(token).getBody();
//        return claimsResolver.apply(claims);
//    }
//    public Date getExpirationDateFromToken(String token) {
//        return getClaimFromToken(token, Claims::getExpiration);
//    }
    public Boolean isTokenExpired(String token) {
        final Date expiration = Jwts.parser().setSigningKey("secret").parseClaimsJws(token).getBody().getExpiration();
        return expiration.before(new Date());
    }
}