package Generics.element_generics;


//持有者
public class Holder<T> {
    private T value;
    public Holder() {}

    public Holder(T val) { value = val; }

    public void set(T val) { value = val; }

    public T get() { return value; }

    public boolean equals(Object obj) {
        return value.equals(obj);
    }
}
