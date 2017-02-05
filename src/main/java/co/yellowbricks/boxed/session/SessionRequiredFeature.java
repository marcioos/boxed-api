package co.yellowbricks.boxed.session;

import com.google.inject.Inject;

import javax.ws.rs.container.DynamicFeature;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.FeatureContext;
import javax.ws.rs.ext.Provider;

@Provider
public class SessionRequiredFeature implements DynamicFeature {

    private final SessionRequiredFilter sessionRequiredFilter;

    @Inject
    public SessionRequiredFeature(SessionRequiredFilter sessionRequiredFilter) {
        this.sessionRequiredFilter = sessionRequiredFilter;
    }

    @Override
    public void configure(ResourceInfo resourceInfo, FeatureContext context) {
        if (resourceInfo.getResourceMethod().getAnnotation(RequiresSession.class) != null) {
            context.register(sessionRequiredFilter);
        }
    }
}
