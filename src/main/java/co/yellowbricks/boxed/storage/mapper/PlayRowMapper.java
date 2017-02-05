package co.yellowbricks.boxed.storage.mapper;

import co.yellowbricks.boxed.domain.Play;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PlayRowMapper implements ResultSetMapper<Play> {

    @Override
    public Play map(int index, ResultSet resultSet, StatementContext ctx) throws SQLException {
        return new Play(resultSet.getString("play_id"),
                        resultSet.getLong("bgg_game_id"),
                        resultSet.getString("user_id"));
    }
}
