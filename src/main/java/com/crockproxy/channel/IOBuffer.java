package com.crockproxy.channel;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.List;


/**
 * Created by yanshi on 15-3-26.
 */
public class IOBuffer {
    private Session session;

    private ByteBuffer buffer = ByteBuffer.allocate(20);

    public IOBuffer(Session session) {
        this.session = session;
    }


    public List<String> read() throws IOException {
        try {
            session.getSocketChannel().read(buffer);
            buffer.flip();
            return bytesToHexString(buffer.array());
        }finally {
            buffer.clear();
        }
    }

    public void write(byte[] msg) throws IOException {
        session.getSocketChannel().write(ByteBuffer.wrap(msg));
    }

    public List<String> bytesToHexString(byte[] buffer) {
        StringBuffer sb = new StringBuffer(buffer.length);
        String sTemp;
        for (int i = 0; i < buffer.length; i++) {
            sTemp = Integer.toHexString(0xFF & buffer[i]);
            if (sTemp.length() < 2) {
                sb.append(0);
            }
            sb.append(sTemp.toUpperCase());
            sb.append(",");
        }
        return Arrays.asList(sb.toString().split(","));
    }


}
