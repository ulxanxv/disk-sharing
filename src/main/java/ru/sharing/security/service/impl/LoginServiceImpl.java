package ru.sharing.security.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.sharing.security.config.TokenProvider;
import ru.sharing.security.domain.User;
import ru.sharing.security.exception.LoginException;
import ru.sharing.security.mo.LoginResponseMo;
import ru.sharing.security.service.LoginService;
import ru.sharing.security.service.UserService;

@Service
public class LoginServiceImpl implements LoginService {

	private static final String PASSWORD_NOT_VALID_MESSAGE = "Неверный пароль!";

	@Autowired
	private UserService userService;
	@Autowired
	private TokenProvider tokenProvider;
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public LoginResponseMo doLogin(String login, String password) {
		final User user = userService.getUserByLogin(login);
		if (!passwordEncoder.matches(password, user.getPassword())) {
			throw new LoginException(PASSWORD_NOT_VALID_MESSAGE, HttpStatus.BAD_REQUEST);
		}

		return new LoginResponseMo(tokenProvider.generateToken(user.getLogin()));
	}

}
