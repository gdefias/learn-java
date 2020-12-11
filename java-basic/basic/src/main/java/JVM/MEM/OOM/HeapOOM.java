package JVM.MEM.OOM;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Defias on 2020/09.
 * Description: 演示堆内存OOM

 VM Args:-Xms20m -Xmx20m -XX:+HeapDumpOnOutOfMemoryError
 限制Java堆的大小为20MB，不可扩展（将堆的最小值-Xms参数与最大值-Xmx参数设置为一样即可避免堆自动扩展）
 HeapDumpOnOutOfMemoryError: 让虚拟机在出现内存溢出异常时Dump出当前的内存堆转储快照以便事后进行分析

 */
public class HeapOOM {
    static class OOMObject {}

    public static void main(String[] args){
        List<OOMObject> list=new ArrayList<OOMObject>();
        while(true){
            list.add(new OOMObject());
        }
    }
}