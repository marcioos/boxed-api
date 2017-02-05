package co.yellowbricks.boxed.exception;

import javax.ws.rs.core.Response;

import static javax.ws.rs.core.Response.Status.UNAUTHORIZED;

public class AuthenticationFailedException extends BoxedException {

    @Override
    public Response toResponse() {
        return Response.status(UNAUTHORIZED).build();
    }
}
