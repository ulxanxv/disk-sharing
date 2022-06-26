package ru.sharing.security.mo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class LoginRequestMo {

	@Schema(description = "User login")
	private String login;

	@Schema(description = "User password")
	private String password;

}
