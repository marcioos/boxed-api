package co.yellowbricks.boxed.domain;

import java.time.Instant;
import java.util.Optional;

public class User {

    public final String userId;
    public final String name;
    public final String email;
    public final Optional<String> password;
    public final String avatarUrl;
    public final AuthenticationMethod authenticationMethod;
    public final Instant createdAt;
    public final Instant lastModifiedAt;

    public User(String userId,
                String name, 
                String email, 
                Optional<String> password,
                String avatarUrl, 
                AuthenticationMethod authenticationMethod,
                Instant createdAt, 
                Instant lastModifiedAt) {
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.password = password;
        this.avatarUrl = avatarUrl;
        this.authenticationMethod = authenticationMethod;
        this.createdAt = createdAt;
        this.lastModifiedAt = lastModifiedAt;
    }
}
