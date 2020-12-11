package Collection.choose;

import java.util.AbstractList;

/**
 * Created by Defias on 2020/07.
 * Description: 通过抽象类AbstractList生成List数据
 */
public class CountingIntegerList extends AbstractList<Integer> {
    private int size;

    public CountingIntegerList(int size) {
        this.size = size < 0 ? 0 : size;
    }

    public Integer get(int index) {
        return Integer.valueOf(index);
    }

    public int size() {
        return size;
    }

    public static void main(String[] args) {
        System.out.println(new CountingIntegerList(30));
    }
}