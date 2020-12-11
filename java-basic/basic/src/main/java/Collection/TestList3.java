package Collection;

import java.util.*;

/**
 * Created by Defias on 2020/07.
 * Description: 固定尺寸的容器
 */

public class TestList3 {
    public static void main(String[] args) {

        //Arrays.asList： 返回一个受指定数组支持的固定大小的列表（仅支持不改变数组大小的操作）
        List<String> list = Arrays.asList("A B C D E F G H I J K L".split(" "));

        test("Modifiable Copy", new ArrayList<String>(list));

        test("Arrays.asList()", list);

        //Collections.unmodifiableList：返回指定列表的不可修改列表（内部将容器包装到一个代理中）
        test("unmodifiableList()", Collections.unmodifiableList(
                        new ArrayList<String>(list)));
    }


    static void test(String msg, List<String> list) {
        System.out.println("--- " + msg + " ---");
        Collection<String> c = list;
        Collection<String> subList = list.subList(1,8);
        // Copy of the sublist:
        Collection<String> c2 = new ArrayList<String>(subList);
        try { c.retainAll(c2); } catch(Exception e) {
            System.out.println("retainAll(): " + e);
        }
        try { c.removeAll(c2); } catch(Exception e) {
            System.out.println("removeAll(): " + e);
        }
        try { c.clear(); } catch(Exception e) {
            System.out.println("clear(): " + e);
        }
        try { c.add("X"); } catch(Exception e) {
            System.out.println("add(): " + e);
        }
        try { c.addAll(c2); } catch(Exception e) {
            System.out.println("addAll(): " + e);
        }
        try { c.remove("C"); } catch(Exception e) {
            System.out.println("remove(): " + e);
        }
        // The List.set() method modifies the value but
        // doesn't change the size of the data structure:
        try {
            list.set(0, "X");
        } catch(Exception e) {
            System.out.println("List.set(): " + e);
        }
    }
}
