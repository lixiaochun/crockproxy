package com.crockproxy.handler;

import com.crockproxy.channel.IOBuffer;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * Created by yanshi on 15-3-26.
 */
public class Authentication implements Socks5OperationState {

    @Override
    public void doHandler(IOBuffer buffer) {
        try {
            List<String> msgs = buffer.read();
            msgs.forEach(s -> System.out.print(s + " "));
            boolean isAny = msgs.containsAll(Arrays.asList(new String[]{"05", "02", "00", "02"}));
            if (isAny) {
                System.out.println("->50");
                buffer.write(new byte[]{0x5, 0x0});
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Socks5OperationState next() {
        return new Router();
    }

}


