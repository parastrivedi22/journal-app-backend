package com.parastrivedi.JournalApplication.config;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.SecretKey;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
//@Slf4j
public class JwtUtil {

	final private String SECRET_KEY = "PARAS!@##$%^^&";
	
	
	private SecretKey getSecretKey() {
		return Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
	}
	
	public String extractUsername(String token) {
		Claims claims = extractAllClaims(token);
		
		return claims.getSubject();
	}
	
	public Date extractExpiration(String token) {
		return extractAllClaims(token).getExpiration();
	}
	
	public Claims extractAllClaims(String token) {
		return Jwts.parser()
				.verifyWith(getSecretKey())
				.build()
				.parseSignedClaims(token)
				.getPayload();
	}
	
	
	private Boolean isTokenExpired(String token) {
		return extractExpiration(token).before(new Date());
	}
	
	public String generateToken(String username) {
		Map<String, Object> claims = new HashMap<>();
		return createToken(username, claims);
	}
	
	
	private String createToken(String username, Map<String, Object> claims) {
		return Jwts.builder()
				.claims(claims)
				.subject(username)
				.header().empty().add("typ", "jwt")
				.and()
				.issuedAt(new Date(System.currentTimeMillis()))
				.expiration(new Date(System.currentTimeMillis()+1000*60*5))
				.signWith(getSecretKey())
				.compact();
	}
	
	 public Boolean validateToken(String token) {
	        return !isTokenExpired(token);
	    }
}
