package FileIO.Serializable;
import java.util.concurrent.*;
import java.io.*;
import java.util.*;
/**
 * Created by Defias on 2020/07.
 * Description:  transient（瞬时）关键字

 transient意味：不用麻烦你保存或恢复数据，我自己会处理

 使用场景：
 1、如果正在操作一个Serializable对象，又想防治该对象的某些敏感部分（密码等）被序列化，可以使用transient来逐个字段的关闭序列化

 2、非序列化的数据字段
 如果一个对象是Serializable的，但是包含有非序列化的数据字段，那么这个对象也是不可序列化的，除非给这些非序列化的数据字段加上
 关键字transient。如：
 public class C implements java.io.SerializableIO {
     private int v1;
     private static double v2; //不会被序列化存储，但不影响对象能否序列化
     private transient A v3 = new A();
     //若类A是不可以序列化的，如果不加transient关键字将会发生异常java.io.NotSerializableException
     //加上transient关键字， 这个字段的生命周期就仅存于调用者的内存中而不会写到磁盘里持久化
 }
 */


public class TestIOObject4 {
    public static void main(String[] args) throws Exception {
        Logon a = new Logon("Hulk", "myLittlePony");
        System.out.println("logon a = " + a);
        ObjectOutputStream o = new ObjectOutputStream(new FileOutputStream("base/src/Logon.out"));
        o.writeObject(a);
        o.close();
        System.out.println();

        TimeUnit.SECONDS.sleep(1); // Delay

        //反序列化后password字段就会变成null(null引用在toString中会转换成null字符串)
        ObjectInputStream in = new ObjectInputStream(new FileInputStream("base/src/Logon.out"));
        System.out.println("Recovering object at " + new Date());
        a = (Logon)in.readObject();
        System.out.println("logon a = " + a);
    }
}


class Logon implements Serializable {
    private Date date = new Date();
    private String username;
    private transient String password;  //加上transient后该字段就不会被序列化了

    public Logon(String name, String pwd) {
        username = name;
        password = pwd;
    }

    public String toString() {
        return "logon info: \n   username: " + username +
                "\n   date: " + date + "\n   password: " + password;
    }
}