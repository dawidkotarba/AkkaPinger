package service;

import java.io.IOException;
import java.net.InetAddress;

/**
 * Created by Dawid on 17.05.2016.
 */
public class PingServiceImpl implements PingService {

    public boolean isReachable(String hostIp) {
        try {
            InetAddress address = InetAddress.getByName(hostIp);
            return address.isReachable(100);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
