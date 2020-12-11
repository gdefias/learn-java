package JVM.MEM.References;
import java.lang.ref.*;
import java.util.*;

/**
 * Created by Defias on 2020/07.
 * Description: 持有引用
 */

public class TestReferences {
    private static ReferenceQueue<VeryBig> rq = new ReferenceQueue<VeryBig>();

    public static void checkQueue() {
        Reference<? extends VeryBig> inq = rq.poll();
        if(inq != null)
            System.out.println("In queue: " + inq.get());
        else
            System.out.println("In queue: null");
    }

    public static void main(String[] args) throws Exception {
        int size = 10;
        // Or, choose size via the command line:
        if(args.length > 0)
            size = new Integer(args[0]);

        //软引用
        LinkedList<SoftReference<VeryBig>> sa = new LinkedList<SoftReference<VeryBig>>();
        for(int i = 0; i < size; i++) {
            sa.add(new SoftReference<VeryBig>(
                    new VeryBig("Soft " + i), rq));  //创建软引用同时关联了引用队列
            System.out.println("Just SoftReference created: " + sa.getLast());
            checkQueue();
        }

        //弱引用
        LinkedList<WeakReference<VeryBig>> wa = new LinkedList<WeakReference<VeryBig>>();
        for(int i = 0; i < size; i++) {
            wa.add(new WeakReference<VeryBig>(
                    new VeryBig("Weak " + i), rq));  //创建弱引用同时关联了引用队列
            System.out.println("Just WeakReference created: " + wa.getLast());
            checkQueue();
        }

        SoftReference<VeryBig> s =
                new SoftReference<VeryBig>(new VeryBig("Soft"));
        WeakReference<VeryBig> w =
                new WeakReference<VeryBig>(new VeryBig("Weak"));

        System.gc();

        //虚引用
        LinkedList<PhantomReference<VeryBig>> pa =
                new LinkedList<PhantomReference<VeryBig>>();

        for(int i = 0; i < size; i++) {
            pa.add(new PhantomReference<VeryBig>(
                    new VeryBig("Phantom " + i), rq));  //创建虚引用同时必须关联引用队列
            System.out.println("Just PhantomReference created: " + pa.getLast());
            checkQueue();
        }

        Thread.sleep(5000);
        System.out.println("-----------");
    }
}


class VeryBig {
    private static final int SIZE = 10000;
    private long[] la = new long[SIZE];
    private String ident;

    public VeryBig(String id) {
        ident = id;
    }

    public String toString() {
        return ident;
    }

    protected void finalize() {
        System.out.println("Finalizing " + ident);
    }
}