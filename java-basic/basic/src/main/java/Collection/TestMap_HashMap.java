package Collection;

/**
 * Created by Defias on 2017/8/27.
 *
 * HashMap
 *
 * HashMap是一个散列表，它存储的内容是键值对(key-value)映射
 * HashMap继承于AbstractMap，实现了Map、Cloneable、java.io.Serializable接口
 * HashMap的实现不是同步的，这意味着它不是线程安全的。它的key、value都可以为null。此外HashMap中的映射不是有序的
 *
 * HashTable：线程安全(方法是Synchronize的)  不允许有null的键和值
 * HashMap： Hashtable的轻量级实现（非线程安全的实现），他们都完成了Map接口
 */
import java.util.Map;
import java.util.Random;
import java.util.Iterator;
import java.util.HashMap;

public class TestMap_HashMap {
    public static void main(String[] args) {
        testHashMapAPIs();
    }

    private static void testHashMapAPIs() {
        //初始化随机种子
        Random r = new Random();

        //新建HashMap
        HashMap map = new HashMap();

        //添加操作
        map.put("one", r.nextInt(10));
        map.put("two", r.nextInt(10));
        map.put("three", r.nextInt(10));

        //打印出map
        System.out.println("map:"+map );

        //通过Iterator遍历key-value
        Iterator iter = map.entrySet().iterator();
        while(iter.hasNext()) {
            Map.Entry entry = (Map.Entry)iter.next();
            System.out.println("next : "+ entry.getKey() +" - "+entry.getValue());
        }

        //HashMap的键值对个数
        System.out.println("size:"+map.size());

        //containsKey(Object key) :是否包含键key
        System.out.println("contains key two : "+map.containsKey("two"));
        System.out.println("contains key five : "+map.containsKey("five"));

        //containsValue(Object value) :是否包含值value
        System.out.println("contains value 0 : "+map.containsValue(new Integer(0)));

        //remove(Object key) ： 删除键key对应的键值对
        map.remove("three");

        System.out.println("map:"+map );

        //clear() ： 清空HashMap
        map.clear();

        //isEmpty() : HashMap是否为空
        System.out.println((map.isEmpty()?"map is empty":"map is not empty") );
    }
}
