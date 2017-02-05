package co.yellowbricks.boxed.domain;

public class Play {

    public final String playId;
    public final Long bggGameId;
    public final String userId;

    public Play(String playId, Long bggGameId, String userId) {
        this.playId = playId;
        this.bggGameId = bggGameId;
        this.userId = userId;
    }
}
