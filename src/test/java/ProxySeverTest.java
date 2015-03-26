import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

/**
 * Created by yanshi on 15-3-24.
 */
public class ProxySeverTest {

    private Selector selector;
    private static final byte[] VER = {0x5, 0x0};


    public void start(int port) throws IOException {
        selector = Selector.open();
        ServerSocketChannel ss = ServerSocketChannel.open();
        ss.socket().bind(new InetSocketAddress(port));
        ss.configureBlocking(false);
        ss.socket().setReuseAddress(true);
        ss.register(selector, SelectionKey.OP_ACCEPT);
        while (selector.select()>0) {
            Iterator<SelectionKey> iter = selector.selectedKeys().iterator();
            while (iter.hasNext()) {
                selector.select();
                SelectionKey selectionKey = iter.next();
                iter.remove();
                if(selectionKey.isAcceptable()) {
                    SocketChannel socketChannel = ((ServerSocketChannel) selectionKey.channel()).accept();
                    socketChannel.configureBlocking(false);
                    socketChannel.register(selector,SelectionKey.OP_READ);
                } else if(selectionKey.isReadable()){
                    SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
                    ByteBuffer byteBuffer = ByteBuffer.allocate(20);
                    socketChannel.read(byteBuffer);
                    byteBuffer.flip();
                    System.out.println(bytesToHexString(byteBuffer.array(), 0, byteBuffer.array().length));
                    byteBuffer.clear();
                    socketChannel.write(ByteBuffer.wrap(VER));
                }
            }
        }
    }



    public static void main(String[] args) throws IOException {
        new ProxySeverTest().start(19801);

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
