package Collection;
import java.util.*;

/**
 * Created by Defias on 2017/6/8.
 *
 * 数组与ArrayList
 */
public class TestArrayList2 {

    public static void main(String[] args) {

        //ArrayList类型的数据转换为String[]
        List<String> list = new ArrayList<String>();
        list.add("王利虎");
        list.add("张三");
        list.add("李四");
        int size=list.size();
        String[] array = (String[])list.toArray(new String[size]);
        for(int i=0;i<array.length;i++){
            System.out.println(array[i]);
        }


        //数组转换成为List（对int等基本类型不行）
        String[] array1 = new String[3];
        array1[0]="王利虎";
        array1[1]="张三";
        array1[2]="李四";
        List<String> list1 = Arrays.asList(array1);
        for(int i=0;i<list1.size();i++){
            System.out.println(list1.get(i));
        }

        List<String> list2 = Arrays.asList("zhangsan", "lisi", "wangwu");
        for(int i=0; i<list2.size(); i++){
            System.out.println(list2.get(i));
        }
        //list2.add("aaaaa");       //error
        //System.out.println(list2);


        List<Snow> snow1 = Arrays.asList(new Crusty(), new Slush(), new Powder());

        // Won't compile:
        // List<Snow> snow2 = Arrays.asList(new Light(), new Heavy());  //这样是不行的
        // Compiler says:
        // found   : java.util.List<Powder>
        // required: java.util.List<Snow>

        // Collections.addAll() doesn't get confused:
        List<Snow> snow3 = new ArrayList<Snow>();
        Collections.addAll(snow3, new Light(), new Heavy());   //这样就可以了

        // Give a hint using an explicit type argument specification:
        List<Snow> snow4 = Arrays.<Snow>asList(new Light(), new Heavy());  //这样也是可以的
    }

}

class Snow {}
class Powder extends Snow {}
class Light extends Powder {}
class Heavy extends Powder {}
class Crusty extends Snow {}
class Slush extends Snow {}