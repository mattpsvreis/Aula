package br.com.serratec.src.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.serratec.src.model.User;
import br.com.serratec.src.service.UserService;

@Service
public class AuthService implements UserDetailsService {

	@Autowired
	JWTUtil jwtUtil;

	@Autowired
	UserService userService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userService.findByUsername(username);

		return new UserSS(user.getId(), user.getUsername(), user.getPassword());

	}

	public UsernamePasswordAuthenticationToken getAuthorization(String token) {
		if (jwtUtil.isValid(token)) {
			String username = jwtUtil.getUsername(token);
			UserDetails user = loadUserByUsername(username);
			return new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
		}

		return null;
	}
}
