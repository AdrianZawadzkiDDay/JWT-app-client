package pl.bykowski.sza6homeworkclient;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

public class JWTGenerator {

    public static String getJwtToken (RSAPublicKey publicRsaKey, RSAPrivateKey privateRsaKey) {

    String token = JWT.create()
            .withClaim("name", "Jan")
            .withClaim("role", "ADMIN")
            .sign(Algorithm.RSA512(publicRsaKey,privateRsaKey));

        return "Bearer " + token;
    }
}
