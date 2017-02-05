package co.yellowbricks.boxed.session;

import co.yellowbricks.boxed.domain.User;
import com.google.inject.Singleton;

@Singleton
public class SessionManager {

    public static final String SESSION_HEADER_NAME = "Boxed-Session";

    private ThreadLocal<String> sessionToken = new ThreadLocal<>();
    private ThreadLocal<User> loggedUser = new ThreadLocal<>();

    public User getLoggedUser() {
        return loggedUser.get();
    }

    public String getSessionToken() {
        return sessionToken.get();
    }

    public void setLoggedUser(User user, String sessionToken) {
        this.sessionToken.set(sessionToken);
        this.loggedUser.set(user);
    }
}
