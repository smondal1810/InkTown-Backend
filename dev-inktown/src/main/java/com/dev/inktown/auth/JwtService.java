package com.dev.inktown.auth;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
@RequiredArgsConstructor

@Log
public class JwtService {

    private final String SIGNING_KEY="e2373a434589000fb5a215f059a6d907b481ad5906c7adc71d68bedd6899aa9";
    public String extractUserName(String token){
        return extractSingleClaim(token,Claims::getSubject);
    }

    public Boolean isTokenValid(String token, UserDetails userDetails){
        final String userName = extractUserName(token); //from token
        return userName.equals(userDetails.getUsername()) && !isTokenExpired(token);
    }

    public boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractSingleClaim(token, Claims::getExpiration);
    }

    /***
     * only generate token with userdetails
     * @param userDetails
     * @return String
     */
    public String generateToken(UserDetails userDetails){
        return generateToken(new HashMap<>(),userDetails);
    }

    /***
     *
     * @param extraClaims
     * @param userDetails
     * @return String
     */
    public String generateToken(Map<String,Object> extraClaims, UserDetails userDetails){
        log.severe("in generateToken");
        return Jwts
                .builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000*60*24))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }


    public <T> T extractSingleClaim(String token, Function<Claims,T> claimResolverFunction){
        final Claims allClaims = extractAllClaims(token);
        return claimResolverFunction.apply(allClaims); //based on function login it will filter out the claim from all the claims
    }
    private Claims extractAllClaims(String token){
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build().parseClaimsJws(token)
                .getBody();


    }

    private Key getSigningKey() {
        byte[] decode = Decoders.BASE64.decode(SIGNING_KEY);
        return Keys.hmacShaKeyFor(decode);
    }

}
