package Collection;
import org.apache.commons.collections.CollectionUtils;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

//Arrays.asList的陷阱


public class TestArraysasList {
    public static void main(String[] args) {

        //Arrays.asList()是泛型方法，传入的对象必须是对象数组
        //当传入一个原生数据类型数组时，Arrays.asList() 的真正得到的参数就不是数组中的元素，而是数组对象本身！此时List的唯一元素就是这个数组

        int[] myArray = {1, 2, 3};
        List myList = Arrays.asList(myArray);
        System.out.println(myList.size()); //1
        System.out.println(myList.get(0)); //数组地址值
        //System.out.println(myList.get(1)); //报错：ArrayIndexOutOfBoundsException
        int[] array = (int[]) myList.get(0);
        System.out.println(array[0]); //1

        //Arrays.asList() 方法返回的并不是 java.util.ArrayList ，而是 java.util.Arrays 的一个内部类,这个内部类并没有实现集合的修
        //改方法或者说并没有重写这些方法
        System.out.println(myList.getClass()); //class java.util.Arrays$ArrayList


        System.out.println("-------------");

        //Array To List
        String[] ss = {"Tom", "Mike", "Jake"};
        List<String> sslist = Arrays.asList(ss);  //把数组包装成一个list对象

        sslist.set(0, "Jane"); //合法
        System.out.println(Arrays.toString(ss));
        System.out.println(sslist);

        //sslist.remove("Mike");  //因长度不能改变，因此非法
        //sslist.add("Mary");   //因长度不能改变，因此非法
        //sslist.clear();

        //原因: Arrays.asList仅仅体现适配器模式，只是转换接口，后台数据仍然是数组

        List<String> sslist2 = new ArrayList<>(Arrays.asList(ss));
        sslist2.set(0, "Jane"); //合法
        sslist2.remove("Mike"); //合法
        sslist2.add("Mary"); //合法
        System.out.println(sslist2);

        System.out.println("---------------");

        //如何正确的将数组转换为ArrayList?

        //2. 最简便的方法(推荐)
        List list = new ArrayList<>(Arrays.asList("a", "b", "c"));
        System.out.println(list);


        //3. 使用 Java8 的Stream(推荐)
        Integer [] myArray1 = { 1, 2, 3 };
        List myList1 = Arrays.stream(myArray1).collect(Collectors.toList());

        //基本类型也可以实现转换（依赖boxed的装箱操作）
        int[] myArray2 = { 1, 2, 3 };
        List myList2 = Arrays.stream(myArray2).boxed().collect(Collectors.toList());

        //4. 使用 Guava(推荐)
        //对于不可变集合，你可以使用ImmutableList类及其of()与copyOf()工厂方法：（参数不能为空）
        //List<String> i1 = ImmutableList.of("string", "elements");  // from varargs
        //List<String> i2 = ImmutableList.copyOf(aStringArray);      // from array
        //
        ////对于可变集合，你可以使用Lists类及其newArrayList()工厂方法：
        //List<String> l1 = Lists.newArrayList(anotherListOrCollection);    // from collection
        //List<String> l2 = Lists.newArrayList(aStringArray);               // from array
        //List<String> l3 = Lists.newArrayList("or", "string", "elements"); // from varargs

        //5.使用Java9的List.of()方法
        //Integer[] array5 = {1, 2, 3};
        //List<Integer> list5 = List.of(array5);
        //System.out.println(list); /* [1, 2, 3] */
        /* 不支持基本数据类型 */


    }


    //1. 自己动手实现
    static <T> List<T> arrayToList(final T[] array) {
        final List<T> l = new ArrayList<T>(array.length);

        for (final T s : array) {
            l.add(s);
        }
        return l;
    }
}
