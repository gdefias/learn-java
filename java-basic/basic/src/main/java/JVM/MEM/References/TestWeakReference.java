package JVM.MEM.References;
import java.lang.ref.WeakReference;

/**
 * Created by Defias on 2020/07.
 * Description: 弱引用
 *
 */
public class TestWeakReference {
    public static void main(String[] args){

        WeakReference<String[]> weakReference = new WeakReference<String[]>(new String[1000]);

        System.out.println(weakReference.get());

        //执行一次gc,即使目前JVM的内存够用,但还是回收仅被弱引用指向的对象
        System.gc();
        System.out.println(weakReference.get());
    }
}
