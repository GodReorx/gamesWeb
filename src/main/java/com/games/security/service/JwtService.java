package com.games.security.service;

import com.games.model.entity.Player;
import com.games.model.repository.PlayerRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service

public class JwtService {

    @Autowired
    private PlayerRepository playerRepository;

    @Value("${jwt.secretKey}")
    private String SECRET_KEY;
    private static final long TIMEEXPIRATION = 86400000;

    public String generateToken(Player player){
        Map<String, Object> extraClaims = new HashMap<>();
        extraClaims.put("id", player.getId());
        return generateToken(extraClaims, player);
    }
    public String generateToken(Map<String, Object> extraClaims, UserDetails userDetails){
        return Jwts.builder()
                .claims(extraClaims)
                .subject(userDetails.getUsername())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + TIMEEXPIRATION))
                .signWith(getSignInKey())
                .compact();
    }


    public <T> T getClaim(String token, Function<Claims,T> claimsResolver){
        final Claims claims = getAllClaims(token);
        return claimsResolver.apply(claims);
    }
    public String getPlayer(String token){
        return getClaim(token, Claims::getSubject);
    }

    public Integer getPlayerId(String token){
        Claims claims = getAllClaims(token);
        return (Integer) claims.get("id");
    }

    private Claims getAllClaims(String token) {
            return Jwts
                    .parser()
                    .verifyWith(getSignInKey())
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();
    }

    private SecretKey getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public boolean validateToken(String token, UserDetails userDetails) {
        final String username = getPlayer(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    private boolean isTokenExpired(String token) {
        return getExpiration(token).before(new Date());
    }

    private Date getExpiration(String token) {
        return getClaim(token, Claims::getExpiration);
    }

}
