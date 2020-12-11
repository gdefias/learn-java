package Net.NIO;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
/**
 * Created by Defias on 2017/6/26.
 *
 * NIO服务器端
 */


public class Test_NIOServer {
    private Selector selector;

    public static void main(String[] args) throws IOException {
        Test_NIOServer server = new Test_NIOServer();
        server.initServer(60001);
        server.listen();
    }

    //获得一个ServerSocket通道，并对该通道做一些初始化的工作
    public void initServer(int port) throws IOException {
        ServerSocketChannel serverChannel = ServerSocketChannel.open();
        serverChannel.configureBlocking(false);
        serverChannel.socket().bind(new InetSocketAddress(port)); //将该通道对应的ServerSocket绑定到port端口

        //获得一个通道管理器
        this.selector = Selector.open();
        serverChannel.register(selector, SelectionKey.OP_ACCEPT);
    }


    //采用轮询的方式监听selector上是否有需要处理的事件，如果有则进行处理
    @SuppressWarnings("unchecked")
    public void listen() throws IOException {
        System.out.println("服务端启动成功！");

        while (true) {
            selector.select(); //当注册的事件到达时方法返回, 否则该方法会一直阻塞
            Iterator ite = this.selector.selectedKeys().iterator();  //获得selector中已到达的注册事件
            while (ite.hasNext()) {
                SelectionKey key = (SelectionKey)ite.next();
                ite.remove();  //删除已选的key以防重复处理
                if (key.isAcceptable()) {
                    accept(key);
                } else if (key.isReadable()) {
                    read(key);
                }
            }
        }
    }

    //处理客户端请求连接事件
    public void accept(SelectionKey key) throws IOException {
        ServerSocketChannel server = (ServerSocketChannel)key.channel();
        SocketChannel channel = server.accept();
        channel.configureBlocking(false);

        channel.write(ByteBuffer.wrap(new String("to client msg").getBytes()));

        //写的数据量比较大时可以把write()的调用放在了while循环中
        //因为无法保证在write的时候实际写入了多少字节的数据，因此可以通过一个循环操作不断把Buffer中数据写入到
        //SocketChannel中直到Buffer中的数据全部写入为止
        //while(buf.hasRemaining()) { channel.write(buf);}

        //为了可以接收到客户端的信息，需要给通道设置可读
        channel.register(this.selector, SelectionKey.OP_READ);
    }

    //处理可读事件
    public void read(SelectionKey key) throws IOException {
        SocketChannel channel = (SocketChannel) key.channel();

        ByteBuffer buffer = ByteBuffer.allocate(10);
        channel.read(buffer);

        byte[] data = buffer.array();
        String msg = new String(data).trim();
        System.out.println("服务端收到信息："+msg);
        ByteBuffer outBuffer = ByteBuffer.wrap(msg.getBytes());
        channel.write(outBuffer);  //将消息回送给客户端
    }

}