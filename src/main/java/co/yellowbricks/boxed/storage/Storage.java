package co.yellowbricks.boxed.storage;

import co.yellowbricks.boxed.domain.User;
import co.yellowbricks.boxed.exception.DatabaseOperationException;
import co.yellowbricks.boxed.exception.EmailAlreadyRegisteredException;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import org.skife.jdbi.v2.DBI;
import org.skife.jdbi.v2.Handle;
import org.skife.jdbi.v2.exceptions.UnableToExecuteStatementException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;

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
        try (Handle handle = dbi.open()) {
            startTransaction(handle);
            UserDao userDao = handle.attach(UserDao.class);
            userDao.insert(id, name, email, password, avatarUrlToInsert, authenticationMethod, createdAt, lastModifiedAt);
        } catch (UnableToExecuteStatementException e) {
            if (e.getMessage().contains("boxed_users_email_key")) {
                throw new EmailAlreadyRegisteredException(e);
            }
            LOGGER.error("While creating user", e);
            throw new DatabaseOperationException(e);
        }  catch (Exception e) {
            LOGGER.error("While creating user", e);
            throw new DatabaseOperationException(e);
        }
    }

    private void startTransaction(Handle handle) throws SQLException {
        handle.begin();
        handle.getConnection().setAutoCommit(true);
    }

    public User readUserById(String userId) {
        try (Handle handle = dbi.open()) {
            startTransaction(handle);
            UserDao userDao = handle.attach(UserDao.class);
            return userDao.readById(userId);
        } catch (Exception e) {
            LOGGER.error("While reading user", e);
            throw new DatabaseOperationException(e);
        }
    }
}
