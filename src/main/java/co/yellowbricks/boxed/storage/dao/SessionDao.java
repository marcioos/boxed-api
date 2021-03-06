package co.yellowbricks.boxed.storage.dao;

import co.yellowbricks.boxed.domain.User;
import co.yellowbricks.boxed.storage.mapper.UserRowMapper;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.Mapper;

public interface SessionDao {

    @SqlUpdate("INSERT INTO boxed_sessions (session_token, user_id, created_at) VALUES (:session_token, :user_id, :created_at)")
    void insert(@Bind("session_token") String sessionToken, @Bind("user_id") String userId, @Bind("created_at") long createdAt);

    @Mapper(UserRowMapper.class)
    @SqlQuery("SELECT u.user_id, u.name, u.email, u.password, u.avatar_url, u.authentication_method, u.created_at, u.last_modified_at " +
              "FROM boxed_sessions s JOIN boxed_users u ON s.user_id = u.user_id " +
              "WHERE s.session_token = :session_token")
    User findLoggedUser(@Bind("session_token") String sessionToken);

    @SqlUpdate("DELETE FROM boxed_sessions WHERE session_token = :session_token")
    void deleteSession(@Bind("session_token") String sessionToken);
}
