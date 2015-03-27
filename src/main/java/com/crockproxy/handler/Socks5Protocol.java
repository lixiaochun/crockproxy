package com.crockproxy.handler;

import com.crockproxy.channel.IOBuffer;

/**
 * Created by yanshi on 15-3-26.
 */
public class Socks5Protocol implements Protocol {


    private Socks5OperationState socks5OperationState = new Authentication();
    private IOBuffer buffer;


    public Socks5Protocol() {
        System.out.println("init");
    }

    public void doHandler() {
        socks5OperationState.doHandler(buffer);
        socks5OperationState = socks5OperationState.next();
    }

    public boolean isEnd() {
        return socks5OperationState == null;
    }

    @Override
    public void setBuffer(IOBuffer buffer) {
        this.buffer = buffer;
    }

    public static void main(String[] args) {
        Socks5Protocol protocol = new Socks5Protocol();
        while (!protocol.isEnd()) {
            protocol.doHandler();
        }
    }

}
