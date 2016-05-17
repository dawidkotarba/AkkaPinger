package actors;

import akka.actor.AbstractActor;
import akka.actor.Props;
import akka.japi.pf.ReceiveBuilder;
import scala.PartialFunction;
import service.PingService;

import java.util.function.Supplier;

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
        return ReceiveBuilder.match(String.class, hostIp -> {
            boolean reachable = pingService.isReachable(hostIp);
            System.out.println(hostIp + ": " + reachable);
        }).build();
    }

    private PartialFunction unsupported() {
        return ReceiveBuilder.match(Object.class, message -> System.out.println("Unsupported object " + message.getClass())).build();
    }

    public static Props props(PingService pingService) {
//        Supplier<PingActor> supp = () -> new PingActor(pingService);
        return Props.create(PingActor.class, pingService);
    }
}
