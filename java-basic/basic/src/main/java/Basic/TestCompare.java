/**
 * Created by Defias on 2017/2/27.
 *
 * Comparable接口
 * 定义了comppareTo方法，用于比较对象
 *
 * Comparable是排序接口。若一个类实现了Comparable接口，就意味着“该类支持排序”,它可以直接通过Arrays.sort() 或 Collections.sort()进行排序
 *
 java.util.Comparable<E>接口
 ----------------------------------
 +compareTo(o: E): int
 */
package Basic;
import java.lang.Comparable;
import java.util.*;

public class TestCompare {
        public static void main(String[] args) {
            ComparableRectangle[] rectangles = {
                    new ComparableRectangle(3.4, 5.4),
                    new ComparableRectangle(13.24, 55.4),
                    new ComparableRectangle(31.4, 52.3),
                    new ComparableRectangle(13.4, 53.0),
            };

            Arrays.sort(rectangles);   //因为实现了Comparable接口，所以可以使用集合排序
            for(ComparableRectangle rectangle: rectangles) {
                System.out.print(rectangle + " ");
                System.out.println();
            }

            //比较
            ComparableRectangle o1 = new ComparableRectangle(3.4, 5.4);
            ComparableRectangle o2 = new ComparableRectangle(13.4, 53.0);
            ComparableRectangle o3 = new ComparableRectangle(2.0, 9.18);

            System.out.println(o1.compareTo(o3));  //使用compareTo来进行比较，而不能使用大于号、小于号等
            System.out.println(o1 == o3);  //不管是基本类型还是引用类型比较的总是所指向的对象的地址
            System.out.println(o1.equals(o3));  //默认比较的是所指向的对象的地址，除非进行重写，不能用于基本数据类型
            System.out.println(o1.hashCode());
            System.out.println(o3.hashCode());

            HashSet<ComparableRectangle> sets = new HashSet<>();  //如果不重写hashCode方法，而且哈希码也不相等，那么相等的对象就可以添加到集合中而不会出现重复现象
            sets.add(o1);
            sets.add(o3);
            for(ComparableRectangle o: sets) {
                System.out.println(o);
            }

        }
}

class ComparableRectangle implements Comparable<ComparableRectangle> {
    private double width;
    private double height;

    public ComparableRectangle(double width, double height) {
        this.width = width;
        this.height = height;
    }

    public double getArea() {
        return (width * height);
    }

    @Override
    public int compareTo(ComparableRectangle o) {
        if(getArea() > o.getArea())
            return 1;
        else if(getArea() < o.getArea())
            return -1;
        else
            return 0;
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
        ComparableRectangle objcr = (ComparableRectangle)obj;
        double off = getArea() - objcr.getArea();
        if(off>-0.0000001 && off<0.0000001) {    //浮点数比较
            return true;
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        //int widthhash = (""+width).hashCode();
        //int heighthash = (""+height).hashCode();
        //return widthhash & heighthash;

        int Areahash = (""+getArea()).hashCode();
        return Areahash;
    }

    @Override
    public String toString() {
        return "width: " + width + " height: " + height + " Area: " + getArea();
    }

}