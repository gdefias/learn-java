package Net.Customer;
import java.io.IOException;
import java.io.OutputStream;
/**
 * Created by Defias on 2020/08.
 * Description:  帧
 */

public interface Framer {
    //添加成帧信息并将指定消息输出到指定流
    void frameMsg(byte[] message, OutputStream out) throws IOException;

    //扫描指定的流，从中抽取出下一条消息
    byte[] nextMsg() throws IOException;
}