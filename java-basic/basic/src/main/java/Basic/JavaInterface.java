/**
 * Created by Defias on 2016/2/28.
 *
 * 接口
 *
 * 1、在抽象类中，可以包含一个或多个抽象方法；但在接口(interface)中，所有的方法必须都是抽象的，不能有方法体，它比抽象类更加“抽象”
 * 2、接口是若干常量和抽象方法的集合，接口本就是从抽象类中演化而来的，因而除特别规定，接口享有和类同样的“待遇”
 * 3、一个接口可以继承多个其他接口，接口的多继承特点弥补了类的单继承
 * 4、如果一个类实现接口时不能实现所有抽象方法，那么这个类必须被定义为抽象类
 *
 * 5、接口和抽象类各有优缺点，在接口和抽象类的选择上，遵守的原则：
 *    行为模型应该总是通过接口而不是抽象类定义，所以通常是优先选用接口，尽量少用抽象类
 *    选择抽象类的时候通常是如下情况：需要定义子类的行为，又要为子类提供通用的功能
 *
 * 6、类是对象的模板，抽象类和接口可以看做是具体的类的模板
 *
 */
package Basic;
import static java.lang.System.*;

public class JavaInterface {
    public void test1(AAA a) {
        a.doSth();
    }

    public static void main(String[] args) {
        SataHdd sh1 = new SeagateHdd(); //初始化希捷硬盘
        SataHdd sh2 = new SamsungHdd(); //初始化三星硬盘
        sh1.writeData("hello");
        sh2.writeData("hello");

        JavaInterface d = new JavaInterface();
        AAA a = new BBB();  //接口作为类型
        d.test1(a);

        SataHdd.tf();
    }
}

//可以在interface关键字前面添加public（但仅限于该接口在于其同名的文件中被定义）
//如果不添加public，则接口具有包访问权限，这样它就只能在同一个包内可用
interface AAA {
    public int doSth();
}


class BBB implements AAA {
    public int doSth() {
        System.out.println("now in B");
        return 123;
    }
}


//串行硬盘接口
interface SataHdd {
    //接口可以包含域，但是这些域隐式就是static和final的，但不能是空白的final的（必须要赋值）
    //连接线的数量
    public static final int CONNECT_LINE=4;

    //接口中只能定义抽象方法默认为public abstract的，在声明方法时可以省略这些修饰符
    //写数据
    public void writeData(String data);

    //读数据
    public String readData();

    //接口的可以定义静态方法
    public static void tf() {
        System.out.println("static function");
    }
}


// 维修硬盘接口
interface fixHdd {
    // 维修地址
    String address = "北京市海淀区";
    // 开始维修
    boolean doFix();
}


//希捷硬盘
class SeagateHdd implements SataHdd, fixHdd {  //类实现接口
    //希捷硬盘读取数据
    public String readData() { //实现(implements)接口的抽象方法
        return "数据";
    }
    //希捷硬盘写入数据
    public void writeData(String data) {
        out.println(data + "写入成功");
    }
    // 维修希捷硬盘
    public boolean doFix() {
        return true;
    }
}


//三星硬盘
class SamsungHdd implements SataHdd {
    //三星硬盘读取数据
    public String readData() {
        return "数据";
    }
    //三星硬盘写入数据
    public void writeData(String data) {
        out.println(data + "写入OK");
    }
}


//某劣质硬盘，不能写数据
abstract class XXHdd implements SataHdd {
    //硬盘读取数据
    public String readData() {
        return "数据";
    }
}