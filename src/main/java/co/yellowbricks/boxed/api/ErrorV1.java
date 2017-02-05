package co.yellowbricks.boxed.api;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.jackson.JsonSnakeCase;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_ABSENT;

@JsonSnakeCase
@JsonInclude(NON_ABSENT)
public class ErrorV1 {

    public static final ErrorV1 EMAIL_ALREADY_EXISTS = new ErrorV1("email_already_exists");

    public final String error;

    public ErrorV1(@JsonProperty("error") String error) {
        this.error = error;
    }
}
