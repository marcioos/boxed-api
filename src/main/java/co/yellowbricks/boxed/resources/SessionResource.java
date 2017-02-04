package co.yellowbricks.boxed.resources;

import co.yellowbricks.boxed.api.UserV1;
import co.yellowbricks.boxed.service.UserService;
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

import java.util.Optional;

import static javax.ws.rs.core.MediaType.APPLICATION_FORM_URLENCODED;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Path("/session")
@Produces(APPLICATION_JSON)
@Singleton
public class SessionResource {

    private final UserService userService;

    @Inject
    public SessionResource(UserService userService) {
        this.userService = userService;
    }

    @POST
    @Path("/login")
    @Consumes(APPLICATION_FORM_URLENCODED)
    public Response login(@FormParam("email") @NotNull String email,
                          @FormParam("password") @NotNull String password) {
        UserV1 loggedUser = new UserV1("id", email.split("@")[0], Optional.empty());

        return Response.ok(loggedUser).build();
    }

    @POST
    @Path("/fbLogin")
    @Consumes(APPLICATION_FORM_URLENCODED)
    public Response fbLogin(@FormParam("fb_user_id") String fbUserId,
                            @FormParam("fb_access_token") String fbAccessToken) {
        return Response.ok().build();
    }

    @DELETE
    @Path("/logout")
    public Response logout() {
        return Response.ok().build();
    }
}
