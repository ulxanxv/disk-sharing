package ru.sharing.mo.error;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@Builder
public class ApiError {

	private String message;

	private HttpStatus status;
	private int statusCode;

}
