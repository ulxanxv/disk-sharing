package ru.sharing.security.service;

import ru.sharing.security.mo.LoginResponseMo;

public interface LoginService {

	LoginResponseMo doLogin(String login, String password);

}
