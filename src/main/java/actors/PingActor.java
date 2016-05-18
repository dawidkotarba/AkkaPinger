package actors;

import akka.actor.AbstractActor;
import akka.actor.Props;
import akka.japi.pf.ReceiveBuilder;
import model.Host;
import scala.PartialFunction;
import service.PingService;
import service.ResultLogger;

/**
 * Created by Dawid on 17.05.2016.
 */
public class PingActor extends AbstractActor {

    private final PingService pingService;
    private final ResultLogger resultLogger;

    public PingActor(PingService pingService, ResultLogger resultLogger) {
        this.pingService = pingService;
        this.resultLogger = resultLogger;
        receive(pingHost().orElse(unsupported()));
    }

    private PartialFunction pingHost() {
        return ReceiveBuilder.match(Host.class, host -> {
            boolean reachable = pingService.isReachable(host.getIp());
            resultLogger.log(host, reachable);
        }).build();
    }

    private PartialFunction unsupported() {
        return ReceiveBuilder.match(Object.class, message -> System.out.println("Unsupported object " + message.getClass())).build();
    }

    public static Props props(PingService pingService, ResultLogger resultLogger) {
        return Props.create(PingActor.class, pingService, resultLogger);
    }
}
