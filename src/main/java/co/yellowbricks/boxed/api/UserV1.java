package co.yellowbricks.boxed.api;

import co.yellowbricks.boxed.domain.User;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.jackson.JsonSnakeCase;

import java.util.Optional;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_ABSENT;
import static java.util.Optional.ofNullable;

@JsonSnakeCase
@JsonInclude(NON_ABSENT)
public class UserV1 {

    public final String id;
    public final String name;
    public final Optional<String> avatarUrl;

    public UserV1(@JsonProperty("id") String id,
                  @JsonProperty("name") String name,
                  @JsonProperty("avatar_url") Optional<String> avatarUrl) {
        this.id = id;
        this.name = name;
        this.avatarUrl = avatarUrl;
    }

    public static UserV1 fromDomain(User user) {
        return new UserV1(user.id, user.name, ofNullable(user.avatarUrl));
    }
}
