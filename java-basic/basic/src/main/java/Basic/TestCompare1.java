/**
 * Created by Defias on 2017/2/27.
 *
 * Comparator接口
 * 用于比较没有实现Comparable的类的对象
 *
 * Comparator是比较器接口。一个类实现了Comparator接口，那么它就是一个“比较器”。其它的类，可以根据该比较器去排序。
 *
 * public interface Comparator<T> {
 *      int compare(T o1, T o2);
 *      boolean equals(Object obj);
 * }
 *
 *  综述：Comparable是内部比较器，而Comparator是外部比较器。
 *  一个类本身实现了Comparable比较器，就意味着它本身支持排序；若它本身没实现Comparable，也可以通过外部比较器Comparator进行排序
 */
package Basic;
import java.util.ArrayList;
import java.util.Comparator;
import java.io.Serializable;

public class TestCompare1 {
    public static void main(String[] args) {
        Rectangle r1 = new Rectangle(1.2, 3.4);
        Rectangle r2 = new Rectangle(4.2, 5.9);
        Rectangle r3 = new Rectangle(2.2, 3.4);
        Rectangle r4 = new Rectangle(3.2, 1.9);
        Rectangle r5 = new Rectangle(2.0, 3.04);

        Rectangle mr = max(r1, r2, new RectangleComparator());
        System.out.println("The area of the larger object is " + mr.getArea());

        ArrayList<Rectangle> arrayrs = new ArrayList<>();
        arrayrs.add(r1);
        arrayrs.add(r2);
        arrayrs.add(r3);
        arrayrs.add(r4);
        RectangleComparator comp = new RectangleComparator();
        arrayrs.sort(comp);
        for(Rectangle r: arrayrs) {
            System.out.println(r);
        }

        System.out.println(r4==r5);
        System.out.println(r4.equals(r5));
        System.out.println(r4.hashCode());
        System.out.println(r5.hashCode());

    }

    public static Rectangle max(Rectangle r1, Rectangle r2, Comparator<Rectangle> c) {
        if(c.compare(r1, r2) > 0) {
            return r1;
        } else {
            return r2;
        }
    }
}


//创建比较器： 一个实现java.util.Comparator<T>接口的类并重写它的compare方法
class RectangleComparator implements Comparator<Rectangle>, Serializable {
    public int compare(Rectangle o1, Rectangle o2) {  //o1和o2也可以是同一父类的不同子类
        double area1 = o1.getArea();
        double area2 = o2.getArea();

        if(area1 < area2) {
            return -1;
        }
        else if(area1 == area2) {
            return 0;
        }
        else {
            return 1;
        }
    }

    //比较器中什么时候需要实现equals方法？如何实现？如果和对象本身的equals方法冲突会怎么样？
    //public boolean equals(Object obj) {
        //if(obj==null)
        //    return false;
        //
        //if(this==obj) {  //如果是同一个对象返回true
        //    return true;
        //}
        //
        //if(this.getClass() != obj.getClass()) {
        //    return false;
        //}
        //Rectangle objcr = (Rectangle)obj;
        //double off = getArea() - objcr.getArea();
        //if(off>-0.0000001 && off<0.0000001) {    //浮点数比较
        //    return true;
        //} else {
        //    return false;
        //}
    //}
}

//本省没有实现Comparable接口
class Rectangle {
    private double width;
    private double height;

    public Rectangle(double width, double height) {
        this.width = width;
        this.height = height;
    }

    public double getArea() {
        return (width * height);
    }

    @Override
    public boolean equals(Object obj) {
        if(obj==null)
            return false;

        if(this==obj) {  //如果是同一个对象返回true
            return true;
        }

        if(this.getClass() != obj.getClass()) {
            return false;
        }
        Rectangle objcr = (Rectangle)obj;
        double off = getArea() - objcr.getArea();
        if(off>-0.0000001 && off<0.0000001) {    //浮点数比较
            return true;
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        return "width: " + width + " height: " + height + " Area: " + getArea();
    }

}