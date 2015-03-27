package com.crockproxy.handler;

import com.crockproxy.channel.IOBuffer;

import java.io.IOException;
import java.util.List;
import java.util.function.Function;

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

                Function<List<String>, String> ipfunc = (infos) -> {
                    StringBuffer temp = new StringBuffer();
                    infos.forEach(s -> {
                        temp.append(Integer.parseInt(s, 16)).append(".");
                    });
                    return temp.toString();
                };

                String ip = ipfunc.apply(msg.subList(4,8));
                      System.out.println(ip);
            } else if (msg.containsAll(HOST)) {

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int getPort(String[] info) {
        return Integer.parseInt(info[8] + info[9], 16);
    }


    @Override
    public Socks5OperationState next() {
        return null;
    }
}

