package co.yellowbricks.boxed.service;

import co.yellowbricks.boxed.domain.Play;
import co.yellowbricks.boxed.exception.UnauthorizedOperationException;
import co.yellowbricks.boxed.storage.Storage;
import com.google.inject.Inject;
import com.google.inject.Singleton;

import java.util.List;
import java.util.UUID;

@Singleton
public class PlayService {

    private final Storage storage;

    @Inject
    public PlayService(Storage storage) {
        this.storage = storage;
    }

    public Play logPlay(Long bggGameId, String userId) {
        String playId = UUID.randomUUID().toString();

        storage.insertPlay(playId, bggGameId, userId);

        return new Play(playId, bggGameId, userId);
    }

    public void deletePlay(String playId, String userId) {
        Play play = storage.findPlayById(playId);

        if (!play.userId.equals(userId)) {
            throw new UnauthorizedOperationException();
        }

        storage.deletePlay(playId);
    }

    public List<Play> findAllPlaysForUser(String userId) {
        return storage.findAllPlaysForUser(userId);
    }
}
