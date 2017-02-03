package co.yellowbricks.boxed.resources;

import co.yellowbricks.boxed.api.UserV1;
import co.yellowbricks.boxed.service.UserService;
import com.google.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.constraints.NotNull;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import static javax.ws.rs.core.MediaType.APPLICATION_FORM_URLENCODED;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

//POST          /api/login                                                    controllers.API.login()
//GET           /api/logout                                                   controllers.API.logout()
//POST          /api/fbLogin                                                  controllers.API.fbLogin()
//POST          /api/playEntry                                                controllers.API.logPlay()
//GET           /api/user/$userId<[0-9]+>/friends                             controllers.API.friends(userId: Long)
//GET           /api/user/$userId<[0-9]+>/plays                               controllers.API.plays(userId: Long)
//GET           /api/user/$userId<[0-9]+>/games                               controllers.API.games(userId: Long)
//DELETE        /api/play/$playId<[0-9]+>                                     controllers.API.deletePlay(playId: Long)
@Path("/api")
@Produces(APPLICATION_JSON)
public class ApiResource {

    private static final Logger LOGGER = LoggerFactory.getLogger(ApiResource.class);

    private final UserService userService;

    @Inject
    public ApiResource(UserService userService) {
        this.userService = userService;
    }

    @POST
    @Path("/login")
    @Consumes(APPLICATION_FORM_URLENCODED)
    public Response login(@FormParam("email") @NotNull String email,
                          @FormParam("password") String password) {
        LOGGER.info("Login called with {} {}", email, password);
        UserV1 loggedUser = new UserV1(1L, email.split("@")[0], email);

        return Response.ok(loggedUser).build();
    }
}
