/**
 * Created by Defias on 2016/5/30.
 *
 * 集合与克隆
 */
package Basic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class JavaClone1 {
    public static void main(String[] args) throws CloneNotSupportedException {
        //test0();
        //test1();
        //test3();
        test4();
    }

    //拷贝Integer列表
    public static void test0()  {

        //申明list1时在要被克隆时需要使用具体的类型ArrayList而不能使用List
        ArrayList<Integer> list1 = new ArrayList<>();
        list1.add(11);
        list1.add(12);

        List<Integer> list2 = (List<Integer>)list1.clone();
        System.out.println(list2);
        System.out.println(list1==list2);
        System.out.println(list1.get(0)==list2.get(0));
    }

    //拷贝String列表 - 直接new
    public static void test1() {

        List<String> srcList = new ArrayList<>();
        srcList.add("张三");
        srcList.add("李四");
        srcList.add("王五");

        List<String> descList = new ArrayList<>(srcList);

        System.out.println(descList.size());
        System.out.println(descList==srcList);

        for (String desc : descList) {
            System.out.println(desc);
        }
    }


    //拷贝String列表  使用clone方法
    public static void test2() {
        List<String> srcList = new ArrayList<>();
        srcList.add("张三");
        srcList.add("李四");
        srcList.add("王五");


        //List<String> descList = new ArrayList<>(3); 这是不对的

        //可以这样
        List<String> descList = new ArrayList<>();
        descList.add(null);
        descList.add(null);
        descList.add(null);
        descList.add("赵六");


        //也可以这样
        //List<String> descList = Arrays.asList(new String[srcList.size()]);

        System.out.println(descList.size());

        //浅拷贝
        Collections.copy(descList, srcList);


        for (int i=0; i<descList.size(); i++) {
            String s = descList.get(i);
            System.out.println(s);
        }

    }

    //拷贝String列表  直接使用列表的addAll方法
    public static void test3() {

        List<String> srcList = new ArrayList<>();
        srcList.add("张三");
        srcList.add("李四");
        srcList.add("王五");

        List<String> descList = new ArrayList<>();
        descList.addAll(srcList);

        srcList.add("麻子");

        System.out.println(descList.size());
        for (String desc : descList) {
            System.out.println(desc);
        }
    }

    public static void test4() {

        List<String> srcList = new ArrayList<>();
        srcList.add("张三");
        srcList.add("李四");
        srcList.add("王五");

        List<List<String>> srcLists = new ArrayList<>();
        srcLists.add(srcList);


        List<List<String>> descList = new ArrayList<>();
        descList.addAll(srcLists);

        //srcList.add("麻子");

        System.out.println(descList.size());
        for (List<String> desc : descList) {
            System.out.println(desc);
        }
    }


}



