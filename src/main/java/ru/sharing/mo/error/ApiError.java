package ru.sharing.mo.error;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@Builder
public class ApiError {

	@Schema(description = "Error message")
	private String message;

	@Schema(description = "Request status")
	private HttpStatus status;

	@Schema(description = "Request status code")
	private int statusCode;

}
