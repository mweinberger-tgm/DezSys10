package balancer;


import server.ServerInfo;

import java.util.ArrayList;

/**
 * Created by Michael on 03.03.2016.
 *
 */
public class Registry {

    private static Registry instance = null;
    private ArrayList<ServerInfo> serverlist;

    private Registry() {
        this.serverlist = new ArrayList<>();
    }

    public static synchronized Registry getInstance() {
        if (instance == null) {
            instance = new Registry();
        }
        return instance;
    }

    public void addServer(ServerInfo server) {
        this.serverlist.add(server);
    }

    public void removeServer(ServerInfo server) {
        this.serverlist.remove(server);
    }

    public ArrayList<ServerInfo> getList() {
        return this.serverlist;
    }

    public String toString() {
        String out = "";
        for (int i = 0; i < this.serverlist.size(); i++) {
            out += "" + this.serverlist.get(i).getIp() + ":" + this.serverlist.get(i).getPort() + "\n";
        }
        return out;
    }
}
