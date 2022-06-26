package ru.sharing;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ru.sharing.security.domain.User;
import ru.sharing.security.exception.LoginException;
import ru.sharing.security.mo.LoginRequestMo;
import ru.sharing.security.service.UserService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class LoginControllerTest {

	private final ObjectMapper mapper = new ObjectMapper();

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private UserService userService;


	@Test
	public void testGenerateToken_success() throws Exception {
		given(userService.getUserByLogin(anyString()))
				.willReturn(getDummyUser("Ulxa", "$2a$12$Tcb2gJaAYXak6ZUEx8EfjOm9VC9KFppV2em5Yp3H1cmsv760cnDMy"));

		mockMvc.perform(
				post("/auth/login")
						.content(getDummyRequest("Ulxa", "1234"))
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.token").isNotEmpty());
	}

	@Test
	public void testGenerateToken_userNotFound() throws Exception {
		given(userService.getUserByLogin(anyString()))
				.willThrow(new LoginException("Пользователя Ulxa не существует!", HttpStatus.NOT_FOUND));

		mockMvc.perform(
				post("/auth/login")
						.content(getDummyRequest("Ulxa", "1234"))
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isNotFound())
				.andExpect(jsonPath("$.message").isNotEmpty())
				.andExpect(jsonPath("$.statusCode").value(404))
				.andExpect(result -> assertTrue(result.getResolvedException() instanceof LoginException))
				.andExpect(result -> assertEquals("Пользователя Ulxa не существует!", result.getResolvedException().getMessage()));
	}

	@Test
	public void testGenerateToken_passwordNotValid() throws Exception {
		given(userService.getUserByLogin(anyString()))
				.willReturn(getDummyUser("Ulxa", "$2a$12$Tcb2gJaAYXak6ZUEx8EfjOm9VC9KFppV2em5Yp3H1cmsv760cnDMy"));

		mockMvc.perform(
				post("/auth/login")
						.content(getDummyRequest("Ulxa", "12345"))
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isBadRequest())
				.andExpect(jsonPath("$.message").isNotEmpty())
				.andExpect(jsonPath("$.statusCode").value(400))
				.andExpect(result -> assertTrue(result.getResolvedException() instanceof LoginException))
				.andExpect(result -> assertEquals("Неверный пароль!", result.getResolvedException().getMessage()));
	}

	private User getDummyUser(String login, String password) {
		final User user = new User();
		user.setLogin(login);
		user.setPassword(password);
		return user;
	}

	private String getDummyRequest(String login, String password) throws JsonProcessingException {
		final LoginRequestMo requestMo = new LoginRequestMo();
		requestMo.setLogin(login);
		requestMo.setPassword(password);
		return mapper.writeValueAsString(requestMo);
	}

}
