package co.yellowbricks.boxed.storage.dao;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;

public interface PlayDao {

    @SqlUpdate("INSERT INTO user_plays (play_id, bgg_game_id, user_id, created_at) " +
               "VALUES (:play_id, :bgg_game_id, :user_id, :created_at)")
    void insert(@Bind("play_id") String playId,
                @Bind("bgg_game_id") Long bggGameId,
                @Bind("user_id") String userId,
                @Bind("created_at") long createdAt);
}
