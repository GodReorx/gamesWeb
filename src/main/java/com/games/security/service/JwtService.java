package com.games.security.service;

import com.games.exceptions.ExcpPlayerNotFound;
import com.games.exceptions.ExcpPlayerNull;
import com.games.model.entity.Player;
import com.games.model.repository.PlayerRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

@Service

public class JwtService {

    @Autowired
    PlayerRepository playerRepository;

    private static final String SECRET_KEY = "18D85BD7369C09B7F7F403EABC33B71BC0B938CAEE9E6E6C507290D06CF6DF7C";
    private static final long TIMEEXPIRATION = 172800000;

    public String generateToken(UserDetails userDetails){
        return generateToken(new HashMap<>(), userDetails);
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
    public String getPlayer(String token){
        return getClaim(token, Claims::getSubject);
    }

    public <T> T getClaim(String token, Function<Claims,T> claimsResolver){
        final Claims claims = getAllClaims(token);
        return claimsResolver.apply(claims);
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

    public boolean checkUser(String token, Integer id){
        String user = extractUser(token);
        Optional<Player> playerDB = playerRepository.findById(id);
        if (playerDB.isPresent()) {
            return user.equals(playerDB.get().getEmail());
        } else {
            throw new ExcpPlayerNotFound(id);
        }
    }
    public Player returnPlayer(String token){
        String user = extractUser(token);
        Optional<Player> playerDB = playerRepository.findUserByEmail(user);
        if(playerDB.isPresent()){
            return playerDB.get();
        } else {
            throw new ExcpPlayerNull();
        }
    }
    private String extractUser(String token){
        String jwtToken = token.substring(7);
        Claims claims = Jwts.parser().verifyWith(getSignInKey()).build().parseSignedClaims(jwtToken).getPayload();
        return claims.getSubject();
    }
}
