package co.yellowbricks.boxed.exception.mapper;

import co.yellowbricks.boxed.exception.BoxedException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class BoxedExceptionMapper implements ExceptionMapper<BoxedException> {

    @Override
    public Response toResponse(BoxedException exception) {
        return exception.toResponse();
    }
}
