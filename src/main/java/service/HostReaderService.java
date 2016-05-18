package service;

import model.Host;

import java.util.Set;

/**
 * Created by Dawid on 18.05.2016.
 */
public interface HostReaderService {

    Set<Host> getHosts(String hostsFile);
}
