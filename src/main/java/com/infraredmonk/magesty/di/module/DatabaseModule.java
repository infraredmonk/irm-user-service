package com.infraredmonk.magesty.di.module;

import com.infraredmonk.magesty.IrmUserServiceConfiguration;
import com.infraredmonk.magesty.jdbi3.IrmUserDao;
import dagger.Module;
import dagger.Provides;
import io.dropwizard.jdbi3.JdbiFactory;
import io.dropwizard.setup.Environment;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.sqlobject.SqlObjectPlugin;

@Module
public class DatabaseModule {

    private final Jdbi jdbi;

    public DatabaseModule(Environment environment, IrmUserServiceConfiguration configuration) {
        final JdbiFactory jdbiFactory = new JdbiFactory();
        jdbi = jdbiFactory
                .build(environment, configuration.getDatabase(), "postgresql")
                .installPlugin(new SqlObjectPlugin());
    }

    @Provides
    public IrmUserDao provideIrmUserDao() {
        return jdbi.onDemand(IrmUserDao.class);
    }
}
