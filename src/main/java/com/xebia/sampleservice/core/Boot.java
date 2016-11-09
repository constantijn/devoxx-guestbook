package com.xebia.sampleservice.core;

import com.xebia.sampleservice.guestbook.GuestBookDao;
import com.xebia.sampleservice.guestbook.GuestBookResource;
import com.xebia.sampleservice.hello.GreetingHealthCheck;
import com.xebia.sampleservice.hello.HelloResource;
import io.dropwizard.Application;
import io.dropwizard.assets.AssetsBundle;
import io.dropwizard.configuration.EnvironmentVariableSubstitutor;
import io.dropwizard.configuration.SubstitutingSourceProvider;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.hibernate.ScanningHibernateBundle;
import io.dropwizard.migrations.MigrationsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class Boot extends Application<AppConfiguration> {

    public static void main(String[] args) throws Exception {
        new Boot().run(args);
    }

    @Override
    public String getName() {
        return "guestbook-sample";
    }

    private final ScanningHibernateBundle<AppConfiguration> hibernate =
            new ScanningHibernateBundle<AppConfiguration>("com.xebia.sampleservice") {
        public DataSourceFactory getDataSourceFactory(AppConfiguration configuration) {
            return configuration.getDataSourceFactory();
        }
    };

    @Override
    public void initialize(Bootstrap<AppConfiguration> bootstrap) {
        System.out.println("GUESTBOOK_JDBC_URL env var: " + System.getenv("GUESTBOOK_JDBC_URL") );

        // Enable variable substitution with environment variables
        bootstrap.setConfigurationSourceProvider(
                new SubstitutingSourceProvider(bootstrap.getConfigurationSourceProvider(),
                        new EnvironmentVariableSubstitutor(false)
                )
        );


        bootstrap.addBundle(new AssetsBundle("/assets", "/", "index.html"));

        bootstrap.addBundle(new MigrationsBundle<AppConfiguration>() {
            public DataSourceFactory getDataSourceFactory(AppConfiguration configuration) {
                return configuration.getDataSourceFactory();
            }
        });

        bootstrap.addBundle(hibernate);
    }

    @Override
    public void run(AppConfiguration configuration, Environment environment) {
        System.out.println("Database url: " + configuration.getDataSourceFactory().getUrl());


        final GuestBookDao guestBookDao = new GuestBookDao(hibernate.getSessionFactory());

        environment.jersey().register(new GuestBookResource(guestBookDao));
        environment.jersey().register(new HelloResource(configuration.getTemplate(),configuration.getDefaultName()));

        environment.healthChecks().register("template", new GreetingHealthCheck(configuration.getTemplate()));
    }


}
