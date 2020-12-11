package TypeInfo.Reflect;
import java.lang.reflect.*;
/**
 * Created by Defias on 2017/8/23.
 *
 * 数组
 */

public class TestReflectionArray {
    public static void main(String[] args) {
        //通过反射取得数组的信息
        int[] temp = {1,2,3,4,5};
        Class<?> dem = temp.getClass();
        System.out.println(dem.getTypeName());

        Class<?> demo = temp.getClass().getComponentType();
        System.out.println("数组成员类型： " + demo.getTypeName());
        System.out.println("数组长度  " + Array.getLength(temp));
        System.out.println("数组的第一个元素: " + Array.get(temp, 0));

        //通过反射修改数组大小
        System.out.println("原数组长度： " + temp.length);
        int[] newTemp = (int[])arrayInc(temp, 15);
        System.out.print("修改后");
        print(newTemp);

    }

    //通过反射修改数组大小
    public static Object arrayInc(Object obj, int len){
        Class<?> arr = obj.getClass().getComponentType();
        Object newArr = Array.newInstance(arr, len);
        int co = Array.getLength(obj);
        System.arraycopy(obj, 0, newArr, 0, co);
        return newArr;
    }

    //通过反射打印数组
    public static void print(Object obj){
        Class<?> c = obj.getClass();
        if(!c.isArray()){
            return;
        }
        System.out.println("数组长度为： "+Array.getLength(obj));
        for (int i=0; i < Array.getLength(obj); i++) {
            System.out.print(Array.get(obj, i)+" ");
        }
    }
}
