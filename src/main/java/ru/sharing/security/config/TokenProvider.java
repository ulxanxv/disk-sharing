package ru.sharing.security.config;

public interface TokenProvider {

	String generateToken(String login);

	boolean validateToken(String token);

	String getLoginFromToken(String token);

}
