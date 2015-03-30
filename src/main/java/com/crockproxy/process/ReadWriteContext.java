package com.crockproxy.process;

import com.crockproxy.channel.Session;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * Created by yanshi on 15-3-30.
 */
public class ReadWriteContext {

    private Session session;

    public ReadWriteContext(Session session) {
        this.session = session;
    }

    public void read(){
    }


    public static void main(String[] args) throws IOException {
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.connect(new InetSocketAddress("119.75.217.109",80));
        ByteBuffer buffer = ByteBuffer.allocate(4096);
        socketChannel.read(buffer);
        buffer.flip();
        System.out.println(new String(buffer.array()));


    }
}
