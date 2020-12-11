package Generics.element_generics;

public class PointOps {

    //泛型方法：独立于泛型类
    //泛型类型参数<T3, T4>位置： 修饰符后面，返回值类型前面
    public <T3, T4> void printPoint2(T3 x, T4 y) {
        T3 m = x;
        T4 n = y;
        System.out.println("This Pointi is：" + m + ", " + n);
    }
}
