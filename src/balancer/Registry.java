package balancer;


import server.ServerInfo;

import java.util.Map;

/**
 * Created by Michael on 03.03.2016.
 *
 */
public class Registry {

    private static Registry instance = null;
    private Map serverlist;

    private Registry() {

    }

    public static synchronized Registry getInstance() {
        if (instance == null) {
            instance = new Registry();
        }
        return instance;
    }

    public void addServer(ServerInfo server) {
        this.serverlist.put(server.getHash(), server);
    }

    public void removeServer(ServerInfo server) {
        this.serverlist.remove(server.getHash());
    }

    public Map getMap() {
        return this.serverlist;
    }
}
