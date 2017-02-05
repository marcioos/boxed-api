package co.yellowbricks.boxed.service;

import co.yellowbricks.boxed.domain.User;
import co.yellowbricks.boxed.exception.AuthenticationFailedException;
import co.yellowbricks.boxed.session.SessionManager;
import co.yellowbricks.boxed.storage.Storage;
import com.google.inject.Inject;
import com.google.inject.Singleton;

import java.util.Optional;
import java.util.UUID;

@Singleton
public class SessionService {

    private final Storage storage;
    private final SessionManager sessionManager;

    @Inject
    public SessionService(Storage storage, SessionManager sessionManager) {
        this.storage = storage;
        this.sessionManager = sessionManager;
    }

    public void createSession(String email, String password) {
        User authenticatedUser = authenticate(email, password);

        createSessionForUser(authenticatedUser);
    }

    private void createSessionForUser(User user) {
        String sessionToken = UUID.randomUUID().toString();

        storage.storeSession(sessionToken, user.id);
        sessionManager.setLoggedUser(user, sessionToken);
    }

    private User authenticate(String email, String providedPassword) {
        Optional<User> user = storage.findUserByEmail(email);

        if (!user.isPresent()) {
            throw new AuthenticationFailedException();
        }
        if (passwordsMatch(user, providedPassword)) {
            return user.get();
        }
        throw new AuthenticationFailedException();
    }

    private boolean passwordsMatch(Optional<User> user, String providedPassword) {
        String encryptedPassword = UserService.encryptPassword(providedPassword);

        return user.get()
                .password
                .filter((storedPassword) -> storedPassword.equals(encryptedPassword))
                .isPresent();
    }
}
