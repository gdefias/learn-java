package JVM.MEM.GC;

/**
 * Created by Defias on 2020/09.
 * Description: testGC()方法执行后, objA和objB会不会被GC呢?


 如果是引用计数：因为互相引用着对方，导致它们的引用计数都不为0，于是引用计数算法无法通知GC收集器回收它们

 实际运行结果，GC日志中包含"4603K-＞210K"，意味着虚拟机并没有因为这两个对象互相引用就不回收它们
 从侧面说明Hotspot虚拟机并不是通过引用计数算法来判断对象是否存活的
 */
public class ReferenceCountingGC {
    public Object instance = null;
    private static final int _1MB = 1024*1024;

    /**
     *这个成员属性的唯一意义就是占点内存, 以便能在GC日志中看清楚是否被回收过
     */
    private byte[]bigSize = new byte[2*_1MB];

    public static void testGC(){
        ReferenceCountingGC objA = new ReferenceCountingGC();
        ReferenceCountingGC objB = new ReferenceCountingGC();
        objA.instance = objB;
        objB.instance = objA;
        objA = null;
        objB = null;

        //假设在这行发生GC,objA和objB是否能被回收?
        System.gc();
        System.out.println("end");
    }

    public static void main(String[] args) {
        testGC();
    }
}