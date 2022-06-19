package ru.sharing.security.config.impl;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.SignatureException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.sharing.security.config.TokenProvider;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

@Slf4j
@Component
public class TokenProviderImpl implements TokenProvider {

	@Value("${jwt.secret}")
	private String secret;

	@Value("${jwt.lifetime}")
	private long lifetime;

	@Override
	public String generateToken(String login) {
		final Instant now = LocalDate.now()
				.plusDays(lifetime)
				.atStartOfDay(ZoneId.systemDefault())
				.toInstant();

		final Date expirationDate = Date.from(now);
		return Jwts.builder()
				.setSubject(login)
				.setExpiration(expirationDate)
				.signWith(SignatureAlgorithm.HS512, secret) // TODO change this
				.compact();
	}

	@Override
	public boolean validateToken(String token) {
		try {
			getClaim(token); // no logic with object, just try parse
			return true;
		} catch (ExpiredJwtException | UnsupportedJwtException | MalformedJwtException | SignatureException e) {
			log.error(e.getLocalizedMessage());
		} catch (Exception e) {
			log.error("Unknown error: {}", e.getLocalizedMessage());
		}

		return false;
	}

	@Override
	public String getLoginFromToken(String token) {
		return getClaim(token).getSubject();
	}

	private Claims getClaim(String token) {
		return Jwts.parserBuilder()
				.setSigningKey(secret)
				.build()
				.parseClaimsJws(token)
				.getBody();
	}

}
