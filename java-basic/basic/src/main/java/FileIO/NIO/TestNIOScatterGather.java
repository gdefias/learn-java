package FileIO.NIO;

import java.io.FileOutputStream;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;

/**
 * Created with IntelliJ IDEA.
 * Description: 分散读Scattering read 与  聚集写gathering write
 * User: Defias
 * Date: 2018-05
 *
 * Java NIO发布时内置了对scatter/gather的支持
 *
 * Scattering read（分散读）
 * 指的是从通道读取的操作能把数据写入多个buffer，也就是sctters代表了数据从一个channel到多个buffer的过程
 *
 * gathering write（聚集写）
 * 则正好相反，表示的是从多个buffer把数据写入到一个channel中
 *
 * Scatter/gather在有些场景下会非常有用，比如需要处理多份分开传输的数据
 * 例子：一个消息包含了header和body，我们可能会把header和body保存在不同独立buffer中，这种分开处理header与body的做法会
 * 使开发更简明
 */

public class TestNIOScatterGather {

    public static void main(String[] args) throws Exception {
        test1();
        //test2();
    }

    public static void test1() throws Exception {
        RandomAccessFile aFile = new RandomAccessFile("base/src/nio-data.txt", "rw");
        FileChannel inChannel = aFile.getChannel();

        ByteBuffer header = ByteBuffer.allocate(12);
        ByteBuffer body = ByteBuffer.allocate(1024);

        ByteBuffer[] bufferArray = {header, body};


        //read()方法按照buffer在数组中的顺序将从channel中读取的数据写入到buffer，当一个buffer被写满后，channel紧接着向
        //另一个buffer中写。Scattering Reads在移动下一个buffer前，必须填满当前的buffer，这也意味着它不适用于动态消息
        // (消息大小不固定)
        long bytesRead = inChannel.read(bufferArray);
        System.out.println(bytesRead);

        System.out.println(header);
        System.out.println(body);
    }

    public static void test2() throws Exception {
        FileChannel channel = new FileOutputStream("base/src/Gather_outnio.txt").getChannel();

        ByteBuffer header = ByteBuffer.allocate(128);
        CharBuffer cheader = header.asCharBuffer();
        cheader.put("{'agent': 'chrome'}");

        ByteBuffer body   = ByteBuffer.allocate(1024);
        CharBuffer cbody = body.asCharBuffer();
        cbody.put("{hello}");

        ByteBuffer[] bufferArray = { header, body };


        //write()方法会按照buffer在数组中的顺序，将数据写入到channel，注意只有position和limit之间的数据才会被写入。因此，
        //如果一个buffer的容量为128byte，但是仅仅包含58byte的数据，那么只有58byte的数据将被写入到channel中。因此与
        //Scattering Reads相反，Gathering Writes能较好的处理动态消息
        channel.write(bufferArray);
    }
}
