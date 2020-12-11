package Net.NIO;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: Defias
 * Date: 2018-05
 *
 * NIOServer - 多线程
 */

public class Test1_NIOThreadServer {
    public static void main(String[] args) throws IOException {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(
                3,
                10,
                1000,
                TimeUnit.MILLISECONDS,
                new ArrayBlockingQueue<Runnable>( 100)
        );

        //打开ServerSocketChannel
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.socket().bind(new InetSocketAddress(1234));

        System.out.println("服务端启动！");
        //默认阻塞模式
        //while (true) {
        //    //accept()是阻塞操作，会阻塞线程直到返回一个连接，然后每个连接使用一个单独线程处理
        //    SocketChannel socketChannel = serverSocketChannel.accept();
        //    if (socketChannel != null) {
        //        executor.submit(new SocketChannelThread(socketChannel));
        //    }
        //}

        //非阻塞模式
        serverSocketChannel.configureBlocking(false);
        while(true) {
            //accept()函数会立刻返回，如果当前没有请求的链接，那么返回值为空null
            SocketChannel socketChannel = serverSocketChannel.accept();
            if(socketChannel != null) {
                executor.submit(new SocketChannelThread(socketChannel));
            }
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.print(".");
        }
    }

    public static class SocketChannelThread implements Runnable {
        private SocketChannel socketChannel;
        private String remoteName;

        public SocketChannelThread(SocketChannel socketChannel) throws IOException {
            this.socketChannel = socketChannel;
            this.remoteName = socketChannel.getRemoteAddress().toString();
            System.out.println("客户:" + remoteName + " 连接成功!");
        }

        @Override
        public void run() {
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            ByteBuffer sizeBuffer = ByteBuffer.allocate(4);
            StringBuilder sb = new StringBuilder();
            byte b[];
            while(true) {
                try {
                    sizeBuffer.clear();
                    int read = socketChannel.read(sizeBuffer);
                    if (read != -1) {
                        sb.setLength(0);
                        sizeBuffer.flip();
                        int size = sizeBuffer.getInt();
                        int readCount = 0;
                        b = new byte[1024];
                        //读取已知长度消息内容
                        while (readCount < size) {
                            buffer.clear();
                            read = socketChannel.read(buffer);
                            if (read != -1) {
                                readCount += read;
                                buffer.flip();
                                int index = 0 ;
                                while(buffer.hasRemaining()) {
                                    b[index++] = buffer.get();
                                    if (index >= b.length) {
                                        index = 0;
                                        sb.append(new String(b,"UTF-8"));
                                    }
                                }
                                if (index > 0) {
                                    sb.append(new String(b,"UTF-8"));
                                }
                            }
                        }
                        System.out.println("receive from " + remoteName +  ": " + sb.toString());
                    }
                } catch (Exception e) {
                    System.out.println(remoteName + " 断线了,连接关闭");
                    try {
                        socketChannel.close();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                    break;
                }
            }
        }
    }

}