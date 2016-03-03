package balancer;

import server.ServerInfo;

import java.util.ArrayList;

/**
 * Created by Michael on 03.03.2016.
 *
 */
public class Registry {

    private static Registry instance;
    private ArrayList<ServerInfo> serverlist;

    private Registry(){
        serverlist = new ArrayList<>();
    }

    public static Registry getInstance() {
        if (instance == null) {
            instance = new Registry ();
        }
        return instance;
    }

    public void addServer(ServerInfo server) {
        serverlist.add(server);
    }

    public void removeServer(ServerInfo server) {
        serverlist.remove(server);
    }

    public String toString() {
        String out = "";
        for (int i=0; i < serverlist.size(); i++) {
            out += ""+serverlist.get(i).getIp() + ":"+serverlist.get(i).getPort() + "\n";
        }
        return out;
    }
}
