package Generics.mix;

import Generics.element.Animal;
import Generics.element.Cat;
import Generics.element.Dog;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Defias on 2020/07.
 * Description: 动态类型的安全 可以向java5之前的代码传递泛型容器
 */
public class TestCheckedList {

    //@SuppressWarnings("unchecked")
    static void oldStyleMethod(List probablyDogs) {
        probablyDogs.add(new Cat());
    }

    public static void main(String[] args) {
        List<Dog> dogs1 = new ArrayList<Dog>();
        oldStyleMethod(dogs1); // Quietly accepts a Cat
        System.out.println("ok");

        List<Dog> dogs2 = Collections.checkedList(
                new ArrayList<Dog>(), Dog.class);
        try {
            oldStyleMethod(dogs2); // Throws an exception
        } catch(Exception e) {
            System.out.println(e);
        }
        // Derived types work fine:
        List<Animal> pets = Collections.checkedList(
                new ArrayList<Animal>(), Animal.class);
        pets.add(new Dog());
        pets.add(new Cat());
        System.out.println("end");
    }
}
