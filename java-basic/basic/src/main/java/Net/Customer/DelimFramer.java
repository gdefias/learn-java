package Net.Customer;
import java.io.ByteArrayOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
/**
 * Created by Defias on 2020/08.
 * Description:  基于定界符的成帧方法

 其定界符为“换行”符(“\n”，字节值为10)
 */

public class DelimFramer implements Framer {

    private InputStream in;        // data source
    private static final byte DELIMITER = '\n'; // message delimiter

    public DelimFramer(InputStream in) {
        this.in = in;
    }

    public void frameMsg(byte[] message, OutputStream out) throws IOException {
        // ensure that the message does not contain the delimiter
        for (byte b : message) {
            if (b == DELIMITER) {
                throw new IOException("Message contains delimiter");
            }
        }
        out.write(message);
        out.write(DELIMITER);
        out.flush();
    }

    public byte[] nextMsg() throws IOException {
        ByteArrayOutputStream messageBuffer = new ByteArrayOutputStream();
        int nextByte;

        // fetch bytes until find delimiter
        while ((nextByte = in.read()) != DELIMITER) {
            if (nextByte == -1) { // end of stream?
                if (messageBuffer.size() == 0) { // if no byte read
                    return null;
                } else { // if bytes followed by end of stream: framing error
                    throw new EOFException("Non-empty message without delimiter");
                }
            }
            messageBuffer.write(nextByte); // write byte to buffer
        }

        return messageBuffer.toByteArray();
    }
}