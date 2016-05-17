package configuration;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import service.PingService;
import service.PingServiceImpl;

import javax.inject.Singleton;

/**
 * Created by Dawid on 17.05.2016.
 */
public class AppModule extends AbstractModule {

    @Override
    protected void configure() {
    }

    @Provides
    @Singleton
    PingService pingService() {
        return new PingServiceImpl();
    }
}
