package app;

import actors.PingActor;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.routing.RoundRobinPool;
import com.google.inject.Guice;
import com.google.inject.Injector;
import configuration.AppModule;
import service.HostReaderService;
import service.PingService;

/**
 * Created by Dawid on 17.05.2016.
 */
public class Main {

    public static void main(String[] args) {

        Injector injector = Guice.createInjector(new AppModule());
        PingService instance = injector.getInstance(PingService.class);
        HostReaderService hostReaderService = injector.getInstance(HostReaderService.class);

        final ActorSystem actorSystem = ActorSystem.create();
        final ActorRef pingActors = actorSystem.actorOf(new RoundRobinPool(hostReaderService.getHosts().size()).props(PingActor.props(instance)));

        hostReaderService.getHosts().stream().forEach(hostIp -> pingActors.tell(hostIp, ActorRef.noSender()));

        actorSystem.shutdown();
    }
}
