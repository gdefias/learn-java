package JVM.MEM.References;
import java.util.*;

/**
 * Created by Defias on 2020/07.
 * Description: WeakHashMap的特性演示
 */

public class TestWeakHashMap {
    public static void main(String[] args) throws Exception {

        test1();
        //test2(args);
    }

    public static void test1() throws Exception {
        Map wmap = new WeakHashMap();

        String w1 = new String("one");
        String w2 = new String("two");
        String w3 = new String("three");

        //WeakHashMap的方法
        wmap.put(w1, "w1");
        wmap.put(w2, "w2");
        wmap.put(w3, "w3");
        System.out.printf("wmap:%s\n",wmap);
        System.out.printf("contains key two : %s\n",wmap.containsKey("two"));
        System.out.printf("contains key five : %s\n",wmap.containsKey("five"));
        System.out.printf("contains value 0 : %s\n",wmap.containsValue(new Integer(0)));
        wmap.remove("three");

        System.out.printf("wmap: %s\n",wmap );

        // 迭代遍历WeakHashMap
        Iterator iter = wmap.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry en = (Map.Entry)iter.next();
            System.out.printf("%s=%s\n",en.getKey(),en.getValue());
        }
        System.out.println("-------------------");

        //WeakHashMap的自动回收特性
        w1 = null;
        w2 = null;

        //此时可能还未执行gc,所以可能还可以通过仅有弱引用的key找到value
        System.out.println(wmap.get("one"));
        System.out.println(wmap.get("two"));
        System.out.printf("befor gc WeakHashMap size:%s\n", wmap.size());

        System.out.println("-------------------");

        //执行gc,导致仅有弱引用的key对应的entry(包括value)全部被回收
        System.gc();
        System.out.println(wmap.get("one"));
        System.out.println(wmap.get("two"));

        Thread.sleep(5000);
        System.out.printf("after gc WeakHashMap size:%s\n", wmap.size());
    }


    public static void test2(String[] args) throws Exception {
        int size = 1000;
        // Or, choose size via the command line:
        if(args.length > 0)
            size = new Integer(args[0]);

        Key[] keys = new Key[size];
        WeakHashMap<Key,Value> map = new WeakHashMap<Key,Value>();
        for(int i = 0; i < size; i++) {
            Key k = new Key(Integer.toString(i));
            Value v = new Value(Integer.toString(i));
            if(i % 3 == 0)  //每隔3个保存一个key的引用为强引用
                keys[i] = k; // Save as "real" references
            map.put(k, v);
        }
        System.gc();  //垃圾回收器GC回收WeakHashMap中的key，每隔3个就跳过一个

        Thread.sleep(5000);
        System.out.println("-----------");
    }
}


class Element {
    private String ident;

    public Element(String id) {
        ident = id;
    }

    public String toString() {
        return ident;
    }

    public int hashCode() {
        return ident.hashCode();
    }

    public boolean equals(Object r) {
        return r instanceof Element &&
                ident.equals(((Element)r).ident);
    }

    //当垃圾回收器准备回收某对象的内存时，将自动调用该对象的finalize方法
    protected void finalize() {
        System.out.println("Finalizing " + getClass().getSimpleName() + " " + ident);
    }
}

class Key extends Element {
    public Key(String id) {
        super(id);
    }
}

class Value extends Element {
    public Value(String id) {
        super(id);
    }
}