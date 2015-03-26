package com.crockproxy.handler;

import java.nio.channels.SocketChannel;

/**
 * Created by yanshi on 15-3-26.
 */
public class Socks5Protocol {


    private Socks5OperationState socks5OperationState = new Authenitcation();
    private SocketChannel socketChannel;


    public Socks5Protocol() {
        System.out.println("init");
    }

    public void doHandler() {
        socks5OperationState.doHandler(socketChannel);
        socks5OperationState = socks5OperationState.next();
    }

    public boolean isEnd() {
        return socks5OperationState == null;
    }

    public void setSocketChannel(SocketChannel socketChannel) {
        this.socketChannel = socketChannel;
    }

    public static void main(String[] args) {
        Socks5Protocol protocol = new Socks5Protocol();
        while (!protocol.isEnd()){
            protocol.doHandler();
        }
    }

}
