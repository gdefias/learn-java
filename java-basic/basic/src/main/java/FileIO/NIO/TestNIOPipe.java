package FileIO.NIO;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.Pipe;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: Defias
 * Date: 2018-05
 *
 * Pipe
 * 一个Java NIO的管道Pipe是两个线程间单向传输数据的连接
 *
 * 一个管道（Pipe）有一个source channel和一个sink channel
 * 通过source通道可以从Pipe读取数据
 * 通过sink通道可以把数据写到Pipe
 */

public class TestNIOPipe {
    public static void main(String[] args) throws IOException {
        Pipe pipe = Pipe.open();

        //将缓冲区数据写入到管道
        Pipe.SinkChannel sinkChannel = pipe.sink();


        String newData = "New String to write to file..." + System.currentTimeMillis();
        ByteBuffer buf = ByteBuffer.allocate(48);
        //buf.clear();
        buf.put(newData.getBytes());
        buf.flip();

        //将数据写入到管道
        while(buf.hasRemaining()) {
            sinkChannel.write(buf);
        }

        //从管道读取数据
        Pipe.SourceChannel sourceChannel = pipe.source();
        ByteBuffer bufr = ByteBuffer.allocate(48);
        int bytesRead = sourceChannel.read(bufr);  //read()的返回值代表实际读取到的字节数
        System.out.println(bytesRead);

        //关闭管道
        sinkChannel.close();
        sourceChannel.close();
    }
}
