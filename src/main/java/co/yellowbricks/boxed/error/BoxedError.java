package co.yellowbricks.boxed.error;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.jackson.JsonSnakeCase;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_ABSENT;

@JsonSnakeCase
@JsonInclude(NON_ABSENT)
public class BoxedError {

    public static final BoxedError EMAIL_ALREADY_EXISTS = new BoxedError("email_already_exists");

    public final String error;

    public BoxedError(@JsonProperty("error") String error) {
        this.error = error;
    }
}
