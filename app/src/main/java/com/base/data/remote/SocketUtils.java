package com.base.data.remote;


import com.base.data.remote.socket.xsocket.tcp.client.TcpConnConfig;
import com.base.data.remote.socket.xsocket.tcp.client.XTcpClient;
import com.base.data.remote.socket.xsocket.tcp.client.bean.TargetInfo;
import com.base.data.remote.socket.xsocket.tcp.client.bean.TcpMsg;
import com.base.data.remote.socket.xsocket.tcp.client.helper.stickpackage.BaseStickPackageHelper;
import com.base.data.remote.socket.xsocket.tcp.client.listener.TcpClientListener;
import com.base.utils.log.KLog;


public class SocketUtils {

    // private static final String HOST = "118.114.237.153";
    // private static final String HOST = "182.150.145.24";// 公网IP
    private static final String HOST = "dc.pushia.com";// 域名
    private static final int PORT = 5703;
    private static SocketUtils instance;
    private static final int TIME_OUT = 8 * 1000;

    public static SocketUtils getInstance() {
        if (instance == null) instance = new SocketUtils();
        return instance;
    }


    public void login(String name, String psw, final SocketClientListener listener) {
        String request = "80010/" + name + "/" + psw + "/";
        send(request, listener);
    }


    public void getCategorys(String userId, SocketClientListener listener) {
        String request = "80011/" + userId + "/";
        send(request, listener);
    }

    public void send(String request, final SocketClientListener listener) {
        TargetInfo targetInfo = new TargetInfo(HOST, PORT);
        final XTcpClient xTcpClient = XTcpClient.getTcpClient(targetInfo);
        xTcpClient.addTcpClientListener(new TcpClientListener() {
            @Override
            public void onConnected(XTcpClient client) {
                KLog.e("onConnected:" + client.getTargetInfo().getIp() + "连接成功");
//                xTcpClient.sendMsg(request);
            }

            @Override
            public void onSended(XTcpClient client, TcpMsg tcpMsg) {
                KLog.e("onSended:" + tcpMsg.getSourceDataString());
            }

            @Override
            public void onDisconnected(XTcpClient client, String msg, Exception e) {
                KLog.e("onDisconnected:" + client.getTargetInfo().getIp() + "断开连接 " + msg + e);
                listener.onFailed(client, msg, e);
            }

            @Override
            public void onReceive(XTcpClient client, TcpMsg msg) {
                KLog.e("onReceive:" + client.getTargetInfo().getIp() + ":" + " len= " + msg.getSourceDataString()
                        .length() + ", " + msg.getSourceDataString());
                listener.onReceive(client, msg);
            }

            @Override
            public void onValidationFail(XTcpClient client, TcpMsg tcpMsg) {

            }
        });
        xTcpClient.config(new TcpConnConfig.Builder().setStickPackageHelper(new BaseStickPackageHelper(2048))
                .setConnTimeout(5000L).create());
        xTcpClient.sendMsg(request);
    }


    public interface SocketClientListener {
        void onReceive(XTcpClient var1, TcpMsg var2);

        void onFailed(XTcpClient var1, String var2, Exception var3);
    }

}
