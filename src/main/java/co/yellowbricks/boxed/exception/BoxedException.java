package co.yellowbricks.boxed.exception;

import javax.ws.rs.core.Response;

public abstract class BoxedException extends RuntimeException {

    BoxedException() {
    }

    BoxedException(Exception cause) {
        super(cause);
    }

    public abstract Response toResponse();
}
