package JVM;
import java.io.*;
/**
 * Created by Defias on 2017/3/17.

 Java运行时

 */
public class TestRuntime {
    public static void main(String[] args) {
        Runtime rt = Runtime.getRuntime();
        System.out.println("count of Processor: "+ rt.availableProcessors() );
        System.out.println("Total memory of JVM: "+ rt.totalMemory()/10001000 );  //下划线为了分清位数，实际就是10001000
        System.out.println("Free memory of jvm:  "+ rt.freeMemory()/10001000 );
        System.out.println("Max unused of jvm: "+rt.maxMemory()/10001000 );


        //演示内存管理
        long mem1,mem2;
        Integer someints[] = new Integer[1000];
        System.out.println("Total memory is ：" + rt.totalMemory());
        mem1 = rt.freeMemory();
        System.out.println("Initial free is : " + mem1);
        rt.gc();
        mem1 = rt.freeMemory();
        System.out.println("Free memory after garbage collection : " + mem1);

        //allocate integers
        for(int i=0; i<1000; i++)
            someints[i] = new Integer(i);
        mem2 = rt.freeMemory();
        System.out.println("Free memory after allocation : " + mem2);
        System.out.println("Memory used by allocation : " +(mem1-mem2));

        //discard Intergers
        for(int i=0; i<1000; i++)
            someints[i] = null;
        rt.gc(); //request garbage collection
        mem2 = rt.freeMemory();
        System.out.println("Free memory after collecting " + "discarded integers : " + mem2);

        //执行其它程序
        try {
            Process p = rt.exec("ifconfig");  //返回一个Process对象
            //Process p = rt.exec("/Applications/QQ.app");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
