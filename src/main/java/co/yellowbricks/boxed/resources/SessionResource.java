package co.yellowbricks.boxed.resources;

import co.yellowbricks.boxed.api.UserV1;
import co.yellowbricks.boxed.domain.User;
import co.yellowbricks.boxed.service.SessionService;
import co.yellowbricks.boxed.session.RequiresSession;
import co.yellowbricks.boxed.session.SessionManager;
import com.google.inject.Inject;
import com.google.inject.Singleton;

import javax.validation.constraints.NotNull;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import static co.yellowbricks.boxed.session.SessionManager.SESSION_HEADER_NAME;
import static javax.ws.rs.core.MediaType.APPLICATION_FORM_URLENCODED;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static javax.ws.rs.core.Response.Status.NOT_IMPLEMENTED;

@Path("/session")
@Produces(APPLICATION_JSON)
@Singleton
public class SessionResource {

    private final SessionService sessionService;
    private final SessionManager sessionManager;

    @Inject
    public SessionResource(SessionService sessionService, SessionManager sessionManager) {
        this.sessionService = sessionService;
        this.sessionManager = sessionManager;
    }

    @POST
    @Path("/login")
    @Consumes(APPLICATION_FORM_URLENCODED)
    public Response login(@FormParam("email") @NotNull String email,
                          @FormParam("password") @NotNull String password) {
        sessionService.createSession(email, password);
        User loggedUser = sessionManager.getLoggedUser();
        String sessionToken = sessionManager.getSessionToken();

        return Response.ok(UserV1.fromDomain(loggedUser))
                       .header(SESSION_HEADER_NAME, sessionToken)
                       .build();
    }

    @POST
    @Path("/fbLogin")
    @Consumes(APPLICATION_FORM_URLENCODED)
    public Response fbLogin(@FormParam("fb_user_id") String fbUserId,
                            @FormParam("fb_access_token") String fbAccessToken) {
        return Response.status(NOT_IMPLEMENTED).build();
    }

    @DELETE
    @Path("/logout")
    @RequiresSession
    public Response logout() {
        sessionService.deleteSession();

        return Response.ok().header(SESSION_HEADER_NAME, null).build();
    }
}
