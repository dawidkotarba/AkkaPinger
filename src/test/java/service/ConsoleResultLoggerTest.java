package service;

import model.Host;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertTrue;

/**
 * Created by Dawid on 18.05.2016.
 */
public class ConsoleResultLoggerTest {

    private ResultLogger underTest;
    private ByteArrayOutputStream outContent;

    @Before
    public void setUp() {
        underTest = new ConsoleResultLogger();
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
    }

    @After
    public void cleanUp() {
        System.setOut(null);
    }

    @Test
    public void logReachable() {
        // given
        final String TEST_NAME = "TEST_NAME";
        final String TEST_IP = "192.168.0.1";
        Host host = new Host(TEST_NAME, TEST_IP);

        // when
        underTest.log(host, true);

        // then
        assertTrue(outContent.toString().contains(TEST_NAME));
        assertTrue(outContent.toString().contains(TEST_IP));
        assertTrue(outContent.toString().contains(Boolean.toString(true)));
    }

    @Test
    public void logUnreachable() {
        // given
        final String TEST_NAME = "TEST_NAME";
        final String TEST_IP = "192.168.0.1";
        Host host = new Host(TEST_NAME, TEST_IP);

        // when
        underTest.log(host, false);

        // then
        assertTrue(outContent.toString().contains(TEST_NAME));
        assertTrue(outContent.toString().contains(TEST_IP));
        assertTrue(outContent.toString().contains(Boolean.toString(false)));
    }
}
