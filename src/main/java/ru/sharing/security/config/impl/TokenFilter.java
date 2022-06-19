package ru.sharing.security.config.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.GenericFilterBean;
import ru.sharing.security.config.TokenProvider;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

@Component
public class TokenFilter extends GenericFilterBean {

	private static final String AUTHORIZATION_HEADER = "Authorization";

	@Autowired
	private TokenProvider tokenProvider;
	@Autowired
	private UserDetailsService userDetailsService;

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
		final String token = extractToken(((HttpServletRequest) servletRequest));
		if (token != null && tokenProvider.validateToken(token)) {
			final String login = tokenProvider.getLoginFromToken(token);
			final UserDetails userDetails = userDetailsService.loadUserByUsername(login);

			UsernamePasswordAuthenticationToken authenticationToken =
					new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

			SecurityContextHolder.getContext()
					.setAuthentication(authenticationToken);
		}

		filterChain.doFilter(servletRequest, servletResponse);
	}

	private String extractToken(HttpServletRequest request) {
		String bearer = request.getHeader(AUTHORIZATION_HEADER);
		if (StringUtils.hasText(bearer) && bearer.startsWith("Bearer ")) {
			return bearer.substring(7);
		}

		return null;
	}

}
