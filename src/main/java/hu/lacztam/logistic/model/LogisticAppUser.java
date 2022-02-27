package hu.lacztam.logistic.model;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

public class LogisticAppUser extends User{

	private String username;
	private String password;
	private UserDetails user;
	
	public LogisticAppUser(
			String username, 
			String password, 
			Collection<? extends GrantedAuthority> authorities,
			UserDetails user) {
		
		super(username, password, authorities);
		this.user = user;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public UserDetails getUser() {
		return user;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setUser(UserDetails user) {
		this.user = user;
	}

}
