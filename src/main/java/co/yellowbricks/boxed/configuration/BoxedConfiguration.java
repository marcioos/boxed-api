package co.yellowbricks.boxed.configuration;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;
import io.dropwizard.db.DataSourceFactory;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public class BoxedConfiguration extends Configuration {

    @Valid
    @NotNull
    @JsonProperty
    public DataSourceFactory database;
}
