package Generics.element_generics;


//泛型类 - 存放一对相同类型的对象
public class PairE<T extends Number> {
    private T first;
    private T second;

    public PairE() {
        first = null;
        second = null;
    }

    public PairE(T first, T second) {
        this.first = first;
        this.second = second;
    }

    public T getFirst() {
        return first;
    }

    public T getSecond() {
        return second;
    }

    public void setFirst(T newValue) {
        first = newValue;
    }

    public void setSecond(T newValue) {
        second = newValue;
    }
}