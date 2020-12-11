package Net.NIO;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;

/**
 * Created by Defias on 2017/6/26.
 *
 * NIO客户端
 */

public class Test_NIOClient {
    private Selector selector;

    public static void main(String[] args) throws IOException {
        Test_NIOClient client = new Test_NIOClient();
        client.initClient("localhost",60001);
        client.listen();
    }



    //获得一个Socket通道，并对该通道做一些初始化的工作
    public void initClient(String serverip, int serverport) throws IOException {
        SocketChannel channel = SocketChannel.open();
        channel.configureBlocking(false);
        this.selector = Selector.open();

        channel.connect(new InetSocketAddress(serverip ,serverport));
        channel.register(selector, SelectionKey.OP_CONNECT);
    }

    //采用轮询的方式监听selector上是否有需要处理的事件，如果有则进行处理
    @SuppressWarnings("unchecked")
    public void listen() throws IOException {

        int timecount = 0; //轮询次数

        while (true) {
            if(++timecount > 15) {
                break;
                //channel.close();  //断开
            }

            selector.select();
            // 获得selector中选中的项的迭代器
            Iterator ite = this.selector.selectedKeys().iterator();

            while (ite.hasNext()) {
                SelectionKey key = (SelectionKey) ite.next();
                // 删除已选的key,以防重复处理
                ite.remove();
                if (key.isConnectable()) {  //连接事件
                    connect(key);
                } else if (key.isReadable()) {  //可读事件
                    read(key);
                }
            }

        }
    }

    public void connect(SelectionKey key) throws IOException {
        SocketChannel channel = (SocketChannel) key.channel();
        // 如果正在连接，则完成连接
        if(channel.isConnectionPending()){
            channel.finishConnect();
        }

        channel.configureBlocking(false);

        channel.write(ByteBuffer.wrap(new String("to server msg").getBytes()));

        //在和服务端连接成功之后，为了可以接收到服务端的信息，需要给通道设置读的权限
        channel.register(this.selector, SelectionKey.OP_READ);
    }

    //处理读取服务端发来的信息 的事件
    public void read(SelectionKey key) throws IOException {
        SocketChannel channel = (SocketChannel)key.channel();

        ByteBuffer buffer = ByteBuffer.allocate(20);
        channel.read(buffer);

        byte[] data = buffer.array();
        String msg = new String(data).trim();
        System.out.println("客户端收到信息并回送给服务器："+msg);
        ByteBuffer outBuffer = ByteBuffer.wrap(msg.getBytes());
        channel.write(outBuffer);// 将消息回送给服务端
    }
}