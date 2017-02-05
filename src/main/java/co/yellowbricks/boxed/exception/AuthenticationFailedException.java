package co.yellowbricks.boxed.exception;

import javax.ws.rs.core.Response;

import static java.net.HttpURLConnection.HTTP_UNAUTHORIZED;

public class AuthenticationFailedException extends BoxedException {

    @Override
    public Response toResponse() {
        return Response.status(HTTP_UNAUTHORIZED).build();
    }
}
