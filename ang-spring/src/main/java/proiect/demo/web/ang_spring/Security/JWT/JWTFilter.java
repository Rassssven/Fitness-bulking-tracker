package proiect.demo.web.ang_spring.Security.JWT;

import java.io.IOException;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import proiect.demo.web.ang_spring.db.UserRepository;

//@Component
public class JWTFilter extends OncePerRequestFilter {

	private final JWTService jwtService;
	private final UserDetailsService userDetailsService;

	public JWTFilter(JWTService jwtService, UserDetailsService userDetailsService) {
		this.jwtService = jwtService;
		this.userDetailsService = userDetailsService;
	}
	
	@Override
	protected void doFilterInternal(
	        HttpServletRequest request,
	        HttpServletResponse response,
	        FilterChain filterChain)
	        throws ServletException, IOException {

	    String authHeader = request.getHeader("Authorization");

	    if (authHeader == null || !authHeader.startsWith("Bearer ")) {
	        filterChain.doFilter(request, response);
	        return;
	    }

	    String token = authHeader.substring(7);
	    String username = jwtService.extractUsername(token);

	    if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

	        UserDetails user = userDetailsService.loadUserByUsername(username);

	        if(jwtService.isTokenValid(token, user)) {
	        	
		        UsernamePasswordAuthenticationToken authToken =
		                new UsernamePasswordAuthenticationToken(
		                        user,
		                        null,
		                        user.getAuthorities()
		                );
	
		        SecurityContextHolder.getContext().setAuthentication(authToken);
	        }
	    }

	    filterChain.doFilter(request, response);
	}
	
}
