package co.yellowbricks.boxed.di;

import co.yellowbricks.boxed.configuration.BoxedConfiguration;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.module.afterburner.AfterburnerModule;
import com.google.inject.AbstractModule;
import io.dropwizard.setup.Environment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.fasterxml.jackson.databind.DeserializationFeature.ADJUST_DATES_TO_CONTEXT_TIME_ZONE;
import static com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES;
import static com.fasterxml.jackson.databind.PropertyNamingStrategy.SNAKE_CASE;
import static com.fasterxml.jackson.databind.SerializationFeature.INDENT_OUTPUT;
import static com.fasterxml.jackson.databind.SerializationFeature.WRITE_DATES_AS_TIMESTAMPS;
import static com.fasterxml.jackson.databind.SerializationFeature.WRITE_NULL_MAP_VALUES;

public class BoxedModule extends AbstractModule {

    private static final Logger LOGGER = LoggerFactory.getLogger(BoxedModule.class);

    private final BoxedConfiguration configuration;
    private final Environment environment;

    public BoxedModule(BoxedConfiguration configuration,
                       Environment environment) {
        this.configuration = configuration;
        this.environment = environment;
    }

    public static ObjectMapper configureObjectMapper(ObjectMapper objectMapper) {
        objectMapper.registerModule(new Jdk8Module())
                    .registerModule(new JavaTimeModule())
                    .registerModule(new AfterburnerModule())
                    .setPropertyNamingStrategy(SNAKE_CASE)
                    .disable(ADJUST_DATES_TO_CONTEXT_TIME_ZONE)
                    .disable(FAIL_ON_UNKNOWN_PROPERTIES)
                    .disable(WRITE_DATES_AS_TIMESTAMPS)
                    .disable(WRITE_NULL_MAP_VALUES)
                    .enable(INDENT_OUTPUT);
        return objectMapper;
    }

    @Override
    protected void configure() {
        bind(BoxedConfiguration.class).toInstance(configuration);
        bind(Environment.class).toInstance(environment);
    }
}
