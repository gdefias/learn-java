package FileIO.Serializable;
import java.io.*;
import java.util.*;

/**
 * Created by Defias on 2020/07.
 * Description: 序列化 -- 利用“持久性”

 如果两个对象都有指向第三个对象的句柄，该如何对这两个对象序列化呢？如果从两个对象序列化后的状态恢复它们，第三个对象的句柄只会出现在一个
 对象身上吗？如果将这两个对象序列化成独立的文件，然后在代码的不同部分重新装配它们，又会得到什么结果呢？

 只要将所有东西都序列化到单独一个数据流里，就能恢复获得与以前写入时完全一样的对象网，不会不慎造成对象的重复
 *
 */

public class TestIOObject6 {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        House house = new House();
        List<Animal> animals = new ArrayList<Animal>();
        animals.add(new Animal("Bosco the dog", house));
        animals.add(new Animal("Ralph the hamster", house));
        animals.add(new Animal("Molly the cat", house));
        System.out.println("animals: " + animals);
        System.out.println();

        ByteArrayOutputStream buf1 = new ByteArrayOutputStream();
        ObjectOutputStream o1 = new ObjectOutputStream(buf1);
        o1.writeObject(animals);
        o1.writeObject(animals); // Write a 2nd set

        ByteArrayOutputStream buf2 = new ByteArrayOutputStream();
        ObjectOutputStream o2 = new ObjectOutputStream(buf2);
        o2.writeObject(animals);


        ObjectInputStream in1 = new ObjectInputStream(new ByteArrayInputStream(buf1.toByteArray()));
        ObjectInputStream in2 = new ObjectInputStream(new ByteArrayInputStream(buf2.toByteArray()));

        List
                animals1 = (List)in1.readObject(),
                animals2 = (List)in1.readObject(),
                animals3 = (List)in2.readObject();

        //序列化到单一数据流中的animals1和animals2其反序列化后仍是同一对象，包括其持有的House对象
        System.out.println("animals1: " + animals1);
        System.out.println("animals2: " + animals2);

        //序列化到不同数据流中的animals1和animals3其反序列化后是不同的对象，包括其持有的House对象
        System.out.println("animals3: " + animals3);
    }
}


class House implements Serializable {}

class Animal implements Serializable {
    private String name;
    private House preferredHouse;

    Animal(String nm, House h) {
        name = nm;
        preferredHouse = h;
    }

    public String toString() {
        return name + "[" + super.toString() +
                "]" + preferredHouse + "\n";
    }
}