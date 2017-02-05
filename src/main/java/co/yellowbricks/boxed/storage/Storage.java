package co.yellowbricks.boxed.storage;

import co.yellowbricks.boxed.domain.User;
import co.yellowbricks.boxed.exception.DatabaseOperationException;
import co.yellowbricks.boxed.exception.EmailAlreadyRegisteredException;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import org.skife.jdbi.v2.DBI;
import org.skife.jdbi.v2.Handle;
import org.skife.jdbi.v2.exceptions.DBIException;
import org.skife.jdbi.v2.exceptions.UnableToExecuteStatementException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Instant;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;

import static java.util.Optional.ofNullable;

@Singleton
public class Storage {

    private static final Logger LOGGER = LoggerFactory.getLogger(Storage.class);

    private final DBI dbi;

    @Inject
    public Storage(DBI dbi) {
        this.dbi = dbi;
    }

    public void insertUser(String id,
                           String name,
                           String email,
                           String password,
                           String avatarUrlToInsert,
                           String authenticationMethod,
                           long createdAt,
                           long lastModifiedAt) {
        executeDatabaseOperation((handle) -> {
            UserDao userDao = handle.attach(UserDao.class);
            try {
                userDao.insert(id,
                               name,
                               email,
                               password,
                               avatarUrlToInsert,
                               authenticationMethod,
                               createdAt,
                               lastModifiedAt);
            } catch (UnableToExecuteStatementException e) {
                if (e.getMessage().contains("boxed_users_email_key")) {
                    throw new EmailAlreadyRegisteredException(e);
                }
                throw e;
            }
        });
    }

    public Optional<User> findUserById(String userId) {
        return executeDatabaseOperation((handle) -> {
            UserDao userDao = handle.attach(UserDao.class);
            return ofNullable(userDao.findById(userId));
        });
    }

    public Optional<User> findUserByEmail(String email) {
        return executeDatabaseOperation((handle) -> {
            UserDao userDao = handle.attach(UserDao.class);
            return ofNullable(userDao.findByEmail(email));
        });
    }

    public void storeSession(String sessionToken, String userId) {
        executeDatabaseOperation((handle) -> {
            SessionDao sessionDao = handle.attach(SessionDao.class);
            sessionDao.insert(sessionToken, userId, Instant.now().getEpochSecond());
        });
    }

    public Optional<User> findUserBySessionToken(String sessionToken) {
        return executeDatabaseOperation((handle) -> {
            SessionDao sessionDao = handle.attach(SessionDao.class);
            return ofNullable(sessionDao.findLoggedUser(sessionToken));
        });
    }

    public void deleteSession(String sessionToken) {
        executeDatabaseOperation((handle) -> {
            SessionDao sessionDao = handle.attach(SessionDao.class);
            sessionDao.deleteSession(sessionToken);
        });
    }

    private <T> T executeDatabaseOperation(Function<Handle, T> operation) {
        try (Handle handle = dbi.open()) {
            try {
                handle.begin();
                T result = operation.apply(handle);
                handle.commit();
                return result;
            } catch (Exception e) {
                handle.rollback();
                throw e;
            }
        } catch (DBIException e) {
            LOGGER.error("While executing database operation", e);
            throw new DatabaseOperationException(e);
        }
    }

    private void executeDatabaseOperation(Consumer<Handle> operation) {
        executeDatabaseOperation((handle) -> {
            operation.accept(handle);
            return null;
        });
    }
}
