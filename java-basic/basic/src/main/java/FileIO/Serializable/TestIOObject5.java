package FileIO.Serializable;
import java.io.*;
/**
 * Created by Defias on 2020/07.
 * Description: Externalizable的替代方法

 若不是特别在意要实现Externalizable接口，还有另一种方法可供选用：
 实现Serializable接口，并添加（注意是添加，而非覆盖或者实现）名为writeObject()和readObject()的方法
 一旦对象被序列化或者重新装配，就会分别调用那两个方法。也就是说，只要提供了这两个方法，就会优先使用它们，而不考虑默认的序列化机制

 其准确的签名：
 private void writeObject(ObjectOutputStream stream) throws IOException;

 private void readObject(ObjectInputStream stream) throws IOException, ClassNotFoundException


 调用时机：
 调用ObjectOutputStream.writeObject(ObjectOutputStream serializableObject)的时候，传递给它的Serializable对象似乎会被检查是
 否实现了自己的writeObject() 【不是检查它的接口—它根本就没有，也不是检查类的类型，而是利用反射实际搜索方法）】。若答案是肯定的是，
 便会跳过常规的序列化过程，并调用writeObject()。readObject()也是同样的情况


 默认的writeObject()和readObject()：
 在添加方法writeObject()内部，可以调用defaultWriteObject()，从而采取默认的行动
 类似地，在readObject()内部，可以调用defaultReadObject()

 */


public class TestIOObject5 {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        SerialCtl sc = new SerialCtl("Test1", "Test2");
        System.out.println("Before:\n" + sc);
        ByteArrayOutputStream buf= new ByteArrayOutputStream();
        ObjectOutputStream o = new ObjectOutputStream(buf);
        o.writeObject(sc);
        System.out.println();


        ObjectInputStream in = new ObjectInputStream(new ByteArrayInputStream(buf.toByteArray()));
        SerialCtl sc2 = (SerialCtl)in.readObject();
        System.out.println("After:\n" + sc2);
    }
}


class SerialCtl implements Serializable {
    private String a;  //非transient字段被defaultWriteObject()方法自动保存
    private transient String b;  //transient字段必须在程序中明确保存和恢复

    public SerialCtl(String aa, String bb) {
        a = "Not Transient: " + aa;
        b = "Transient: " + bb;
    }

    public String toString() {
        return a + "\n" + b;
    }

    private void writeObject(ObjectOutputStream stream) throws IOException {
        stream.defaultWriteObject();  //第一个字段a对应第一个操作
        stream.writeObject(b);
    }

    private void readObject(ObjectInputStream stream) throws IOException, ClassNotFoundException {
        stream.defaultReadObject();
        b = (String)stream.readObject();
    }
}