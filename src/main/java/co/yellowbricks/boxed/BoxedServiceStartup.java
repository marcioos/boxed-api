package co.yellowbricks.boxed;

import co.yellowbricks.boxed.configuration.BoxedConfiguration;
import co.yellowbricks.boxed.di.BoxedModule;
import co.yellowbricks.boxed.exception.mapper.BoxedExceptionMapper;
import co.yellowbricks.boxed.resources.PlayResource;
import co.yellowbricks.boxed.resources.SessionResource;
import co.yellowbricks.boxed.resources.UserResource;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.module.afterburner.AfterburnerModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import io.dropwizard.Application;
import io.dropwizard.db.PooledDataSourceFactory;
import io.dropwizard.migrations.MigrationsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

import static com.fasterxml.jackson.databind.DeserializationFeature.ADJUST_DATES_TO_CONTEXT_TIME_ZONE;
import static com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES;
import static com.fasterxml.jackson.databind.SerializationFeature.WRITE_NULL_MAP_VALUES;

public class BoxedServiceStartup extends Application<BoxedConfiguration> {

    @Override
    public void initialize(Bootstrap<BoxedConfiguration> bootstrap) {
        bootstrap.getObjectMapper().registerModule(new Jdk8Module())
                 .registerModule(new JavaTimeModule())
                 .registerModule(new AfterburnerModule())
                 .disable(ADJUST_DATES_TO_CONTEXT_TIME_ZONE)
                 .disable(FAIL_ON_UNKNOWN_PROPERTIES)
                 .disable(WRITE_NULL_MAP_VALUES);
        bootstrap.addBundle(new MigrationsBundle<BoxedConfiguration>() {
            @Override
            public PooledDataSourceFactory getDataSourceFactory(BoxedConfiguration configuration) {
                return configuration.database;
            }
        });
    }

    @Override
    public void run(BoxedConfiguration configuration, Environment environment) throws Exception {
        Injector injector = createInjector(configuration, environment);
        registerResources(environment, injector);
        registerExceptionMappers(environment);
    }

    private void registerResources(Environment environment, Injector injector) {
        environment.jersey().register(injector.getInstance(PlayResource.class));
        environment.jersey().register(injector.getInstance(SessionResource.class));
        environment.jersey().register(injector.getInstance(UserResource.class));
    }

    private void registerExceptionMappers(Environment environment) {
        environment.jersey().register(new BoxedExceptionMapper());
    }

    private Injector createInjector(BoxedConfiguration configuration, Environment environment) {
        BoxedModule module = new BoxedModule(configuration, environment);
        return Guice.createInjector(module);
    }

    public static void main(String[] args) throws Exception {
        new BoxedServiceStartup().run(args);
    }
}
