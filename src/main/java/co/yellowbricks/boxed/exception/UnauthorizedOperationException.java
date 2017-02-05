package co.yellowbricks.boxed.exception;

import javax.ws.rs.core.Response;

import static javax.ws.rs.core.Response.Status.FORBIDDEN;

public class UnauthorizedOperationException extends BoxedException {

    @Override
    public Response toResponse() {
        return Response.status(FORBIDDEN).build();
    }
}
