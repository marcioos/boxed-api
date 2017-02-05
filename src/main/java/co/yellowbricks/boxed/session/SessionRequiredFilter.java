package co.yellowbricks.boxed.session;

import co.yellowbricks.boxed.domain.User;
import co.yellowbricks.boxed.storage.Storage;
import com.google.inject.Inject;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.util.Optional;

import static javax.ws.rs.core.Response.Status.UNAUTHORIZED;

@Provider
public class SessionRequiredFilter implements ContainerRequestFilter {

    private final Storage storage;
    private final SessionManager sessionManager;

    @Inject
    public SessionRequiredFilter(Storage storage, SessionManager sessionManager) {
        this.storage = storage;
        this.sessionManager = sessionManager;
    }

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        String sessionToken = requestContext.getHeaderString(SessionManager.SESSION_HEADER_NAME);

        if (sessionToken == null) {
            throw new WebApplicationException(UNAUTHORIZED);
        }
        setLoggedUser(sessionToken);
    }

    private void setLoggedUser(String sessionToken) {
        Optional<User> loggedUser = storage.findUserBySessionToken(sessionToken);

        if (!loggedUser.isPresent()) {
            throw new WebApplicationException(UNAUTHORIZED);
        }
        sessionManager.setLoggedUser(loggedUser.get(), sessionToken);
    }
}
