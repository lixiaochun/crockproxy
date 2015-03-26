package com.crockproxy.handler;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * Created by yanshi on 15-3-26.
 */
public class Authenitcation implements Socks5OperationState {

    @Override
    public void doHandler(SocketChannel socketChannel) {
        ByteBuffer byteBuffer = ByteBuffer.allocate(20);
        try {
            socketChannel.read(byteBuffer);
            byteBuffer.flip();
            System.out.println(bytesToHexString(byteBuffer.array(), 0, byteBuffer.array().length));
            byteBuffer.clear();
            socketChannel.write(ByteBuffer.wrap(new byte[]{0x5, 0x0}));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Socks5OperationState next() {
        return new Request();
    }

    public String bytesToHexString(byte[] buffer, int begin, int end) {
        StringBuffer sb = new StringBuffer(buffer.length);
        String sTemp;
        for (int i = begin; i < end; i++) {
            sTemp = Integer.toHexString(0xFF & buffer[i]);
            if (sTemp.length() < 2) {
                sb.append(0);
            }
            sb.append(sTemp.toUpperCase());
            sb.append(" ");
        }

        return sb.toString();
    }
}


