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
 * Created by Defias on 2017/3/8.

 NIOServer

 Selector选择器对象
 用于检查一个或多个NIO Channel的状态是否处于可读、可写。可以实现单线程管理多个channels（多个网络链接）

 selectedKeys()方法：
 获取I/O操作就绪的SelectionKey, 通过SelectionKey可以知道哪些Channel的哪类I/O操作已经就绪


 SocketChannel对象
 configureBlocking(false)方法： 把Channel设置为非阻塞


 ServerSocketChannel对象
 register()方法:
 将channel注册到selector中，并指定事件，通常都是先注册一个OP_ACCEPT事件, 然后在OP_ACCEPT到来时, 再将这个Channel的OP_READ
 注册到Selector中。如果一个Channel要注册到Selector中, 那么这个Channel必须是非阻塞的,因此FileChannel是不能够使用选择器的,
 因为FileChannel都是阻塞的

 */


public class Test1_NIOServer {
    private static final int BUF_SIZE = 256;
    private static final int TIMEOUT = 3000;

    public static void main(String args[]) throws IOException {
        test1();
    }

    public static void test1() throws IOException {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.socket().bind(new InetSocketAddress(1234));
        serverSocketChannel.configureBlocking(false);  //配置为非阻塞模式

        Selector selector = Selector.open();

        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        System.out.println("服务端启动！");
        while (true) {
            //通过调用select方法, 阻塞地等待channel I/O可操作
            if (selector.select(TIMEOUT) == 0) {
                System.out.print(".");
                continue;
            }

            Iterator<SelectionKey> keyIterator = selector.selectedKeys().iterator();
            while (keyIterator.hasNext()) {  //遍历已经就绪的I/O操作
                SelectionKey key = keyIterator.next();
                keyIterator.remove();  //当获取一个SelectionKey后, 就要将它删除, 表示已经对这个IO事件进行了处理

                if (key.isAcceptable()) {
                    SocketChannel clientChannel = ((ServerSocketChannel)key.channel()).accept();
                    clientChannel.configureBlocking(false);

                    clientChannel.register(key.selector(), SelectionKey.OP_READ, ByteBuffer.allocate(BUF_SIZE));
                }

                if (key.isReadable()) {
                    SocketChannel clientChannel = (SocketChannel) key.channel();
                    ByteBuffer buf = (ByteBuffer) key.attachment();
                    long bytesRead = clientChannel.read(buf);
                    if (bytesRead == -1) {
                        clientChannel.close();
                    } else if (bytesRead > 0) {
                        key.interestOps(SelectionKey.OP_READ | SelectionKey.OP_WRITE);
                        System.out.println("Get data length: " + bytesRead);
                    }
                }

                if (key.isValid() && key.isWritable()) {
                    ByteBuffer buf = (ByteBuffer) key.attachment();
                    buf.flip();
                    SocketChannel clientChannel = (SocketChannel) key.channel();

                    clientChannel.write(buf);

                    if (!buf.hasRemaining()) {
                        key.interestOps(SelectionKey.OP_READ);
                    }
                    buf.compact();
                }
            }
        }
    }
}
