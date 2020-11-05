package de.dlh.lhind.annualleave.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import de.dlh.lhind.annualleave.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.time.Instant;
import java.util.Calendar;
import java.util.Date;
import java.util.function.Function;

@Component
public class JwtHelper {
	private final RSAPrivateKey privateKey;
	private final RSAPublicKey publicKey;
	public JwtHelper(RSAPrivateKey privateKey, RSAPublicKey publicKey) {
		this.privateKey = privateKey;
		this.publicKey = publicKey;
	}
	
	public String createJwtForClaims(User user) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(Instant.now().toEpochMilli());
		calendar.add(Calendar.DATE, 1);
		return JWT
				.create()
				.withSubject(user.getUsername())
				.withClaim("id", user.getId())
				.withClaim("username", user.getUsername())
				.withArrayClaim("authorities", user
						.getAuthorities()
						.stream()
						.map((Function<GrantedAuthority, String>) GrantedAuthority::getAuthority).toArray(String[]::new))
				.withIssuedAt(new Date())
				.withNotBefore(new Date())
				.withExpiresAt(calendar.getTime())
				.sign(Algorithm.RSA256(publicKey, privateKey));
	}
}
