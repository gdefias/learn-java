package JVM.MEM.OOM;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * Created by Defias on 2020/09.
 * Description: 演示本机直接内存溢出

 借助Unsafe类（Java魔法类）

 VM Args:-Xmx20M-XX:MaxDirectMemorySize=10M
 */
public class DirectMemoryOOM {
    private static final long _1MB = 1024*1024;

    public static void main(String[]args)throws Exception{
        Field unsafeField = Unsafe.class.getDeclaredFields()[0];
        unsafeField.setAccessible(true);
        Unsafe unsafe = (Unsafe)unsafeField.get(null);

        while(true) {
            unsafe.allocateMemory(_1MB);
        }
    }
}
