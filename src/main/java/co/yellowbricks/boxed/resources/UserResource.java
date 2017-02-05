package co.yellowbricks.boxed.resources;

import co.yellowbricks.boxed.api.CreateUserRequest;
import co.yellowbricks.boxed.api.UserV1;
import co.yellowbricks.boxed.domain.AuthenticationMethod;
import co.yellowbricks.boxed.domain.User;
import co.yellowbricks.boxed.service.UserService;
import com.google.inject.Inject;
import com.google.inject.Singleton;

import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import static java.util.Optional.of;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Path("/user")
@Produces(APPLICATION_JSON)
@Singleton
public class UserResource {

    private final UserService userService;

    @Inject
    public UserResource(UserService userService) {
        this.userService = userService;
    }

    @POST
    @Consumes(APPLICATION_JSON)
    public Response create(@Valid CreateUserRequest createUserRequest) {
        User user = userService.createUser(createUserRequest.name,
                                           createUserRequest.email,
                                           of(createUserRequest.password),
                                           createUserRequest.avatarUrl,
                                           AuthenticationMethod.DEFAULT);

        return Response.ok(UserV1.fromDomain(user)).build();
    }
}
