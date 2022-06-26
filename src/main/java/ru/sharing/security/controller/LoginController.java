package ru.sharing.security.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.sharing.mo.error.ApiError;
import ru.sharing.security.exception.LoginException;
import ru.sharing.security.mo.LoginRequestMo;
import ru.sharing.security.mo.LoginResponseMo;
import ru.sharing.security.service.LoginService;

@RestController
@RequestMapping("/auth")
public class LoginController {

	@Autowired
	private LoginService loginService;

	@Operation(summary = "Авторизация")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Успешная авторизация",
					content = {
							@Content(
									mediaType = "application/json",
									schema = @Schema(implementation = LoginResponseMo.class))
					}),
			@ApiResponse(responseCode = "400", description = "Неверный пароль",
					content = {
							@Content(
									mediaType = "application/json",
									schema = @Schema(implementation = ApiError.class))
					}),
			@ApiResponse(responseCode = "404", description = "Неверный пользователь",
					content = {
							@Content(
									mediaType = "application/json",
									schema = @Schema(implementation = ApiError.class))
					}),
	})
	@PostMapping("/login")
	public LoginResponseMo doLogin(@RequestBody LoginRequestMo requestMo) throws LoginException {
		return loginService.doLogin(requestMo.getLogin(), requestMo.getPassword());
	}

}
