package ru.sharing.security.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.sharing.security.domain.User;
import ru.sharing.security.service.UserService;

@Slf4j
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	private static final String DEFAULT_ROLE = "DEFAULT";

	@Autowired
	private UserService userService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		final User user = userService.getUserByLogin(username);
		return org.springframework.security.core.userdetails.User.builder()
				.username(user.getLogin())
				.password(user.getPassword())
				.roles(DEFAULT_ROLE)
				.build();
	}

}
