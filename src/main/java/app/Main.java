package app;

import actors.PingActor;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.routing.RoundRobinPool;
import com.google.inject.Guice;
import com.google.inject.Injector;
import configuration.AppModule;
import service.PingService;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Dawid on 17.05.2016.
 */
public class Main {

    public static void main(String[] args) {
        List<String> testList = Arrays.asList(new String[] {"192.168.7.77", "192.168.7.78", "192.168.7.76"});

        Injector injector = Guice.createInjector(new AppModule());
        PingService instance = injector.getInstance(PingService.class);

        final ActorSystem actorSystem = ActorSystem.create();
        final ActorRef pingActors = actorSystem.actorOf(new RoundRobinPool(testList.size()).props(PingActor.props(instance)));

        testList.forEach(hostIp -> {
            pingActors.tell(hostIp, ActorRef.noSender());
        });

        actorSystem.shutdown();
    }
}
