package com.crockproxy.handler;

import java.nio.channels.SocketChannel;

/**
 * Created by yanshi on 15-3-26.
 * AUTHENTICATION, NONE, REQUEST, DOMAIN_REQUEST, IP_REQUEST, HTTP_CONN, DIRECTLY_SEND_BACK
 */
public interface Socks5OperationState {

    public void doHandler(SocketChannel socketChannel);


    public Socks5OperationState next();

}
