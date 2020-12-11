/**
 * Created by Defias on 2017/2/25.
 *
 * House
 *
 */
package Basic;
import java.util.Date;

public class JavaClone3 implements Cloneable, Comparable<JavaClone3> {
    private int id;
    private double area;
    private java.util.Date whenBuilt;

    public JavaClone3(int id, double area) {
        this.id = id;
        this.area = area;
        whenBuilt = new Date();
    }

    public double getId() {
        return id;
    }

    public double getArea() {
        return area;
    }

    public Date getWhenBuilt() {
        return whenBuilt;
    }

    /** Override the protected clone method defined in the Objectclass */
    //浅克隆
    //public Object clone() throws CloneNotSupportedException {
    //    return super.clone();
    //}

    //深克隆
    public Object clone() throws CloneNotSupportedException {
        JavaClone3 houseclone = (JavaClone3)super.clone();
        houseclone.whenBuilt = (Date)whenBuilt.clone();
        return houseclone;
    }


    /** Implement the compareTo method defined in Comparable */
    public int compareTo(JavaClone3 o) {
        if (area > ((JavaClone3)o).area)
            return 1;
        else if (area < ((JavaClone3)o).area)
            return -1;
        else
            return 0;
    }
}
