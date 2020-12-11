package FileIO.Serializable;

/**
 * Created by Defias on 2016/5/9.

 对象序列化

 对象的序列化：把对象写到一个输出流中
 对象的反序列化：从一个输入流中读取一个对象

 对象序列化机制自动弥补了不同操作系统之间的差异

 ObjectInputStream和ObjectOutputStream类用于读写可序列化的对象
 ObjectInputStream
    public class ObjectInputStream extends InputStream implements ObjectInput, ObjectStreamConstants
    扩展InputStream类，并实现ObjectInput接口（DataInput的子接口）和ObjectStreamConstants接口
        +ObjectInputStream(in: InputStream)  //构造方法
        +readObject(): object  //读取一个对象

 ObjectOutputStream
    public class ObjectOutputStream extends OutputStream implements ObjectOutput, ObjectStreamConstants
    扩展OutputStream类，并实现ObjectOutput接口（DataOutput的子接口）和ObjectStreamConstants接口
        +ObjectOutputStream(out: OutputStream)  //构造方法
        +writeObject(): void  //写入一个对象

 ObjectStreamConstants
 包含支持ObjectInputStream和ObjectOutputStream类所用的常量


 Serializable接口

 并不是每一个对象都可以写到输出流中，只有实现了serializable接口（一种标记接口）的类的对象才能被序列化和反序列化
 （所有基本类型的封装类、所有容器类以及许多其他的类，甚至Class对象也可以被序列化）

 当存储一个序列化对象时，会对该对象的类进行编码，编码包括类名、类的签名、对象实例变量的值以及该对象引用的任何其他对象的闭包，
 但不存储对象静态变量的值


 重复的对象
 如果一个对象不止一次写入对象流，读出这些对象时他们的引用相同，内存中实际上存储的是一个对象

 */
import java.io.*;
import java.util.Date;

public class TestIOObject {
    public static void main(String[] args) throws ClassNotFoundException, IOException {
        //序列化
        ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream("base/src/object.dat"));
        //ObjectOutputStream output = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream("object.dat")));

        output.writeUTF("John");
        output.writeDouble(85.5);
        output.writeObject(new Date());
        output.close();

        //反序列化
        ObjectInputStream input = new ObjectInputStream(new FileInputStream("base/src/object.dat"));

        String name = input.readUTF();
        double score = input.readDouble();
        java.util.Date date = (java.util.Date)(input.readObject());
        System.out.println(name + " " + score + " " + date);
        input.close();

        System.out.println("---------------------------");

        //序列化
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("base/src/objectFile.obj"));

        String obj1 = "hello";
        Date obj2 = new Date();
        Customerr obj3 = new Customerr("Tom",20);

        out.writeObject(obj1);
        out.writeObject(obj2);
        out.writeObject(obj3);
        out.writeObject(obj3);
        out.close();

        //反序列化
        ObjectInputStream in = new ObjectInputStream(new FileInputStream("base/src/objectFile.obj"));
        String obj4 = (String)in.readObject();
        System.out.println("obj4: " + obj4);
        //反序列化生成的对象与原对象不是同一个对象
        System.out.println("obj4==obj1: "+ (obj4==obj1));

        Date obj5 = (Date)in.readObject();
        System.out.println("obj5: " + obj5);
        System.out.println("obj5==obj2: " + (obj5==obj2));

        Customerr obj6 = (Customerr)in.readObject();
        System.out.println("obj6: " + obj6);
        System.out.println("obj6==obj3: " + (obj6==obj3));

        Customerr obj7 = (Customerr)in.readObject();
        System.out.println("obj7: " + obj7);
        System.out.println("obj7==obj6: " + (obj6==obj7));  //重复的对象
        in.close();

        System.out.println("---------------------------");

        //序列化数组
        int[] numbers = {1, 2, 3, 4, 5};
        String[] strings = {"John", "Jim", "Jake"};

        ObjectOutputStream arrayout = new ObjectOutputStream(
                new FileOutputStream("base/src/array.dat", true));
        arrayout.writeObject(numbers);
        arrayout.writeObject(strings);
        arrayout.close();

        //反序列化
        ObjectInputStream arrayin = new ObjectInputStream(
                new FileInputStream("base/src/array.dat"));

        int[] newNumbers = (int[])(arrayin.readObject());
        String[] newStrings = (String[])(arrayin.readObject());

        for (int i=0; i<newNumbers.length; i++)
            System.out.print(newNumbers[i] + " ");
        System.out.println();

        for (int i = 0; i < newStrings.length; i++)
            System.out.print(newStrings[i] + " ");
        System.out.println();
    }
}

class Customerr implements Serializable {
    private String name;
    private int age;

    public Customerr(String name,int age) {
        this.name=name;
        this.age=age;
        System.out.println("call constructor");
    }

    public boolean equals(Object o) {
        if(this==o)
            return true;
        if(!(o instanceof Customerr))
            return false;

        final Customerr other = (Customerr)o;
        if(this.name.equals(other.name) && this.age==other.age)
            return true;
        else
            return false;
    }

    @Override
    public String toString() {
        return "name="+name+",age="+age;
    }
}