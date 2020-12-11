package Basic.Enum;
/**
 * Created by Defias on 2017/3/8.
 *
 * 自定义枚举
 */

public class TestEnum2 {
    public static void main(String[] args) {
        //test1();
        test2();
    }

    public static void test1() {
        //输出某一枚举的值
        System.out.println(Colorr.RED.getName());
        System.out.println(Colorr.RED.getIndex());
        System.out.println(Colorr.RED);
        System.out.println(Colorr.contains("test"));
        System.out.println(Colorr.getEnumByValue("红色"));

        //遍历所有的枚举
        for(Colorr color : Colorr.values()) {
            System.out.println( color + "  name: " + color.getName() + "  index: " + color.getIndex() );
        }
    }

    public static void test2(){
        System.out.println(Comenum.valueOf("A"));
        System.out.println(Comenum.A);
        for (Comenum c: Comenum.values()) {
            System.out.println(c.getValue());
        }
    }
}



enum Colorr {
    RED("红色", 1), GREEN("绿色", 2), BLANK("白色", 3),
    YELLO("黄色", 4), YELLO2("黄色2", 5), YELLO3("黄色3", 6),
    YELLO4("黄色4", 7);  //枚举列表必须写在最前面，否则编译出错，相当于该枚举类的实例

    private String name ;
    private int index ;

    //private构造函数
    private Colorr(String name , int index) {
        this.name = name ;
        this.index = index ;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    //通过值获取枚举
    public static Colorr getEnumByValue(String value) {
        for (Colorr c : Colorr.values()) {
            if (c.getName().equals(value)) {
                return c;
            }
        }
        return null;
    }

    ////覆盖方法
    //@Override
    //public String toString() {
    //    return this.index+"_"+this.name;
    //}

    //判断某个值是否是枚举中包含的
    public static boolean contains(String test) {
        for (Colorr c : Colorr.values()) {
            if (c.getName().equals(test)) {
                return true;
            }
        }
        return false;
    }
}


enum Comenum {
    A("hello", new interc()),
    B("world", new interc());

    private String value;
    private interc value2;

    private Comenum(String value, interc value2){
        this.value = value;
        this.value2 = value2;
    }

    public String getValue(){
        return value;
    }

//    @Override
//    public String toString() {
//        return value;
//    }
}

class interc { }