package Generics.element_generics;


//水果盘
public class Plate<T> {
    private T item;
    public Plate() {item=null;}
    public Plate(T t) {item=t;}
    public void set(T t) {item=t;}
    public T get() {return item;}
}