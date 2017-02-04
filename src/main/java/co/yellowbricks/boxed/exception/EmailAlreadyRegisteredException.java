package co.yellowbricks.boxed.exception;

import co.yellowbricks.boxed.error.BoxedError;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static java.net.HttpURLConnection.HTTP_BAD_REQUEST;

public class EmailAlreadyRegisteredException extends BoxedException {

    public EmailAlreadyRegisteredException(Exception cause) {
        super(cause);
    }

    public Response toResponse() {
        return Response.status(HTTP_BAD_REQUEST)
                       .entity(BoxedError.EMAIL_ALREADY_EXISTS)
                       .type(MediaType.APPLICATION_JSON)
                       .build();
    }
}
