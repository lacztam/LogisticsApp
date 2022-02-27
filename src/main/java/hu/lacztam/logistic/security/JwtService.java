package hu.lacztam.logistic.security;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;

import hu.lacztam.logistic.config.ConfigProperties;

@Service
public class JwtService {

	@Autowired ConfigProperties config;
	
	public String createJwtToken(UserDetails userDetails) {

		return JWT.create()
				.withSubject(userDetails.getUsername())
				.withArrayClaim(config.getJwtTokenProperties().getAuth(), userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority).toArray(String[]::new))
				.withExpiresAt(config.getJwtTokenProperties().getExpireTime())
				.withIssuer(config.getJwtTokenProperties().getIssuer())
				.sign(config.getJwtTokenProperties().getAlgorithm());
	}

	public UserDetails parseJwt(String jwToken) {
	
		DecodedJWT decodedJwt = JWT.require(config.getJwtTokenProperties().getAlgorithm())
			.withIssuer(config.getJwtTokenProperties().getIssuer())
			.build()
			.verify(jwToken);
		
		return new User(
				decodedJwt.getSubject(), 
				"dummy", 
				decodedJwt.getClaim(config.getJwtTokenProperties().getAuth()).asList(String.class)
				.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList())
				);
	}
	
}
