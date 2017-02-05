package co.yellowbricks.boxed.storage.dao;

import co.yellowbricks.boxed.domain.Play;
import co.yellowbricks.boxed.storage.mapper.PlayRowMapper;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

import java.util.List;

@RegisterMapper(PlayRowMapper.class)
public interface PlayDao {

    @SqlUpdate("INSERT INTO user_plays (play_id, bgg_game_id, user_id, created_at) " +
               "VALUES (:play_id, :bgg_game_id, :user_id, :created_at)")
    void insert(@Bind("play_id") String playId,
                @Bind("bgg_game_id") Long bggGameId,
                @Bind("user_id") String userId,
                @Bind("created_at") long createdAt);

    @SqlQuery("SELECT play_id, bgg_game_id, user_id FROM user_plays WHERE play_id = :play_id")
    Play findById(@Bind("play_id") String playId);

    @SqlUpdate("DELETE FROM user_plays WHERE play_id = :play_id")
    void delete(@Bind("play_id") String playId);

    @SqlQuery("SELECT play_id, bgg_game_id, user_id FROM user_plays WHERE user_id = :user_id ORDER BY created_at DESC")
    List<Play> findByUserId(@Bind("user_id") String userId);
}
