package co.yellowbricks.boxed.exception;

import co.yellowbricks.boxed.api.ErrorV1;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static javax.ws.rs.core.Response.Status.BAD_REQUEST;

public class EmailAlreadyRegisteredException extends BoxedException {

    public EmailAlreadyRegisteredException(Exception cause) {
        super(cause);
    }

    public Response toResponse() {
        return Response.status(BAD_REQUEST)
                       .entity(ErrorV1.EMAIL_ALREADY_EXISTS)
                       .type(MediaType.APPLICATION_JSON)
                       .build();
    }
}
