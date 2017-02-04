package co.yellowbricks.boxed.api;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.jackson.JsonSnakeCase;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Optional;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_ABSENT;

@JsonSnakeCase
@JsonInclude(NON_ABSENT)
public class CreateUserRequest {

    @NotNull
    @Size(max = 255)
    public final String name;

    @NotNull
    @Size(max = 255)
    public final String email;

    @Size(max = 255)
    public final Optional<String> avatarUrl;

    @NotNull
    @Size(max = 255)
    public final String password;

    public CreateUserRequest(@JsonProperty("name") String name,
                             @JsonProperty("email") String email,
                             @JsonProperty("avatar_url") Optional<String> avatarUrl,
                             @JsonProperty("password") String password) {
        this.name = name;
        this.email = email;
        this.avatarUrl = avatarUrl;
        this.password = password;
    }
}
