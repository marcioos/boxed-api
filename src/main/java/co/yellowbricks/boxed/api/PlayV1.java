package co.yellowbricks.boxed.api;

import co.yellowbricks.boxed.domain.Play;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.dropwizard.jackson.JsonSnakeCase;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_ABSENT;

@JsonSnakeCase
@JsonInclude(NON_ABSENT)
public class PlayV1 {

    public final String playId;
    public final Long bggGameId;
    public final String userId;

    public PlayV1(String playId, Long bggGameId, String userId) {
        this.playId = playId;
        this.bggGameId = bggGameId;
        this.userId = userId;
    }

    public static PlayV1 fromDomain(Play play) {
        return new PlayV1(play.playId, play.bggGameId, play.userId);
    }
}
