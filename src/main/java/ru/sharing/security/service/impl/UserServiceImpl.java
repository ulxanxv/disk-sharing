package ru.sharing.security.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import ru.sharing.security.domain.User;
import ru.sharing.security.exception.LoginException;
import ru.sharing.security.repository.UserRepository;
import ru.sharing.security.service.UserService;

@Service
public final class UserServiceImpl implements UserService {

	private static final String USER_NOT_FOUND_MESSAGE = "Пользователя %s не существует!";

	@Autowired
	private UserRepository userRepository;

	@Override
	public User getUserByLogin(String login) {
		return userRepository.findByLogin(login)
				.orElseThrow(() -> new LoginException(String.format(USER_NOT_FOUND_MESSAGE, login), HttpStatus.NOT_FOUND));
	}

	@Override
	public User getUser() {
		final UserDetails loggedUser = ((UserDetails) SecurityContextHolder.getContext()
				.getAuthentication()
				.getPrincipal());

		return getUserByLogin(loggedUser.getUsername());
	}


}
