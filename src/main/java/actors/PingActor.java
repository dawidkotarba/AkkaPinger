package actors;

import akka.actor.AbstractActor;
import akka.actor.Props;
import akka.japi.pf.ReceiveBuilder;
import model.Host;
import scala.PartialFunction;
import service.PingService;

/**
 * Created by Dawid on 17.05.2016.
 */
public class PingActor extends AbstractActor {

    private PingService pingService;

    public PingActor(PingService pingService) {
        this.pingService = pingService;
        receive(pingHost().orElse(unsupported()));
    }

    private PartialFunction pingHost() {
        return ReceiveBuilder.match(Host.class, host -> {
            boolean reachable = pingService.isReachable(host.getIp());
            System.out.println(host.getName() + " (" + host.getIp() + "): " + reachable);
        }).build();
    }

    private PartialFunction unsupported() {
        return ReceiveBuilder.match(Object.class, message -> System.out.println("Unsupported object " + message.getClass())).build();
    }

    public static Props props(PingService pingService) {
        return Props.create(PingActor.class, pingService);
    }
}
