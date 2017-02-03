package co.yellowbricks.boxed;

import co.yellowbricks.boxed.configuration.BoxedConfiguration;
import co.yellowbricks.boxed.di.BoxedModule;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.google.inject.Guice;
import com.google.inject.Injector;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class BoxedServiceStartup extends Application<BoxedConfiguration> {

    @Override
    public void initialize(Bootstrap<BoxedConfiguration> bootstrap) {
        bootstrap.getObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    @Override
    public void run(BoxedConfiguration configuration, Environment environment) throws Exception {
        Injector injector = createInjector(configuration, environment);
        registerResources(environment, injector);
    }

    private void registerResources(Environment environment, Injector injector) {
        environment.jersey().register(injector.getInstance(co.yellowbricks.boxed.resources.ApiResource.class));
    }

    private Injector createInjector(BoxedConfiguration configuration, Environment environment) {
        BoxedModule module = new BoxedModule(configuration, environment);
        return Guice.createInjector(module);
    }

    /**
     * Start method.
     *
     * @param args command line arguments. Typically "server path_to_config_yml"
     * @throws Exception in case of any fatal problem.
     */
    public static void main(String[] args) throws Exception {
        new BoxedServiceStartup().run(args);
    }
}
