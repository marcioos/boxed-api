package co.yellowbricks.boxed.storage.dao;

import co.yellowbricks.boxed.domain.User;
import co.yellowbricks.boxed.storage.mapper.UserRowMapper;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

@RegisterMapper(UserRowMapper.class)
public interface UserDao {

    @SqlUpdate("INSERT INTO boxed_users (user_id, name, email, password, avatar_url, authentication_method, created_at, last_modified_at) " +
                       "VALUES (:user_id, :name, :email, :password, :avatar_url, :authentication_method, :created_at, :last_modified_at)")
    void insert(@Bind("user_id") String id, @Bind("name") String name, @Bind("email") String email, @Bind("password") String password,
                @Bind("avatar_url") String avatarUrl, @Bind("authentication_method") String authenticationMethod,
                @Bind("created_at") long createdAt, @Bind("last_modified_at") long lastModifiedAt);

    @SqlQuery("SELECT user_id, name, email, password, avatar_url, authentication_method, created_at, last_modified_at " +
                      "FROM boxed_users WHERE user_id = :user_id")
    User findById(@Bind("user_id") String userId);

    @SqlQuery("SELECT user_id, name, email, password, avatar_url, authentication_method, created_at, last_modified_at " +
                      "FROM boxed_users WHERE email = :email")
    User findByEmail(@Bind("email") String email);
}
