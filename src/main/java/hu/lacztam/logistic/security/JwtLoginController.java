package hu.lacztam.logistic.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import hu.lacztam.logistic.config.ConfigProperties;
import hu.lacztam.logistic.dto.LogisticAppUserDto;

@RestController
public class JwtLoginController {

	@Autowired
	AuthenticationManager authenticationManager;
	
	@Autowired
	JwtService jwtService;
	
	@Autowired
	ConfigProperties config;
	
	@PostMapping("/api/login")
	public String login(@RequestBody LogisticAppUserDto user) {
		Authentication authenticate = 
				authenticationManager.authenticate(
						new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
		return jwtService.createJwtToken((UserDetails)authenticate.getPrincipal());
	}
	
}
