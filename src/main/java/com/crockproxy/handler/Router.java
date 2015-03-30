package com.crockproxy.handler;

import com.crockproxy.channel.HttpHost;
import com.crockproxy.channel.IOBuffer;

import java.io.IOException;
import java.util.List;

/**
 * Created by yanshi on 15-3-26.
 */
public class Router implements Socks5OperationState {

    @Override
    public void doHandler(IOBuffer buffer) {
        try {
            List<String> msg = buffer.read();
            msg.forEach(s -> System.out.print(s + " "));
            System.out.println("");

            if (msg.containsAll(IP)) {
                HttpHost httpHost = HttpHost.create(msg);
                System.out.println(httpHost);
                buffer.getSession().setAttribute("host", httpHost);
                buffer.getSession().toWrite();
            } else if (msg.containsAll(HOST)) {

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public Socks5OperationState next() {
        return new WriteProcess();
    }
}

