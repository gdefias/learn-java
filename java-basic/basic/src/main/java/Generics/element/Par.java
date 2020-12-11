package Generics.element;

/**
 * Created by Defias on 2020/07.
 * Description: 键值对
 */

public class Par<K,V> {
    public final K key;
    public final V value;
    public Par(K k, V v) {
        key = k;
        value = v;
    }
}