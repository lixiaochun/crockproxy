package com.crockproxy.channel;

import com.crockproxy.handler.Protocol;

import java.nio.channels.ClosedChannelException;
import java.nio.channels.SocketChannel;

/**
 * Created by yanshi on 15-3-26.
 */
public class Session {
    private Protocol protocol;

    private SocketChannel socketChannel;


    public static Session create(SocketChannel socketChannel, Protocol protocol) throws ClosedChannelException {
        return new Session(socketChannel, protocol);
    }

    private Session(SocketChannel socketChannel, Protocol protocol) throws ClosedChannelException {
        this.socketChannel = socketChannel;
        this.protocol = protocol;
        this.protocol.setBuffer(new IOBuffer(this));
    }

    public Protocol getProtocol() {
        return protocol;
    }


    public SocketChannel getSocketChannel() {
        return socketChannel;
    }

}
