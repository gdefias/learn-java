package Collection;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

import static Basic.Print.print;
import static Basic.Print.printnb;

/**
 * Created by Jeff on 2016/3/5.
 *
 * 映射表Map接口
 * 一种依照键/值对存储元素的容器，提供了通过键快速获取、删除和更新键/值对的功能，键可以是任意类型的对象，不能有重复的键，每个键都对应一个值
 *
 *
 * 实现Map接口的3个具体类：
 * 散列映射表HashMap   --采用散列实现（数组+链表/红黑树）
 * 链式散列映射表LinkedHashMap（数组+链表/红黑树+双向链表结构）
 * 树形映射表TreeMap
 *
 java.util.Map<K,V>接口
 --------------------------------------------------------------------------------------------------
 +clear(): void   从该映射表中删除所有条目
 +containsKey(key: Object): boolean   如果该映射表包含了指定键的条目，则返回true
 +containsValue(value: Object): boolean  如果该映射表将一个或者多个键映射到指定值，则返回true
 +entrySet(): Set<Map.Entry<K,V>>   返回一个包含了该映射表中条目的集合
 +get(key: Object): V   返回该映射表中指定建对应的值
 +isEmpty(): boolean   如果该映射表中没有包含任何条目，则返回true
 +keySet(): Set<K>   返回一个包含该映射表中所有键的集合
 +put(key: K, value: V): V   将一个条目放入该映射表中
 +putAll(m: Map<? extends K,? extends V>): void   将m中的所有条目添加到该映射表中
 +remove(key: Object): V   删除指定键对应的条目
 +size(): int   返回该映射表中的条目数
 +values(): Collection<V>   返回该映射表中所有值组成的合集


 java.util.Map.Entry<K,V>接口
 --------------------------------------------------------------------------------------------------
 +getKey(): K   返回该条目的键
 +getValue(): V   返回该条目的值
 +setValue(value: V): void    将该条目中的值赋以新的值



 java.util.SortedMap<K,V>接口 有序
 --------------------------------------------------------------------------------------------------
 +firstKey(): K
 +lastKey(): K
 +comparator(): Comparator<? super K>)
 +headMap(toKey: K): SortedMap<K,V>
 +tailMap(fromKey: K): SortedMap<K, V>


 java.util.NavigableMap<K, V>接口  扩展的SortedMap，具有了针对给定搜索目标返回最接近匹配项的导航方法  可以按照键的升序或降序访问和遍历NavigableMap
 --------------------------------------------------------------------------------------------------
 +floorKey(key: K): K
 +ceilingKey(key: K): K
 +lowerKey(key: K): K
 +higherKey(key: K): K
 +pollFirstEntry(): Map.EntrySet<K, V>
 +pollLastEntry(): Map.EntrySet<K, V>



 具体实现类：

 java.util.HashMap<K,V>类  无序  线程不安全
 --------------------------------------------------------------------------------------------------
 +HashMap()
 +HashMap(initialCapacity: int,loadFactor: float)
 +HashMap(m: Map<? extends K, ? extends V>)


 java.util.LinkedHashMap<K,V>类  有序（可分为插入顺序和访问顺序两种）  按照插入顺序排序   继承于HashMap 双向链表来实现的  线程不安全  允许null key or value
 --------------------------------------------------------------------------------------------------
 +LinkedHashMap()
 +LinkedHashMap(m: Map<? extends K,? extends V>)
 +LinkedHashMap(initialCapacity: int, loadFactor: float, accessOrder: boolean)  //访问顺序为true； 插入顺序为false（默认）


 java.util.TreeMap<K,V>  有序 实现了SortedMap接口保证了有序性 默认排序是根据key进行升序排序的，也可以重写comparator方法   红黑树实现 线程不安全
 --------------------------------------------------------------------------------------------------
 +TreeMap()
 +TreeMap(m: Map<? extends K,? extends V>)
 +TreeMap(c: Comparator<? super K>)

 * WeakHashMap 弱键映射
 * ConcurrentHashMap： 一种线程安全的Map，它不涉及同步加锁
 * IdentityHashMap：使用==代替equals进行比较的散列映射
 */

public class TestMap {

	public static void main(String[] args) {

        //test0();
        test1();
//        test2();
//        sortForValue();
	}

	public static void test0() {
        Random rand = new Random(47);
        Map<Integer,Integer> m =
                new HashMap<Integer,Integer>();
        for(int i = 0; i < 10000; i++) {
            // Produce a number between 0 and 20:
            int r = rand.nextInt(20);
            Integer freq = m.get(r);
            m.put(r, freq == null ? 1 : freq + 1);  //统计0~19内的随机数的重复度
        }
        System.out.println(m);
    }

