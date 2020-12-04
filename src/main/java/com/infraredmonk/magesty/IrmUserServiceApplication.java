package com.infraredmonk.magesty;

import com.infraredmonk.magesty.jdbi3.IrmUserDao;
import com.infraredmonk.magesty.resources.PingResource;
import com.infraredmonk.magesty.resources.UserRegistrationResource;
import io.dropwizard.Application;
import io.dropwizard.jdbi3.JdbiFactory;
import io.dropwizard.setup.Environment;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.sqlobject.SqlObjectPlugin;

public class IrmUserServiceApplication extends Application<IrmUserServiceConfiguration> {

    @Override
    public void run(IrmUserServiceConfiguration configuration, Environment environment) {
        final JdbiFactory jdbiFactory = new JdbiFactory();
        final Jdbi jdbi = jdbiFactory
                .build(environment, configuration.getDatabase(), "postgresql")
                .installPlugin(new SqlObjectPlugin());
        environment.jersey().register(new UserRegistrationResource(jdbi.onDemand(IrmUserDao.class)));
        environment.jersey().register(PingResource.class);
    }

    public static void main(String[] args) throws Exception {
        new IrmUserServiceApplication().run(args);
    }
}
