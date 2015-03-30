package com.crockproxy.channel;

import com.crockproxy.handler.Protocol;

import java.nio.channels.ClosedChannelException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.HashMap;

/**
 * Created by yanshi on 15-3-26.
 */
public class Session {
    private Protocol protocol;

    private SocketChannel socketChannel;
    private HashMap<String, Object> attribute = new HashMap();
    private Selector selector;


    public static Session create(SocketChannel socketChannel, Protocol protocol, Selector selector) throws ClosedChannelException {
        return new Session(socketChannel, protocol, selector);
    }

    private Session(SocketChannel socketChannel, Protocol protocol, Selector selector) throws ClosedChannelException {
        this.socketChannel = socketChannel;
        this.protocol = protocol;
        this.selector = selector;
        this.protocol.setBuffer(new IOBuffer(this));
    }

    public Protocol getProtocol() {
        return protocol;
    }

    public SocketChannel getSocketChannel() {
        return socketChannel;
    }

    public Object getAttribute(String name) {
        return attribute.get(name);
    }

    public void toWrite(){
        try {
            socketChannel.register(selector, SelectionKey.OP_WRITE);
        } catch (ClosedChannelException e) {
            e.printStackTrace();
        }
    }

    public void setAttribute(String name, Object val) {
        attribute.put(name, val);
    }
}
