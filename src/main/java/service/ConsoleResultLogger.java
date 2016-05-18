package service;

import com.google.common.base.Preconditions;
import model.Host;

/**
 * Created by Dawid on 18.05.2016.
 */
public class ConsoleResultLogger implements ResultLogger {
    @Override
    public void log(Host host, boolean reachable) {
        Preconditions.checkNotNull(host);
        Preconditions.checkNotNull(host.getName());
        Preconditions.checkNotNull(host.getIp());

        System.out.println(host.getName() + " (" + host.getIp() + "): " + reachable);
    }
}
