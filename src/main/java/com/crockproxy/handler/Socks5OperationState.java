package com.crockproxy.handler;

import com.crockproxy.channel.IOBuffer;

import java.util.Arrays;
import java.util.List;

/**
 * Created by yanshi on 15-3-26.
 * AUTHENTICATION, NONE, REQUEST, DOMAIN_REQUEST, IP_REQUEST, HTTP_CONN, DIRECTLY_SEND_BACK
 */
public interface Socks5OperationState {

    public static List<String> IP = Arrays.asList("05", "01", "00", "01");
    public static List<String> HOST = Arrays.asList("05", "01", "00", "03");
    public static List<String> ANY= Arrays.asList("05", "02", "00", "02");

    public void doHandler(IOBuffer buffer);


    public Socks5OperationState next();

}
