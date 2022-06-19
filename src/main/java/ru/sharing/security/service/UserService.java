package ru.sharing.security.service;

import ru.sharing.security.domain.User;

public interface UserService {

	User getUserByLogin(String login);

	User getUser();

}
