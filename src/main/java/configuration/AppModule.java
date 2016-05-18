package configuration;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import service.*;

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

    @Provides
    @Singleton
    HostReaderService hostFileReaderService() {
        return new HostFileReaderService();
    }

    @Provides
    @Singleton
    ResultLogger resultLogger() {
        return new ConsoleResultLogger();
    }
}
