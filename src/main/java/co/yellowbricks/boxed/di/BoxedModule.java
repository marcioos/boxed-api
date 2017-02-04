package co.yellowbricks.boxed.di;

import co.yellowbricks.boxed.configuration.BoxedConfiguration;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import io.dropwizard.jdbi.DBIFactory;
import io.dropwizard.setup.Environment;
import liquibase.Liquibase;
import liquibase.database.jvm.JdbcConnection;
import liquibase.exception.LiquibaseException;
import liquibase.resource.ClassLoaderResourceAccessor;
import org.skife.jdbi.v2.DBI;
import org.skife.jdbi.v2.Handle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BoxedModule extends AbstractModule {

    private static final Logger LOGGER = LoggerFactory.getLogger(BoxedModule.class);

    private static final String DATABASE_MIGRATIONS_FILE = "migrations.xml";

    private final BoxedConfiguration configuration;
    private final Environment environment;

    private DBI database;

    public BoxedModule(BoxedConfiguration configuration, Environment environment) {
        this.configuration = configuration;
        this.environment = environment;
    }

    @Override
    protected void configure() {
        bind(BoxedConfiguration.class).toInstance(configuration);
        bind(Environment.class).toInstance(environment);
        createDatabase();
        createSchema();
    }

    @Provides
    @Singleton
    public DBI provideDbi() {
        return database;
    }

    private void createDatabase() {
        database = new DBIFactory().build(environment, configuration.database, "postgresql");
    }

    private void createSchema() {
        LOGGER.info("Updating Boxed database schema using migrations.xml now...");
        Handle handle = database.open();
        JdbcConnection connection = new JdbcConnection(handle.getConnection());
        Liquibase liquibase;
        try {
            liquibase = new Liquibase(DATABASE_MIGRATIONS_FILE, new ClassLoaderResourceAccessor(), connection);
            liquibase.update(DATABASE_MIGRATIONS_FILE);
        } catch (LiquibaseException e) {
            LOGGER.error("Could not create or update schema in database", e);
            System.out.println("Failed to create or update schema in database");
            e.printStackTrace(System.out);
        } finally {
            handle.commit();
            handle.close();
        }
    }
}
