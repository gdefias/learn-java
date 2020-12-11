package Collection;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: Defias
 * @date: 2020/11/30
 * @description:
 */
public class TestListtoArray {
    public static void main(String[] args) throws Exception {
        List<String> list = new ArrayList<String>();
        list.add("A");
        list.add("B");
        list.add("C");

        //String[] array = (String[])list.toArray();  //ClassCastException
        String[] array = list.toArray(new String[list.size()]);  //最正确的用法
        //String[] array = list.toArray(new String[0]);

        System.out.println(array[0]);
        System.out.println(array.length);
    }
}
