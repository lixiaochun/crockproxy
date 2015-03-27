package com.crockproxy.handler;

import com.crockproxy.channel.IOBuffer;

/**
 * Created by yanshi on 15-3-26.
 */
public interface Protocol {
    public void doHandler();

    public void setBuffer(IOBuffer buffer);

}
