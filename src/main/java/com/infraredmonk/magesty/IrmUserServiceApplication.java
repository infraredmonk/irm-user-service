package com.infraredmonk.magesty;

import com.infraredmonk.magesty.auth.ServiceAuthenticator;
import com.infraredmonk.magesty.auth.ServiceAuthorizer;
import com.infraredmonk.magesty.auth.ServiceTokenPrincipal;
import com.infraredmonk.magesty.di.AppComponent;
import com.infraredmonk.magesty.di.DaggerAppComponent;
import com.infraredmonk.magesty.di.module.DatabaseModule;
import com.infraredmonk.magesty.resources.PingResource;
import io.dropwizard.Application;
import io.dropwizard.auth.AuthDynamicFeature;
import io.dropwizard.auth.basic.BasicCredentialAuthFilter;
import io.dropwizard.jdbi3.bundles.JdbiExceptionsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.glassfish.jersey.server.filter.RolesAllowedDynamicFeature;

public class IrmUserServiceApplication extends Application<IrmUserServiceConfiguration> {

    @Override
    public void initialize(Bootstrap<IrmUserServiceConfiguration> bootstrap) {
        super.initialize(bootstrap);
        bootstrap.addBundle(new JdbiExceptionsBundle());
    }

    @Override
    public void run(IrmUserServiceConfiguration configuration, Environment environment) {
        AppComponent component = DaggerAppComponent.builder()
                .databaseModule(new DatabaseModule(environment, configuration))
                .build();

        BasicCredentialAuthFilter<ServiceTokenPrincipal> basicAuthFilter =
                new BasicCredentialAuthFilter.Builder<ServiceTokenPrincipal>()
                        .setAuthenticator(new ServiceAuthenticator(configuration.getIrmAuthConfig()))
                        .setAuthorizer(new ServiceAuthorizer())
                        .setRealm("magesty")
                        .buildAuthFilter();

        environment.jersey().register(new AuthDynamicFeature(basicAuthFilter));
        environment.jersey().register(RolesAllowedDynamicFeature.class);
        environment.jersey().register(component.getUserResource());
        environment.jersey().register(PingResource.class);
        new ServiceTokenPrincipal("test", null);
    }

    public static void main(String[] args) throws Exception {
        if (args.length > 0) {
            new IrmUserServiceApplication().run(args);
        } else {
            new IrmUserServiceApplication().run("server", "deploy/local/application.yaml");
        }
    }
}
