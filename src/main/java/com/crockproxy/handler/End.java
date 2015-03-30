package com.crockproxy.handler;

import com.crockproxy.channel.IOBuffer;

import java.io.IOException;
import java.nio.ByteBuffer;

/**
 * Created by yanshi on 15-3-30.
 */
public class End implements Socks5OperationState {
    @Override
    public void doHandler(IOBuffer buffer) {
        try {
            buffer.getSession().getSocketChannel().write(ByteBuffer.wrap("aaaadsdasdsddsadddddddddddddddddddddddddd".getBytes()))  ;
            buffer.getSession().getSocketChannel().finishConnect();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Socks5OperationState next() {
        return null;
    }
}
