package TypeInfo;

/**
 * Created by Defias on 2017/8/16.
 *
 * 获得Class对象的三种方法
 */

public class TestClass {

    public  static void main(String[] args) throws ClassNotFoundException {

        test0();
        //test1();
        //test2();
    }

    public static void test0() throws ClassNotFoundException {
        Class c2 = Class.forName("[D"); 	//double[]
        System.out.println(c2.getCanonicalName());

        Class c3 = Class.forName("[[Ljava.lang.String;");  //java.lang.String[][]
        System.out.println(c3.getCanonicalName());
    }

    public static void test1() {
        try {
            Class testTypeForName = Class.forName("TypeInfo.TestClassType");
            System.out.println("testForName---" + testTypeForName);
            System.out.println("");

            Class testTypeClass = TestClassType.class;
            System.out.println("testTypeClass---" + testTypeClass);
            System.out.println("");

            TestClassType testGetClass = new TestClassType();
            Class testTypegetClass = testGetClass.getClass();
            System.out.println("testGetClass---" + testTypegetClass);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void test2() {
        System.out.println("inside main");
        new Candy();
        System.out.println("After creating Candy");
        try {
            Class.forName("Gum");
        } catch(ClassNotFoundException e) {
            System.out.println("Couldn't find Gum");
        }
        System.out.println("After Class.forName(\"Gum\")");
        new Cookie();
        System.out.println("After creating Cookie");


    }

}


class TestClassType {
    public TestClassType(){
        System.out.println("----构造函数---");
    }

    //静态的参数初始化
    static {
        System.out.println("---静态的参数初始化---");
    }

    //非静态的参数初始化
    {
        System.out.println("----非静态的参数初始化---");
    }

}



class Candy {
    static { System.out.println("Loading Candy"); }  //类被加载时会执行
}

class Gum {
    static { System.out.println("Loading Gum"); }
}

class Cookie {
    static { System.out.println("Loading Cookie"); }
}