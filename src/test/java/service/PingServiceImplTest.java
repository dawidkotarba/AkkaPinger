package service;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


/**
 * Created by Dawid on 18.05.2016.
 */
public class PingServiceImplTest {

    private PingService underTest;

    @Before
    public void setUp() {
        underTest = new PingServiceImpl();
    }

    @Test
    public void isReachablePingableIP() {
        // given
        String ip = "127.0.0.1";

        // when
        boolean reachable = underTest.isReachable(ip);

        // then
        assertTrue(reachable);
    }

    @Test
    public void isReachableUnreachableIP() {
        // given
        String ip = "0.0.0.0";

        // when
        boolean reachable = underTest.isReachable(ip);

        // then
        assertFalse(reachable);
    }

    @Test(expected = RuntimeException.class)
    public void isReachableWrongIp() {
        // given
        String ip = "wrongIP";

        // when
        underTest.isReachable(ip);
    }
}
