package com.parastrivedi.JournalApplication.config;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

@Component
public class JwtUtil {
	
	// requirement :
		public static final long JWT_TOKEN_VALIDITY = 5 * 60 * 60;

		private String secret = "ASDFGHJKXCVBNMWERTYUIOqwertyuioplmnbvcxasdfghjqwerfvQWERFVBsdfhwertyuiiiiiQWSDFGHTGVdfghjkjnbtgbvbhhjnj";

		public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
			final Claims claims = getAllClaimsFromToken(token);
			return claimsResolver.apply(claims);
		}
	
		// retrieve username from jwt token
		public String getUsernameFromToken(String token) {
			return getClaimFromToken(token, Claims::getSubject);
		}

		// retrieve expiration date from jwt token
		public Date getExpirationDateFromToken(String token) {
			return getClaimFromToken(token, Claims::getExpiration);
		}

	

		private Claims getAllClaimsFromToken(String token) {
			return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
		}

		// check if the token has expired
		private Boolean isTokenExpired(String token) {
			final Date expiration = getExpirationDateFromToken(token);
			return expiration.before(new Date());
		}

		public String generateToken(UserDetails userDetails) {
			Map<String, Object> claims = new HashMap<>();
			return doGenerateToken(claims, userDetails.getUsername());
		}

		private String doGenerateToken(Map<String, Object> claims, String subject) {

			return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
					.setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000))
					.signWith(SignatureAlgorithm.HS512, secret).compact();
		}
		//validate token
	    public Boolean validateToken(String token, UserDetails userDetails) {
	        final String username = getUsernameFromToken(token);
	        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
	    }
/*
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
	
	
	public Boolean isTokenExpired(String token) {
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

*/
}
