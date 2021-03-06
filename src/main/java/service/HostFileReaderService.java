package service;

import model.Host;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

/**
 * Created by Dawid on 18.05.2016.
 */
public class HostFileReaderService implements HostReaderService {

    @Override
    public Set<Host> getHosts(String hostsFile) {

        Set<Host> hosts = new HashSet<>();
        final String SPACE = " ";

        try (Stream<String> stream = Files.lines(Paths.get(hostsFile))) {

            stream.forEach(s -> {
                String[] split = s.split(SPACE);
                hosts.add(new Host(split[0], split[1]));
            });

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return Collections.unmodifiableSet(hosts);
    }
}
