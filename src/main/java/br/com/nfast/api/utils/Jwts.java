package br.com.nfast.api.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.time.LocalDateTime;

public class Jwts {
    private static final String issuer = "impulse";
    private static final Algorithm algorithm = Algorithm.HMAC256("mvbVzlYkqhD85OqO9ux2smHkDzVJFbW7");
    private static final JWTVerifier verifier = JWT.require(algorithm).withIssuer(issuer).build();

    public static String create(String... payload) {
        return create(null, payload);
    }

    public static String create(LocalDateTime expiresAt, String... payload) {
        JWTCreator.Builder builder = JWT.create().withIssuer(issuer);
        if (expiresAt != null)
            builder.withExpiresAt(Dates.toDate(expiresAt));

        int index = -1;
        String name = null;
        for (String item : payload) {
            index++;
            if ((index % 2) != 0)
                builder.withClaim(name, item);
            else name = item;
        }

        String token = builder.sign(algorithm);
        return token;
    }

    public static DecodedJWT decode(String token) {
        DecodedJWT jwt = verifier.verify(token);
        return jwt;
    }

    public static String claim(String token, String key) {
        return claim(token, key, null);
    }

    public static String claim(String token, String key, String def) {
        DecodedJWT jwt = decode(token);
        Claim item = jwt.getClaim(key);
        String value = item != null ? item.asString() : null;
        return Strings.ifEmpty(value, def);
    }

}
