package proiect.demo.web.ang_spring.Security.JWT;

import java.security.Key;
import java.util.Date;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Service
public class JWTService {

	private final String SECRET = "mysecretkeymysecretkeymysecretkey123456";
	
	public String generateToken(UserDetails user) {
		
		return Jwts.builder()
				.setSubject(user.getUsername())
				.claim("role", user.getAuthorities().iterator().next().getAuthority())
				.setIssuedAt(new Date())
				.setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60))
				.signWith(getSignKey(), SignatureAlgorithm.HS256)
				.compact();
		
	}
	
	public Key getSignKey() {
		return Keys.hmacShaKeyFor(SECRET.getBytes());
	}
	
	public String extractUsername(String token) {
		return extractClaims(token).getSubject();
	}
	
	public Claims extractClaims(String token) {
		
		return Jwts.parserBuilder()
				.setSigningKey(getSignKey())
				.build()
				.parseClaimsJws(token)
				.getBody();
		
	}
	
}
