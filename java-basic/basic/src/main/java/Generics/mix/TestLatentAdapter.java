package Generics.mix;

import Generics.element_generics.Generator;
import java.util.*;
import static Generics.element.Coffees.*;
import static Basic.Print.print;
/**
 * Created by Defias on 2020/07.
 * Description: 使用适配器仿真潜在类型机制
 *
 */
public class TestLatentAdapter {

    public static void main(String[] args) {
        // Adapt a Collection:
        List<Coffee> carrier = new ArrayList<Coffee>();
        Fill2.fill(new AddableCollectionAdapter<Coffee>(carrier),
                Coffee.class, 3);
        // Helper method captures the type:
        Fill2.fill(Adapter.collectionAdapter(carrier),
                Latte.class, 2);
        for(Coffee c: carrier)
            print(c.toString());
        print("----------------------");

        AddableSimpleQueue<Coffee> coffeeQueue = new AddableSimpleQueue<Coffee>();
        Fill2.fill(coffeeQueue, Mocha.class, 4);
        Fill2.fill(coffeeQueue, Latte.class, 1);
        for(Coffee c: coffeeQueue)
            print(c.toString());
    }
}



interface Addable<T> { void add(T t); }


// To adapt a base type, you must use composition.
// Make any Collection Addable using composition:
class AddableCollectionAdapter<T> implements Addable<T> {
    private Collection<T> c;
    public AddableCollectionAdapter(Collection<T> c) {
        this.c = c;
    }

    public void add(T item) {
        c.add(item);
    }
}

// A Helper to capture the type automatically:
class Adapter {
    public static <T> Addable<T> collectionAdapter(Collection<T> c) {
        return new AddableCollectionAdapter<T>(c);
    }
}

// To adapt a specific type, you can use inheritance.
// Make a SimpleQueue Addable using inheritance:
class AddableSimpleQueue<T>
        extends SimpleQueue<T> implements Addable<T> {
    public void add(T item) { super.add(item); }
}


class Fill2 {
    // Classtoken version:
    public static <T> void fill(Addable<T> addable,
                                Class<? extends T> classToken, int size) {
        for(int i = 0; i < size; i++)
            try {
                addable.add(classToken.newInstance());
            } catch(Exception e) {
                throw new RuntimeException(e);
            }
    }
    // Generator version:
    public static <T> void fill(Addable<T> addable,
                                Generator<T> generator, int size) {
        for(int i = 0; i < size; i++)
            addable.add(generator.next());
    }
}



class SimpleQueue<T> implements Iterable<T> {
    private LinkedList<T> storage = new LinkedList<T>();
    public void add(T t) { storage.offer(t); }
    public T get() { return storage.poll(); }
    public Iterator<T> iterator() {
        return storage.iterator();
    }
}