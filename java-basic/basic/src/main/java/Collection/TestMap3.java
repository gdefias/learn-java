package Collection;

import java.util.AbstractMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by Defias on 2020/07.
 * Description: 填充Map容器 -- 数据生成器
 */
public class TestMap3 {

    public static void main(String[] args) {

//        CountingMapData cm = new CountingMapData(60);
//        System.out.print("{");
//        for(Map.Entry entry: cm.entrySet() ) {
//            System.out.print(entry.getKey());
//            System.out.print("=");
//            System.out.print(entry.getValue());
//            System.out.print(", ");
//        }
//        System.out.print("}\n");

        System.out.println(new CountingMapData(60));
    }
}


//通过抽象类AbstractMap生成Map数据
class CountingMapData extends AbstractMap<Integer,String> {
    private int size;
    private static String[] chars =
            "A B C D E F G H I J K L M N O P Q R S T U V W X Y Z"
                    .split(" ");

    public CountingMapData(int size) {
        if(size < 0) this.size = 0;
        this.size = size;
    }

    private static class Entry
            implements Map.Entry<Integer,String> {
        int index;
        Entry(int index) { this.index = index; }
        public boolean equals(Object o) {
            return Integer.valueOf(index).equals(o);
        }
        public Integer getKey() { return index; }
        public String getValue() {
            return
                    chars[index % chars.length] +
                            Integer.toString(index / chars.length);
        }
        public String setValue(String value) {
            throw new UnsupportedOperationException();
        }
        public int hashCode() {
            return Integer.valueOf(index).hashCode();
        }
    }

    public Set<Map.Entry<Integer,String>> entrySet() {
        // LinkedHashSet retains initialization order:
        Set<Map.Entry<Integer,String>> entries =
                new LinkedHashSet<Map.Entry<Integer,String>>();
        for(int i = 0; i < size; i++)
            entries.add(new Entry(i));
        return entries;
    }
}