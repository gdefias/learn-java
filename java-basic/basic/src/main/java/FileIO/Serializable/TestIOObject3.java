package FileIO.Serializable;
import java.io.*;
/**
 * Created by Defias on 2020/07.
 * Description: 序列化的控制
 *

 Externalizable接口
 public interface Externalizable extends java.io.Serializable

 writeExternal(ObjectOutput out)和readExternal(ObjectInput in)方法分别在序列化和反序列化时被自动调用

 恢复（反序列化）Externalizable对象与Serializable对象不同，对于Serializable对象，完全以它存储的二进制位位基础来构造，而不用调用构造
 器，而对于Externalizable对象，恢复时所有普通的默认构造器都会被调用（包括字段定义时的初始化），然后再调用readExternal方法

 使用场景：
 处于某种特殊或安全原因，希望对象的某一部分不被序列化，即对序列化进行控制时可使该对象实现Externalizable

 */

public class TestIOObject3 {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        //test1();
        test2();
    }


    public static void test1() throws IOException, ClassNotFoundException {
        System.out.println("Constructing objects:");
        Blip1 b1 = new Blip1();
        Blip2 b2 = new Blip2();
        System.out.println();

        ObjectOutputStream o = new ObjectOutputStream(new FileOutputStream("base/src/Blips.out"));
        System.out.println("Saving objects:");
        o.writeObject(b1);
        o.writeObject(b2);
        o.close();
        System.out.println();

        ObjectInputStream in = new ObjectInputStream(new FileInputStream("base/src/Blips.out"));
        System.out.println("Recovering b1:");
        b1 = (Blip1)in.readObject();
        // OOPS! Throws an exception: 由于Blip2的构造器不是public的，导致反序列化时无法调用默认构造器导致异常
        //System.out.println("Recovering b2:");
        //b2 = (Blip2)in.readObject();
    }


    //完整的保存和恢复一个Externalizable对象
    public static void test2() throws IOException, ClassNotFoundException {
        System.out.println("Constructing objects:");
        Blip3 b3 = new Blip3("A String ", 47);
        System.out.println(b3);
        ObjectOutputStream o = new ObjectOutputStream(new FileOutputStream("base/src/Blip3.out"));
        System.out.println("Saving object:");
        o.writeObject(b3);
        o.close();
        System.out.println();

        ObjectInputStream in = new ObjectInputStream(new FileInputStream("base/src/Blip3.out"));
        System.out.println("Recovering object:");
        b3 = (Blip3)in.readObject();
        System.out.println(b3);
    }
}



class Blip1 implements Externalizable {
    public Blip1() {
        System.out.println("Blip1 Constructor");
    }

    public void writeExternal(ObjectOutput out) throws IOException {
        System.out.println("Blip1.writeExternal");
    }

    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        System.out.println("Blip1.readExternal");
    }
}

class Blip2 implements Externalizable {
    Blip2() {
        System.out.println("Blip2 Constructor");
    }

    public void writeExternal(ObjectOutput out) throws IOException {
        System.out.println("Blip2.writeExternal");
    }

    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        System.out.println("Blip2.readExternal");
    }
}



class Blip3 implements Externalizable {
    private int i;
    private String s; // No initialization

    public Blip3() {
        System.out.println("Blip3 Constructor");
        // s, i not initialized
    }

    public Blip3(String x, int a) {
        System.out.println("Blip3(String x, int a)");
        s = x;
        i = a;
        // s & i initialized only in non-default constructor.
    }
    public String toString() {
        return s + i;
    }

    public void writeExternal(ObjectOutput out) throws IOException {
        System.out.println("Blip3.writeExternal");
        // You must do this:
        out.writeObject(s);
        out.writeInt(i);
    }

    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        System.out.println("Blip3.readExternal");
        // You must do this:
        s = (String)in.readObject();
        i = in.readInt();
    }
}