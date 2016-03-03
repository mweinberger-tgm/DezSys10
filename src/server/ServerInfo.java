package server;

/**
 * Created by Michael on 03.03.2016.
 */
public class ServerInfo {

    private String ip;
    private int port;

    public ServerInfo(){

    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public int getHash() {
        return ip.hashCode() + (""+port).hashCode();
    }

    @Override
    public String toString() {
        return ip  + ":" + port;
    }

    /*
    @Override
    public boolean equals(Object o) {

    }*/

}
