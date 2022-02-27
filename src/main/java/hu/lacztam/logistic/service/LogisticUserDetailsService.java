package hu.lacztam.logistic.service;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;

import hu.lacztam.logistic.model.LogisticAppUser;

@Service
public class LogisticUserDetailsService implements UserDetailsService {
	@Autowired AuthenticationManagerBuilder auth;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserDetails user = null;
		
		UserDetailsManager userDetailsService;
		try {
			userDetailsService = auth.inMemoryAuthentication().getUserDetailsService();
			user = userDetailsService.loadUserByUsername(username);
		} catch (Exception e) {
			e.printStackTrace();
			throw new UsernameNotFoundException(username);
		}
		
		return new LogisticAppUser(user.getUsername(), user.getPassword() ,Arrays.asList(new SimpleGrantedAuthority("USER")), user);
	}

}
