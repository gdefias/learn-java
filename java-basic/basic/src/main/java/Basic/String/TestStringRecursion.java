package Basic.String;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Defias on 2020/07.
 * Description: 无意识的递归调用
 *
 */


//打印对象的内存地址
public class TestStringRecursion {

    //覆写Object的toString
    public String toString() {

        //这里发生自动类型转换，将TestStringRecursion类型转换为String，而转为String又依赖当前方法toString，因此发生递归调用而引发异常
        //return " InfiniteRecursion address: " + this + "\n";

        //正确的做法
        return " InfiniteRecursion address: " + super.toString() + "\n";
    }

    public static void main(String[] args) {
        List<TestStringRecursion> v = new ArrayList<TestStringRecursion>();
        for(int i = 0; i < 10; i++)
            v.add(new TestStringRecursion());
        System.out.println(v);
    }
}
