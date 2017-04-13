package com.base.data.remote.socket.xsocket.udp.client.bean;

import com.base.data.remote.socket.xsocket.tcp.client.bean.TargetInfo;
import com.base.data.remote.socket.xsocket.tcp.client.bean.TcpMsg;

/**
 */
public class UdpMsg extends TcpMsg {

    public UdpMsg(byte[] data, TargetInfo target, MsgType type) {
        super(data, target, type);
    }

    public UdpMsg(String data, TargetInfo target, MsgType type) {
        super(data, target, type);
    }

    public UdpMsg(int id) {
        super(id);
    }
}
