package com.base.data.remote.socket.xsocket.tcp.server.manager;

import com.base.data.remote.socket.xsocket.tcp.server.XTcpServer;

import java.util.HashSet;
import java.util.Set;

/**
 * tcpserver
 */
public class TcpServerManager {
    private static Set<XTcpServer> sMXTcpServers = new HashSet<>();

    public static void putTcpServer(XTcpServer XTcpServer) {
        sMXTcpServers.add(XTcpServer);
    }

    public static XTcpServer getTcpServer(int port) {
        for (XTcpServer ts : sMXTcpServers) {
            if (ts.getPort() == port) {
                return ts;
            }
        }
        return null;
    }
}
