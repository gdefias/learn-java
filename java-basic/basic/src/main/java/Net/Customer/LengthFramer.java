package Net.Customer;
import java.io.DataInputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
/**
 * Created by Defias on 2020/08.
 * Description:长度的成帧方法

 适用于长度小于65535(216-1)字 节的消息
 发送者首先给出指定消息的长度，并将长度信息以big-endian顺序存入两个字 节的整数中，再将这两个字节放在完整的消息内容前，
 连同消息一起写入输出流
 */
public class LengthFramer implements Framer {
    public static final int MAXMESSAGELENGTH = 65535;
    public static final int BYTEMASK = 0xff;
    public static final int SHORTMASK = 0xffff;
    public static final int BYTESHIFT = 8;

    private DataInputStream in; // wrapper for data I/O

    public LengthFramer(InputStream in) throws IOException {
        this.in = new DataInputStream(in);
    }

    public void frameMsg(byte[] message, OutputStream out) throws IOException {
        if (message.length > MAXMESSAGELENGTH) {
            throw new IOException("message too long");
        }
        // write length prefix
        out.write((message.length >> BYTESHIFT) & BYTEMASK);
        out.write(message.length & BYTEMASK);
        // write message
        out.write(message);
        out.flush();
    }

    public byte[] nextMsg() throws IOException {
        int length;
        try {
            length = in.readUnsignedShort(); // read 2 bytes
        } catch (EOFException e) { // no (or 1 byte) message
            return null;
        }
        // 0 <= length <= 65535
        byte[] msg = new byte[length];
        in.readFully(msg); // if exception, it's a framing error.
        return msg;
    }
}