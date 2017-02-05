package co.yellowbricks.boxed.service;

import co.yellowbricks.boxed.domain.Play;
import co.yellowbricks.boxed.storage.Storage;
import com.google.inject.Inject;
import com.google.inject.Singleton;

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
}
