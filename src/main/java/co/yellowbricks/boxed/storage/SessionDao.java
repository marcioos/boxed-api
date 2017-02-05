package co.yellowbricks.boxed.storage;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;

public interface SessionDao {

    @SqlUpdate("INSERT INTO boxed_sessions (session_token, user_id, created_at) VALUES (:session_token, :user_id, :created_at)")
    void insert(@Bind("session_token") String sessionToken, @Bind("user_id") String userId, @Bind("created_at") long createdAt);
}
