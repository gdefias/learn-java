package TypeInfo;

/**
 * Created by Defias on 2020/08.
 * Description: Java基本数据类型的包装类的YPE字段

 对于基本数据类型的包装类，有一个标准的字段TYPE，是一个引用，指向对应的基本数据类型的Class对象
 如：Integer.TYPE 等价于 int.class
    Void.TYPE 等价于 void.class
    Boolean.TYPE 等价于 boolean.class
    ...
 */
public class TestTYPE {
    public static void main(String[] args) {
        test0();
    }

    public static void test0() {
        //基本数据类型的Class对象
        Class<?> intclass = int.class;
        Class<?> shortclass = short.class;
        Class<?> byteclass = byte.class;
        Class<?> longclass = long.class;
        Class<?> doubleclass = double.class;
        Class<?> floatclass = float.class;
        Class<?> booleanclass = boolean.class;


        Class<?> Integertype = Integer.TYPE;
        Class<?> Shorttype = Short.TYPE;
        Class<?> Bytetype = Byte.TYPE;
        Class<?> Longtype = Long.TYPE;
        Class<?> Doubletype = Double.TYPE;
        Class<?> Floattype = Float.TYPE;
        Class<?> Booleantype = Boolean.TYPE;

        //基本数据类型的包装类的Class对象
        Class<?> Integerclass = Integer.class;
        Class<?> Shortclass = Short.class;
        Class<?> Byteclass = Byte.class;
        Class<?> Longclass = Long.class;
        Class<?> Doubleclass = Double.class;
        Class<?> Floatclass = Float.class;
        Class<?> Booleanclass = Boolean.class;


        try {
            //运行时错误  基本数据类型的Class对象是没有newInstance方法的
            //Object into = intclass.newInstance();
            //Object Integero1 = Integerclass.newInstance();
            //Object Integero2 = Integertype.newInstance();

            System.out.println(intclass.hashCode());
            System.out.println(Integertype.hashCode());
            System.out.println(Integerclass.hashCode());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
