package co.yellowbricks.boxed.storage;

import co.yellowbricks.boxed.domain.User;
import co.yellowbricks.boxed.storage.mapper.UserRowMapper;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

@RegisterMapper(UserRowMapper.class)
public interface UserDao {

    @SqlUpdate("INSERT INTO boxed_users (id, name, email, password, avatar_url, authentication_method, created_at, last_modified_at) " +
                       "VALUES (:id, :name, :email, :password, :avatar_url, :authentication_method, :created_at, :last_modified_at)")
    void insert(@Bind("id") String id, @Bind("name") String name, @Bind("email") String email, @Bind("password") String password,
                @Bind("avatar_url") String avatarUrl, @Bind("authentication_method") String authenticationMethod,
                @Bind("created_at") long createdAt, @Bind("last_modified_at") long lastModifiedAt);

    @SqlQuery("SELECT id, name, email, password, avatar_url, authentication_method, created_at, last_modified_at " +
                      "FROM boxed_users WHERE id = :id")
    User readById(@Bind("id") String userId);
}
