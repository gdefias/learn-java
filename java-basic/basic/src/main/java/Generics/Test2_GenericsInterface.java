package Generics;
import Generics.element_generics.Generator;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Random;
import static Generics.element.Coffees.*;
/**
 * Created by Defias on 2020/07.
 * Description: 实现泛型接口
 */

public class Test2_GenericsInterface {

    public static void main(String[] args) {
        //Fibonacci fgen = new Fibonacci();
        //for(int i = 0; i < 18; i++)
        //    System.out.print(fgen.next() + " ");
        //System.out.println("\n-----------------");

        CoffeeGenerator gen = new CoffeeGenerator();
        for(int i = 0; i < 1; i++)
            System.out.println(gen.next());
        System.out.println("-----------------");

        //for(Coffee c : new CoffeeGenerator(5))
        //    System.out.println(c);
    }
}


//咖啡生成器
class CoffeeGenerator implements Generator<Coffee>, Iterable<Coffee> {
    private Class[] types = {
            Latte.class,
            Mocha.class,
            Cappuccino.class,
            Americano.class,
            Breve.class, };

    private static Random rand = new Random(47);

    public CoffeeGenerator() {

    }

    // For iteration
    private int size = 0;

    public CoffeeGenerator(int sz) {
        size = sz;
    }

    public Coffee next() {
        try {
            int randn = rand.nextInt(types.length);
            //System.out.println("randn: " +  randn);
            //System.out.println("types: " + Arrays.asList(types));

            Class randclass = types[randn];
           // System.out.println("randclass: " + randclass);
            Object randoj = randclass.newInstance();
            //System.out.println("randoj: " + randoj);

            return (Coffee) (randoj);
            //return (Coffee) ((types[randn]).newInstance());  ??

        } catch(Exception e) {
            throw new RuntimeException(e);
        }
    }

    class CoffeeIterator implements Iterator<Coffee> {
        int count = size;

        public boolean hasNext() {
            return count > 0;
        }
        public Coffee next() {
            count--;
            return CoffeeGenerator.this.next();
        }
        public void remove() { // Not implemented
            throw new UnsupportedOperationException();
        }
    };

    public Iterator<Coffee> iterator() {
        return new CoffeeIterator();
    }
}


//斐波拉契数生成器
class Fibonacci implements Generator<Integer> {
    private int count = 0;

    public Integer next() {
        return fib(count++);
    }

    private int fib(int n) {
        if(n < 2)
            return 1;
        return fib(n-2) + fib(n-1);
    }
}

