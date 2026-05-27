package proiect.demo.web.ang_spring.Security.JWT;

import java.security.Key;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import proiect.demo.web.ang_spring.Entities.User;

@Service
public class JWTService {

	@Value("${app.jwt.secret}")
	private String secret;
	
	public String generateToken(User user) {
		
		return Jwts.builder()
				.setSubject(user.getEmail())
				.claim("id", user.getId())
				.claim("firstName", user.getFirstName())
				.claim("role", user.getRole())
				.setIssuedAt(new Date())
				.setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60))
				.signWith(getSignKey())
				.compact();
		
	}
	
	public Key getSignKey() {
		byte[] keyBytes = Decoders.BASE64.decode(secret);
		return Keys.hmacShaKeyFor(keyBytes);
	}
	
	public String extractUsername(String token) {
		return extractClaims(token).getSubject();
	}
	
	
	public boolean isTokenValid(String token, UserDetails userDetails) {
	    final String username = extractUsername(token);
	    return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
	}

	private boolean isTokenExpired(String token) {
	    return extractClaims(token).getExpiration().before(new Date());
	}
	
	
	public Claims extractClaims(String token) {
		
		return Jwts.parserBuilder()
				.setSigningKey(getSignKey())
				.build()
				.parseClaimsJws(token)
				.getBody();
		
	}
	
}
