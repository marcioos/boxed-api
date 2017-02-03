package co.yellowbricks.boxed.api;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.jackson.JsonSnakeCase;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_ABSENT;

@JsonSnakeCase
@JsonInclude(NON_ABSENT)
public class UserV1 {

    public final Long id;
    public final String userName;
    public final String email;

    public UserV1(Long id, String userName, String email) {
        this.id = id;
        this.userName = userName;
        this.email = email;
    }
}