	public static void test1() {
        //HashMap
        Map<String, Integer> map = new HashMap<String, Integer>();
        map.put("语文", 1);
        map.put("数学", 2);
        map.put("英语", 3);
        map.put("历史", 4);
        map.put("政治", 5);
        map.put("地理", 6);
        map.put("生物", 7);
        map.put("化学", 8);
        map.put(null, 9);  //允许null value和null key
        map.put("几何", null);
        map.put("化学", 18);
        map.put("abc d ef", 999);

        System.out.println(map.get(null));  //通过键拿到对应的值

        //entrySet: Returns a Set view of the mappings contained in this map  Set<Map.Entry<K,V>>
        //entrySet方法返回一个set集合，这个集合中存放了Map.Entry类型的元素，每个Map.Entry对象代表Map中的一对键与值
        for(Map.Entry<String, Integer> entry : map.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }

        System.out.println(map);

        System.out.println(map.containsKey("abc d ef"));
        System.out.println(map.containsValue(999));

        System.out.println(map.values());  //Map中的值集合

        System.out.println("---------------------");

        //TreeMap
        Map<Integer, String> map2 = new TreeMap<Integer, String>();  //装入时map2中会进行自然排序，也可以指定排序方法作为参数
        map2.put(1, "语文");
        map2.put(3, "英语");
        map2.put(4, "历史");
        map2.put(2, "数学");


        Set<Integer> keys = map2.keySet();  //返回集合中所有键的集合
        Iterator<Integer> it = keys.iterator();  //迭代器
        while(it.hasNext()) {
            int key = it.next();
            String value = map2.get(key);
            System.out.println(key + ": " + value);
        }

        // Create a TreeMap from the previous HashMap
        //Map<String, Integer> map3 = new TreeMap<String, Integer>(map);
        //System.out.println("\nDisplay entries in ascending order of key");
        //System.out.println(map3);

        System.out.println("---------------------");

        //LinkedHashMap
        Map<String, Integer> linkedHashMap = new LinkedHashMap<String, Integer>(16, 0.75f, true);
        linkedHashMap.put("Smith", 30);
        linkedHashMap.put("Anderson", 31);
        linkedHashMap.put("Lewis", 29);
        linkedHashMap.put("Cook", 29);

        // Display the age for Lewis
        System.out.println("The age for " + "Lewis is " + linkedHashMap.get("Lewis").intValue());

        System.out.println("\nDisplay entries in LinkedHashMap");
        System.out.println(linkedHashMap);

        //使用Map数据生成器CountingMapData
        LinkedHashMap<Integer,String> linkedMap = new LinkedHashMap<Integer,String>(
                        new CountingMapData(9));
        System.out.println(linkedMap);
        // Least-recently-used order:
        linkedMap = new LinkedHashMap<Integer,String>(16, 0.75f, true);
        linkedMap.putAll(new CountingMapData(9));
        System.out.println(linkedMap);
        for(int i = 0; i < 6; i++) // Cause accesses:
            linkedMap.get(i);
        System.out.println(linkedMap);
        linkedMap.get(0);
        System.out.println(linkedMap);

    }

	public static void test2() {
        //Map<String, Object> map = new HashMap<String, Object>();
        //
        //int[] res = new int[] {12, 34};
        //map.put("res", res);
        //int vv = ((int[])map.get("res"))[1];
        //System.out.println(vv);

        Map<String, Integer> map2 = new HashMap<String, Integer>();
        map2.put("abc", 1);
        map2.put("abc", map2.get("abc")+1);
        System.out.println(map2.get("abc"));

    }

    //Map操作
    public static void test3() {
        test(new HashMap<Integer,String>());
        test(new TreeMap<Integer,String>());
        test(new LinkedHashMap<Integer,String>());
        test(new IdentityHashMap<Integer,String>());
        test(new ConcurrentHashMap<Integer,String>());
        test(new WeakHashMap<Integer,String>());
    }


    public static void printKeys(Map<Integer,String> map) {
        printnb("Size = " + map.size() + ", ");
        printnb("Keys: ");
        print(map.keySet().toString()); // Produce a Set of the keys
    }

    public static void test(Map<Integer,String> map) {
        print(map.getClass().getSimpleName());
        map.putAll(new CountingMapData(25));
        // Map has 'Set' behavior for keys:
        map.putAll(new CountingMapData(25));
        printKeys(map);
        // Producing a Collection of the values:
        printnb("Values: ");
        print(map.values().toString());
        print(map.toString());
        print("map.containsKey(11): " + map.containsKey(11));
        print("map.get(11): " + map.get(11));
        print("map.containsValue(\"F0\"): "
                + map.containsValue("F0"));
        Integer key = map.keySet().iterator().next();
        print("First key in map: " + key);
        map.remove(key);
        printKeys(map);
        map.clear();
        print("map.isEmpty(): " + map.isEmpty());
        map.putAll(new CountingMapData(25));
        // Operations on the Set change the Map:
        map.keySet().removeAll(map.keySet());
        print("map.isEmpty(): " + map.isEmpty());
    }

    //根据HashMap中的值将其元素排序
    public static void sortForValue() {
        Map<String, Integer> map = new HashMap<String, Integer>();
        map.put("语文", 10);
        map.put("数学", 22);
        map.put("英语", 32);
        map.put("历史", 43);
        map.put("政治", 61);
        map.put("地理", 61);
        map.put("生物", 777);
        map.put("化学", 89);

        List<Map.Entry<String, Integer>> list = new ArrayList<>();
        for(Map.Entry<String, Integer> entry : map.entrySet()) {
            list.add(entry);
        }

        list.sort(new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                return o2.getValue()-o1.getValue();
            }
        });

        for(Map.Entry<String, Integer> entry: list) {
            System.out.println(entry.getKey());
        }
    }
}
