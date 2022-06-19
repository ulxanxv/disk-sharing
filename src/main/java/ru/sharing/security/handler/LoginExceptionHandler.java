package ru.sharing.security.handler;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.sharing.security.exception.LoginException;
import ru.sharing.mo.error.ApiError;

@RestControllerAdvice
public class LoginExceptionHandler {

	@ExceptionHandler(LoginException.class)
	public ResponseEntity<ApiError> handleException(LoginException e) {
		return ResponseEntity
				.status(e.getStatus())
				.body(ApiError.builder()
						.message(e.getLocalizedMessage())
						.status(e.getStatus())
						.statusCode(e.getStatus().value())
						.build());
	}

}
