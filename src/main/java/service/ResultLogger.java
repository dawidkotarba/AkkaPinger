package service;

import model.Host;

/**
 * Created by Dawid on 18.05.2016.
 */
public interface ResultLogger {
    void log(Host host, boolean reachable);
}
