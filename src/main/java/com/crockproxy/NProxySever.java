package com.crockproxy;

import com.crockproxy.handler.Socks5Protocol;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by yanshi on 15-3-26.
 */
public class NProxySever {

    private Selector selector;
    private Map<SocketChannel, Socks5Protocol> socketChannelSessionMap = new ConcurrentHashMap<SocketChannel, Socks5Protocol>();


    public void start(int port) {
        try {
            selector = Selector.open();
            ServerSocketChannel server = ServerSocketChannel.open();
            server.configureBlocking(false);
            server.socket().setReuseAddress(true);
            server.socket().bind(new InetSocketAddress(port));
            server.register(selector, SelectionKey.OP_ACCEPT);
            listener();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    private void listener() {
        while (true) {
            try {
                selector.select();
                Iterator<SelectionKey> keys = selector.selectedKeys().iterator();
                while (keys.hasNext()) {
                    SelectionKey key = keys.next();
                    keys.remove();
                    process(key);

                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    private void process(SelectionKey key) {
        if (key.isAcceptable()) {
            SocketChannel socketChannel;
            try {
                socketChannel = ((ServerSocketChannel) key.channel()).accept();
                socketChannel.configureBlocking(false);
                socketChannel.register(selector, SelectionKey.OP_READ);
                socketChannelSessionMap.put(socketChannel,new Socks5Protocol());
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (key.isReadable()) {
            SocketChannel socketChannel = (SocketChannel) key.channel();
            Socks5Protocol socks5Protocol = socketChannelSessionMap.get(socketChannel);
            socks5Protocol.setSocketChannel(socketChannel);
            socks5Protocol.doHandler();
        }
    }


    public static void main(String[] args){
                new NProxySever().start(1210);
    }
}
