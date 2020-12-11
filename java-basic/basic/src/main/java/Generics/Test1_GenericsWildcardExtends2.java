package Generics;
import java.util.*;
import Generics.element.*;
/**
 * Created by Defias on 2016/3/12.

 泛型通配符  extends
 */


public class Test1_GenericsWildcardExtends2 {

    //给动物们喂食
    public static void main(String[] args) {
        go();
    }

    //用容器
    public static void go() {
        ArrayList<Animal> animals = new ArrayList<Animal>();
        animals.add(new Dog());
        animals.add(new Cat());
        animals.add(new Dog());
        takeAnimals(animals);

    }

    //泛型<? extends Animal>
    public static void takeAnimals(ArrayList<? extends Animal>  animals) {
        for(Animal a: animals) {
            a.eat();
        }

        //animals.add(new Cat());  //错误   对于泛型<? extends Animal>不能写只能读
    }
}



