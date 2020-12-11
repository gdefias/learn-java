package Collection;
import java.util.*;
/**
 * Created by Defias on 2020/07.
 * Description: Set中的元素类
 */

public class TestSet {
    public static void main(String[] args) {
        test(new HashSet<HashType>(), HashType.class);
        test(new LinkedHashSet<HashType>(), HashType.class);
        test(new TreeSet<TreeType>(), TreeType.class);

        // Things that don't work:
        // 对于没有定义hashCode方法的元素类，放入hash容器中会产生重复的值（虽然是合法的，但是违背了set的基本原则）
        test(new HashSet<SetType>(), SetType.class);
        test(new HashSet<TreeType>(), TreeType.class);
        test(new LinkedHashSet<SetType>(), SetType.class);
        test(new LinkedHashSet<TreeType>(), TreeType.class);

        /// 对于没有定义Comparable方法的元素类，放入TreeSet容器会直接产生异常
        try {
            test(new TreeSet<SetType>(), SetType.class);
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }

        try {
            test(new TreeSet<HashType>(), HashType.class);
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }


    static <T> Set<T> fill(Set<T> set, Class<T> type) {
        try {
            for(int i = 0; i < 10; i++)
                set.add(type.getConstructor(int.class).newInstance(i)); //使用int参数的构造器
        } catch(Exception e) {
            throw new RuntimeException(e);
        }
        return set;
    }

    static <T> void test(Set<T> set, Class<T> type) {
        fill(set, type);
        fill(set, type); // Try to add duplicates
        fill(set, type);
        System.out.println(set);
    }
}


//Set中存储的元素的类型
class SetType {
    int i;
    public SetType(int n) { i = n; }
    public boolean equals(Object o) {
        return o instanceof SetType && (i == ((SetType)o).i);
    }
    public String toString() { return Integer.toString(i); }
}

class HashType extends SetType {
    public HashType(int n) { super(n); }
    public int hashCode() { return i; }
}

class TreeType extends SetType
        implements Comparable<TreeType> {
    public TreeType(int n) { super(n); }
    public int compareTo(TreeType arg) {
        return (arg.i < i ? -1 : (arg.i == i ? 0 : 1));
    }
}