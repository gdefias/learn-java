package Basic;
import java.util.Arrays;

/**
 * Created by Defias on 2017/3/8.
 *
 * System类
 * 代表系统，系统级的很多属性和控制方法都放置在该类的内部。该类位于java.lang包
 *
 */


public class TestSystem {
    public static void main(String[] args) {
        System.out.println("Test");
        System.out.println("----------------");

        //arraycopy方法： public static void arraycopy(Object src, int srcPos, Object dest, int destPos, int length)
        //数组拷贝，也就是将一个数组中的内容复制到另外一个数组中的指定位置，由于该方法是native方法，所以性能上比使用循环高效
        int[] a = {1,2,3,4};
        int[] b = new int[5];
        System.arraycopy(a,1,b,3,2);
        System.out.println(Arrays.toString(b));
        System.out.println("----------------");

        //currentTimeMillis方法:  public static long currentTimeMillis()
        //返回当前的计算机时间，时间的表达格式为当前计算机时间和GMT时间(格林威治时间)1970年1月1号0时0分0秒所差的毫秒数
        long start = System.currentTimeMillis();  //当前时间戳
        for(int i = 0;i < 100000000;i++){
            int c = 0;
        }
        long end = System.currentTimeMillis();
        long time = end-start;
        System.out.println("----------------");

        //getProperty方法: public static String getProperty(String key)
        //获得系统中属性名为key的属性对应的值
        String osName = System.getProperty("os.name");
        String user = System.getProperty("user.name");
        System.out.println("当前操作系统是：" + osName);
        System.out.println("当前用户是：" + user);
        System.out.println("各种路径-------");
        System.out.println("java.version: " + System.getProperty("java.version"));
        System.out.println("java.home: " + System.getProperty("java.home"));
        System.out.println("os.version: " + System.getProperty("os.version"));
        System.out.println("user.home: " + System.getProperty("user.home"));
        System.out.println("user.dir: " + System.getProperty("user.dir"));
        System.out.println("java.io.tmpdir: " + System.getProperty("java.io.tmpdir"));
        System.out.println(System.getProperty("sun.boot.class.path"));  //根类加载器Bootstrap ClassLoader加载的类路径
        System.out.println("--------------");

        System.out.println("系统默认编码：" + System.getProperty("file.encoding")) ;  //获取当前系统编码
        System.out.println(System.identityHashCode("asdfasd"));  //获取到对象的真实hashCode，即内存地址
        System.out.println(System.getenv("PATH"));   //获取环境变量

        //exit方法: public static void exit(int status)  退出程序。其中status的值为0代表正常退出，非零代表异常退出
        //gc方法: public static void gc() 请求系统进行垃圾回收。至于系统是否立刻回收，则取决于系统中垃圾回收算法的实现以及系统执行时的情况
    }
}
