package Concurrency.atomic;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

/**
 * Created by Defias on 2020/09.
 * Description: AtomicReference VS  AtomicReferenceFieldUpdater

 原子域更新器类AtomicReferenceFieldUpdater 能够在已有的volatile域上使用CAS

 */

public class TestAtomicReference {
    public static void main(String... args) {
        //testAtomicReference();
        testAtomicReferenceFieldUpdater();
    }

    public static void testAtomicReference() {
        String title = "title";
        AtomicReference atomicReference = new AtomicReference(title);
        String reference = (String) atomicReference.get();
        System.out.println(reference);
    }


    public static void testAtomicReferenceFieldUpdater() {
        AtomicReferenceFieldUpdater updater =
                AtomicReferenceFieldUpdater.newUpdater(Dog.class, String.class,"name");
        Dog aDog = new Dog();

        System.out.println(updater.compareAndSet(aDog,"dog1","dog2"));
        System.out.println(aDog.name);

        System.out.println(updater.getAndSet(aDog, "dog3"));
        System.out.println(aDog.name);
    }
}



class Dog  {
    volatile  String name = "dog1";
}