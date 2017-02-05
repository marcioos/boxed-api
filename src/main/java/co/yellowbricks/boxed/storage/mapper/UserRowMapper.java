package co.yellowbricks.boxed.storage.mapper;

import co.yellowbricks.boxed.domain.AuthenticationMethod;
import co.yellowbricks.boxed.domain.User;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Instant;

import static java.util.Optional.ofNullable;

public class UserRowMapper implements ResultSetMapper<User> {

    @Override
    public User map(int index, ResultSet resultSet, StatementContext context) throws SQLException {
        return new User(resultSet.getString("user_id"),
                        resultSet.getString("name"),
                        resultSet.getString("email"),
                        ofNullable(resultSet.getString("password")),
                        resultSet.getString("avatar_url"),
                        AuthenticationMethod.of(resultSet.getString("authentication_method")),
                        Instant.ofEpochSecond(resultSet.getLong("created_at")),
                        Instant.ofEpochSecond(resultSet.getLong("last_modified_at")));
    }
}
