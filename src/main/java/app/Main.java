package app;

import actors.PingActor;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.routing.RoundRobinPool;
import com.google.inject.Guice;
import com.google.inject.Injector;
import configuration.AppModule;
import configuration.Constants;
import model.Host;
import service.HostReaderService;
import service.PingService;
import service.ResultLogger;

import java.util.Set;

/**
 * Created by Dawid on 17.05.2016.
 */
public class Main {

    public static void main(String[] args) {

        Injector injector = Guice.createInjector(new AppModule());
        PingService pingService = injector.getInstance(PingService.class);
        ResultLogger resultLogger = injector.getInstance(ResultLogger.class);
        HostReaderService hostReaderService = injector.getInstance(HostReaderService.class);

        Set<Host> hosts = hostReaderService.getHosts(Constants.HOSTS_FILE);

        ActorSystem actorSystem = ActorSystem.create();
        ActorRef pingActors = actorSystem.actorOf(new RoundRobinPool(hosts.size()).props(PingActor.props(pingService, resultLogger)));

        hosts.stream().forEach(hostIp -> pingActors.tell(hostIp, ActorRef.noSender()));

        actorSystem.shutdown();
    }
}
