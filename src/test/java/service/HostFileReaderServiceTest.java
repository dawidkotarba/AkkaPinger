package service;

import configuration.Constants;
import model.Host;
import org.junit.Before;
import org.junit.Test;

import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

/**
 * Created by Dawid on 18.05.2016.
 */
public class HostFileReaderServiceTest {

    private HostReaderService underTest;

    @Before
    public void setUp() {
        underTest = new HostFileReaderService();
    }

    @Test
    public void getHosts() {
        // given
        String hostsFile = Constants.HOSTS_FILE;

        // when
        Set<Host> hosts = underTest.getHosts(hostsFile);

        // then
        assertFalse(hosts.isEmpty());
        Host exampleHost = hosts.iterator().next();
        assertEquals("example", exampleHost.getName());
        assertEquals("127.0.0.1", exampleHost.getIp());
    }

    @Test(expected = RuntimeException.class)
    public void getHostWrongFile() {
        // given
        String hostsFile = "";

        // when
        underTest.getHosts(hostsFile);
    }
}
