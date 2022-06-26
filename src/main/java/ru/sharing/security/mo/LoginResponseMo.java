package ru.sharing.security.mo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginResponseMo {

	@Schema(description = "Generated token")
	private String token;

}
