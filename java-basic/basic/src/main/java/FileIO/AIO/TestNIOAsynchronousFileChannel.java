package FileIO.AIO;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.channels.CompletionHandler;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.concurrent.Future;

/**
 * Created with IntelliJ IDEA.
 * Description: 文件AIO
 * User: Defias
 * Date: 2018-05

 AsynchronousFileChannel

 abstract Future<Integer>	read(ByteBuffer dst, long position)
 从给定的文件位置开始，从该通道读取一个字节序列到给定的缓冲区
 dst - 要传输字节的缓冲区
 position - 传输开始的文件位置; 必须是非负的

 abstract <A> void	read(ByteBuffer dst, long position, A attachment, CompletionHandler<Integer,? super A> handler)
 从给定的文件位置开始，从该通道读取一个字节序列到给定的缓冲区
 src - 要传输字节的缓冲区
 position - 传输开始的文件位置; 必须是非负的
 attachment - 要附加到I / O操作的对象; 可以是null
 handler - 消费结果的处理程序
 当读取操作完成后，CompletionHandler的completed()方法就会被调用，如果读取操作失败，则CompletionHandler的failed()会被调用
 当传递给complete()方法的参数时，将传递一个整数，表示读取了多少字节，以及传递给read()方法的附件


 */

public class TestNIOAsynchronousFileChannel {

    public static void main(String[] args) throws IOException {
        //Read_Solution1();
        //Read_Solution2();
        //Write_Solution1();
        Write_Solution2();

    }

    //第1种方式read
    public static void Read_Solution1() throws IOException {
        Path path = Paths.get("base/src/nio-data.txt");
        //StandardOpenOption.READ表示以读的形式操作文件
        AsynchronousFileChannel fileChannel = AsynchronousFileChannel.open(path, StandardOpenOption.READ);

        //创建Buffer
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        long position = 0;
        //读 - 会立刻返回
        Future<Integer> rfuture = fileChannel.read(buffer, position);

        //检查是否读取完成
        while(!rfuture.isDone());

        buffer.flip();
        byte[] data = new byte[buffer.limit()];
        buffer.get(data);
        System.out.println(new String(data));
        buffer.clear();
    }

    //第2种方式read
    public static void Read_Solution2() throws IOException {
        Path path = Paths.get("base/src/nio-data.txt");
        AsynchronousFileChannel fileChannel = AsynchronousFileChannel.open(path, StandardOpenOption.READ);

        //创建Buffer
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        long position = 0;
        //读
        fileChannel.read(buffer, position, buffer, new CompletionHandler<Integer, ByteBuffer>() {
            @Override
            public void completed(Integer result, ByteBuffer attachment) {
                System.out.println("result = " + result);
                attachment.flip();
                byte[] data = new byte[attachment.limit()];
                attachment.get(data);
                System.out.println(new String(data));
                attachment.clear();
                System.out.println("completed end");
            }

            @Override
            public void failed(Throwable exc, ByteBuffer attachment) {
                System.out.println("read failed");
            }
        });

        //因为CompletionHandler是异步处理的，如果要显示就要让主线程等一下它
        //否则主线程（main）在异步线程执行之前退出了，处理过程就不会显示了
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //第1种方式write
    public static void Write_Solution1() throws IOException {
        Path path = Paths.get("base/src/nio-data-write.txt"); //文件必须是已经存在的
        if(!Files.exists(path)) {
            Files.createFile(path);
        }
        AsynchronousFileChannel fileChannel = AsynchronousFileChannel.open(path, StandardOpenOption.WRITE);

        //创建Buffer
        ByteBuffer buffer = ByteBuffer.allocate(10);
        long position = 0;
        buffer.put("test data".getBytes());
        buffer.flip();

        //写
        //在代码工作之前，文件必须已经存在。如果文件不存在，write()方法会抛出一个
        Future<Integer> operation = fileChannel.write(buffer, position);
        buffer.clear();

        while(!operation.isDone());

        System.out.println("Write done");
    }

    //第2种方式write
    public static void Write_Solution2() throws IOException {
        Path path = Paths.get("base/src/nio-data-write.txt");
        if(!Files.exists(path)){
            Files.createFile(path);
        }
        AsynchronousFileChannel fileChannel = AsynchronousFileChannel.open(path, StandardOpenOption.WRITE);

        //创建Buffer
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        long position = 0;
        buffer.put("test data".getBytes());
        buffer.flip();

        //写
        //当写入操作完成后，CompletionHandler的completed()方法就会被调用
        //如果因为某些原因导致写入失败，failed()方法就会被调用
        fileChannel.write(buffer, position, buffer, new CompletionHandler<Integer, ByteBuffer>() {

            @Override
            public void completed(Integer result, ByteBuffer attachment) {
                System.out.println("bytes written: " + result);
                attachment.flip();
                byte[] data = new byte[attachment.limit()];
                attachment.get(data);
                System.out.println(new String(data));
                attachment.clear();
                System.out.println("completed end");
            }

            @Override
            public void failed(Throwable exc, ByteBuffer attachment) {
                System.out.println("Write failed");
                exc.printStackTrace();
            }
        });

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
