package co.yellowbricks.boxed.exception;

import javax.ws.rs.core.Response;

public class DatabaseOperationException extends BoxedException {

    public DatabaseOperationException(Exception cause) {
        super(cause);
    }

    @Override
    public Response toResponse() {
        return Response.serverError().build();
    }
}
