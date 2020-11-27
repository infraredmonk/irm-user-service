package com.infraredmonk.magesty;

import com.infraredmonk.magesty.resources.UserRegistrationResource;
import io.dropwizard.Application;
import io.dropwizard.setup.Environment;

public class IrmUserServiceApplication extends Application<IrmUserServiceConfiguration> {

    @Override
    public void run(IrmUserServiceConfiguration configuration, Environment environment) {
        environment.jersey().register(UserRegistrationResource.class);
    }

    public static void main(String[] args) throws Exception {
        new IrmUserServiceApplication().run(args);
    }
}
