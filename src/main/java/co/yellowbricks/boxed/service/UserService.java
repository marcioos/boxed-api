package co.yellowbricks.boxed.service;

import co.yellowbricks.boxed.domain.AuthenticationMethod;
import co.yellowbricks.boxed.domain.User;
import co.yellowbricks.boxed.storage.Storage;
import com.google.inject.Inject;
import com.google.inject.Singleton;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Singleton
public class UserService {

    public static final String DEFAULT_AVATAR_URL = "https://s3.amazonaws.com/boxed-avatars/image-avatar-120.jpg";

    private final Storage storage;

    @Inject
    public UserService(Storage storage) {
        this.storage = storage;
    }


    public User createUser(String name,
                           String email,
                           Optional<String> password,
                           Optional<String> avatarUrl,
                           AuthenticationMethod authenticationMethod) {
        String id = UUID.randomUUID().toString();
        Instant now = Instant.now();
        String avatarUrlToInsert = avatarUrl.orElse(DEFAULT_AVATAR_URL);

        storage.insertUser(id,
                           name,
                           email,
                           password.orElse(null),
                           avatarUrlToInsert,
                           authenticationMethod.name(),
                           now.getEpochSecond(),
                           now.getEpochSecond());

        return new User(id, name, email, password, avatarUrlToInsert, authenticationMethod, now, now);
    }

    public User findUserById(String userId) {
        return storage.readUserById(userId);
    }
}
