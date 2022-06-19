package ru.sharing.handler;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.sharing.exception.DiskOperationException;
import ru.sharing.mo.error.ApiError;

@RestControllerAdvice
public class SharingExceptionHandler {

	@ExceptionHandler(DiskOperationException.class)
	public ResponseEntity<ApiError> handleException(DiskOperationException e) {
		return ResponseEntity
				.status(e.getStatus())
				.body(ApiError.builder()
						.message(e.getLocalizedMessage())
						.status(e.getStatus())
						.statusCode(e.getStatus().value())
						.build());
	}

}
