package cjmp.desafio.foroHub.infra.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import cjmp.desafio.foroHub.domain.usuario.Usuario;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {
    @Value("${api.security.secret}")
    private String apiSecret;

    public String generarToken(Usuario usuario) {
        try{
            Algorithm algorithm = Algorithm.HMAC256(apiSecret);
            return JWT.create()
                    .withIssuer("foro Hub")
                    .withSubject(usuario.getEmail())
                    .withClaim("id", usuario.getId())
                    .withExpiresAt(generarFechaExpiracion())
                    .sign(algorithm);
        } catch (JWTCreationException exception) {
            throw new RuntimeException();
        }
    }

    public String getSubject(String token) {
        if (token == null) {
            throw new RuntimeException("Token nulo.");
        }
        try {
            Algorithm algorithm = Algorithm.HMAC256(apiSecret);
            DecodedJWT decodedJWT = JWT.require(algorithm)
                    .withIssuer("foro Hub")
                    .build()
                    .verify(token);
            String subject = decodedJWT.getSubject();
            if (subject == null) {
                throw new RuntimeException("Subject no encontrado en el token");
            }
            return  subject;
        } catch (JWTVerificationException exception) {
            throw new RuntimeException("token inválido: " + exception.getMessage());
        }
    }

    private Instant generarFechaExpiracion () {
        return LocalDateTime.now().plusHours(3).toInstant(ZoneOffset.of("-06:00"));
    }
}
