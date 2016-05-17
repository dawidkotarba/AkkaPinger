package model;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

/**
 * Created by Dawid on 18.05.2016.
 */
public class Host {

    private String name;
    private String ip;

    public Host(String name, String ip) {
        this.name = name;
        this.ip = ip;
    }

    public String getName() {
        return name;
    }

    public String getIp() {
        return ip;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name, ip);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (this.getClass() != obj.getClass()) {
            return false;
        }
        final Host other = (Host) obj;
        return Objects.equal(this.ip, other.ip)
            && Objects.equal(this.name, other.name);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
            .add("name", name)
            .add("ip", ip)
            .toString();
    }
}
