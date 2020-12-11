package JVM.MEM.GC;

/**
 * Created by Defias on 2020/09.
 * Description: 内存分配与回收策略
 *
 */

public class TestMemGC {
    private static final int _1MB = 1024*1024;


    public static void main(String[] args) {
        //testAllocation();

        //testPretenureSizeThreshold();

        testTenuringThreshold();
    }

    /**
     * 对象优先在Eden分配
     * VM参数：-verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8
     */
    public static void testAllocation(){
        byte[]allocation1, allocation2, allocation3, allocation4;
        allocation1=new byte[2*_1MB];
        allocation2=new byte[2*_1MB];
        allocation3=new byte[2*_1MB];
        allocation4=new byte[4*_1MB];//出现一次Minor GC

    }


    /**
     * 大对象直接进入老年代
     * VM参数：-verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8 -XX:PretenureSizeThreshold=3145728
     */
    public static void testPretenureSizeThreshold() {
        byte[] allocation;
        allocation = new byte[4*_1MB]; //直接分配在老年代中
    }

    
    /**
     * 长期存活的对象将进入老年代
     * 如果对象在Eden出生并经过第一次Minor GC后仍然存活，并且能被Survivor容纳的话，将被移动到Survivor空间中，并且对象年龄设为1。
     * 对象在Survivor区中每“熬过”一次Minor GC，年龄就增加1岁，当它的年龄增加到一定程度（默认为15岁），就将会被晋升到老年代中。
     * 对象晋升老年代的年龄阈值，可以通过参数-XX:MaxTenuringThreshold设置
     *
     * VM参数：-verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8 -XX:MaxTenuringThreshold=1
     * -XX:+PrintTenuringDistribution
     */
    @SuppressWarnings("unused")
    public static void testTenuringThreshold() {
        byte[]allocation1, allocation2, allocation3;
        allocation1 = new byte[_1MB/4];

        //什么时候进入老年代取决于XX:MaxTenuringThreshold设置
        allocation2 = new byte[4*_1MB];
        allocation3 = new byte[4*_1MB];  //第一次GC
        allocation3 = null;
        allocation3 = new byte[4*_1MB];  //第二次GC
    }
    
    /**
     * 动态对象年龄判定
     * 虚拟机并不是永远地要求对象的年龄必须达到了MaxTenuringThreshold才能晋升老年代，如果在Survivor空间中相同年龄所有对象大小的总
     * 和大于Survivor空间的一半，年龄大于或等于该年龄的对象就可以直接进入老年代，无须等到MaxTenuringThreshold中要求的年龄
     *
     * VM参数：-verbose:gc-Xms20M-Xmx20M-Xmn10M-XX:+PrintGCDetails-XX:SurvivorRatio=8-XX:MaxTenuringThreshold=15
     * -XX:+PrintTenuringDistribution
     */
    @SuppressWarnings("unused")
    public static void testTenuringThreshold2() {
        byte[]allocation1, allocation2, allocation3, allocation4;
        allocation1=new byte[_1MB/4];

        //allocation1+allocation2大于survivo空间一半
        allocation2=new byte[_1MB/4];
        allocation3=new byte[4*_1MB];
        allocation4=new byte[4*_1MB];
        allocation4=null;
        allocation4=new byte[4*_1MB];
    }
}
