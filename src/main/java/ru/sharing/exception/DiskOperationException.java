package ru.sharing.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class DiskOperationException extends RuntimeException {

	private final HttpStatus status;

	public DiskOperationException(String message, HttpStatus status) {
		super(message);
		this.status = status;
	}

}
