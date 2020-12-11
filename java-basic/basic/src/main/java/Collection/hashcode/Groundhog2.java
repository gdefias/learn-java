package Collection.hashcode;

/**
 * Created by Defias on 2020/07.
 * Description: 土拨鼠
 */
public class Groundhog2 extends Groundhog {
    public Groundhog2(int n) {
        super(n);
    }

    public int hashCode() {
        return number;
    }

    //重写equals方法
    public boolean equals(Object o) {
        return o instanceof Groundhog2 && (number == ((Groundhog2)o).number);
    }
}