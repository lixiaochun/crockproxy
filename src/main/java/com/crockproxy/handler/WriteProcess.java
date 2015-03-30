package com.crockproxy.handler;

import com.crockproxy.channel.HttpHost;
import com.crockproxy.channel.IOBuffer;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.nio.ByteBuffer;

/**
 * Created by yanshi on 15-3-30.
 */
public class WriteProcess implements  Socks5OperationState{
    //                05 00 00 01 00  00 00 00 00 00

    @Override
    public void doHandler(IOBuffer buffer) {
        HttpHost httpHost = HttpHost.class.cast(buffer.getSession().getAttribute("host"));
        System.out.println(httpHost);

        try {
            buffer.getSession().getSocketChannel().write(ByteBuffer.wrap(new byte[]{0x5, 0x0, 0x0, 0x1, 0, 0, 0, 0, 0, 0}));
            Socket socket = new Socket(httpHost.getIp(),httpHost.getPort());
            InputStream isIn = socket.getInputStream();
            byte[] t = new byte[409600];
            System.out.println("start");
            int len;
            while ((len = isIn.read(t)) != -1) {
                if (len > 0) {
                    buffer.getSession().getSocketChannel().write(ByteBuffer.wrap(t)) ;
                }
            }
            System.out.println("DONE");

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
