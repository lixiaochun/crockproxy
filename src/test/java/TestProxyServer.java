import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.*;

/**
 * Created by yanshi on 15-3-24.
 */
public class TestProxyServer {

    public static void main(String[] args) throws IOException {
        {
            Proxy proxy = new Proxy(Proxy.Type.SOCKS, new InetSocketAddress("127.0.0.1", 1210));
            URL url = new URL("http://www.baidu.com");
            URLConnection connection = url.openConnection(proxy);
            InputStream in = connection.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }

        }
    }
}
