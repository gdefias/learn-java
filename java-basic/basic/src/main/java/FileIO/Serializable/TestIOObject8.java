package FileIO.Serializable;

import java.io.*;

/**
 * Created by Defias on 2020/07.
 * Description:  单例模式增强

 单例类的序列化添加自定义的readResolve()已保持单例性


 serialVersionUID
 serialVersionUID是序列化前后的唯一标识符，默认如果没有人为显式定义过serialVersionUID，那编译器会为它自动声明一个
 serialVersionUID序列化ID，可以看成是序列化和反序列化过程中的“暗号”，在反序列化时，JVM会把字节流中的序列号ID和被序列化类
 中的序列号ID做比对，只有两者一致，才能重新反序列化，否则就会报异常来终止反序列化的过程
 */

public class TestIOObject8 {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(
                new FileOutputStream(
                        new File("base/src/singletonn.txt")));
        // 将单例对象先序列化到文本文件singletonn.txt中
        objectOutputStream.writeObject( Singletonn.getSingletonn() );
        objectOutputStream.close();

        ObjectInputStream objectInputStream = new ObjectInputStream(
                new FileInputStream(
                        new File("base/src/singletonn.txt")));

        // 将文本文件singletonn.txt中的对象反序列化为singleton1
        Singletonn singleton1 = (Singletonn) objectInputStream.readObject();
        objectInputStream.close();

        Singletonn singleton2 = Singletonn.getSingletonn();

        //可序列化的单例类有可能并不单例，解决办法是添加自定义的readResolve方法
        System.out.println( singleton1 == singleton2 );
    }

}




class Singletonn implements Serializable {
    private static final long serialVersionUID = -1576643344804979563L;

    private Singletonn() {
    }

    private static class SingletonnHolder {
        private static final Singletonn singleton = new Singletonn();
    }

    public static synchronized Singletonn getSingletonn() {
        return SingletonnHolder.singleton;
    }

//    private Object readResolve() {
//        return SingletonnHolder.singleton;
//    }

}