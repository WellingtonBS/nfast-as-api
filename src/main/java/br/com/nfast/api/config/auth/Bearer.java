package br.com.nfast.api.config.auth;

import br.com.nfast.api.utils.Dates;
import br.com.nfast.api.utils.Jwts;
import br.com.nfast.api.utils.Strings;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;

public class Bearer {
    private static final Logger log = LoggerFactory.getLogger(Bearer.class);

    private String token;
    private String author;
    private String clientId;
    private LocalDateTime created;
    private LocalDateTime expires;

    public static Bearer create(String author, String clientId) {
        Bearer bearer = new Bearer();
        bearer.setAuthor(author);
        bearer.setClientId(clientId);
        bearer.setCreated(LocalDateTime.now());
        //bearer.setExpires(bearer.getCreated().plusMinutes(30));
        bearer.setExpires(bearer.getCreated().plusDays(90));
        bearer.setToken(Jwts.create(
                "author", author,
                "clientId", bearer.getClientId(),
                "created", Dates.format(bearer.getCreated()),
                "expires", Dates.format(bearer.getExpires())
        ));

        return bearer;
    }

    public static Bearer decode(String token) {
        if (Strings.isEmpty(token))
            throw new RuntimeException("Token not provided");

        DecodedJWT jwt;
        try {
            jwt = Jwts.decode(token);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new RuntimeException("Invalid token");
        }

        Claim author = jwt.getClaim("author");
        Claim clientId = jwt.getClaim("clientId");
        Claim created = jwt.getClaim("created");
        Claim expires = jwt.getClaim("expires");

        Bearer bearer = new Bearer();
        bearer.setToken(token);
        bearer.setAuthor(author != null ? author.asString() : null);
        bearer.setClientId(clientId != null ? clientId.asString() : null);
        bearer.setCreated(created != null ? Dates.toDateTime(created.asString()) : null);
        bearer.setExpires(expires != null ? Dates.toDateTime(expires.asString()) : null);

        return bearer;
    }

    public static void validate(String token, String clientId) {
        Bearer bearer = decode(token);
        if (bearer.isExpired())
            throw new RuntimeException("Token expired at " + Dates.format(bearer.getExpires()));

        if (Strings.diff(clientId, bearer.getClientId()))
            throw new RuntimeException("Invalid token for this clientId");
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public LocalDateTime getExpires() {
        return expires;
    }

    public void setExpires(LocalDateTime expires) {
        this.expires = expires;
    }

    @JsonIgnore
    public boolean isExpired() {
        return (expires != null) && expires.isBefore(LocalDateTime.now());
    }

}
