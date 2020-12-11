package Collection.hashcode;

import java.util.*;

/**
 * Created by Defias on 2020/07.
 * Description: 生成散列码
 */

public class TestHashCode2 {
    private static List<String> created = new ArrayList<String>();
    private String s;
    private int id = 0;

    public static void main(String[] args) {

        //String的一个特点：多个包含相同字符序列的字符串对象都映射到同一块内存区域
        //虽然它们相互独立，但它们的hashcode生成同样的结果
        String[] hellos = "Hello Hello".split(" ");
        System.out.println(hellos[0].hashCode());
        System.out.println(hellos[1].hashCode());


        Map<TestHashCode2,Integer> map = new HashMap<TestHashCode2,Integer>();
        TestHashCode2[] cs = new TestHashCode2[5];
        for(int i = 0; i < cs.length; i++) {
            cs[i] = new TestHashCode2("hi");
            map.put(cs[i], i); // Autobox int -> Integer
        }
        System.out.println(map);
        for(TestHashCode2 cstring : cs) {
            System.out.println("Looking up " + cstring);
            System.out.println(map.get(cstring));
        }
    }


    public TestHashCode2(String str) {
        s = str;
        created.add(s);
        //id：统计当前created中当前字符串的数量
        for(String s2 : created)
            if(s2.equals(s))
                id++;
    }

    public String toString() {
        return "【String: " + s + " id: " + id +
                " hashCode(): " + hashCode() + "】";
    }

    public int hashCode() {
        int result = 17;
        result = 37 * result + s.hashCode();
        result = 37 * result + id;
        return result;
    }
    public boolean equals(Object o) {
        return o instanceof TestHashCode2 &&
                s.equals(((TestHashCode2)o).s) &&
                id == ((TestHashCode2)o).id;
    }
}