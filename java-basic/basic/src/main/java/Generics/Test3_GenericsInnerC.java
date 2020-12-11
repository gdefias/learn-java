package Generics;
import Generics.element_generics.Generator;
import java.util.*;

/**
 * Created by Defias on 2020/07.
 * Description: 使用泛型的匿名内部类
 *
 *
 * 泛型可以用于内部类
 */

public class Test3_GenericsInnerC {

    public static void serve(Teller t, Customer c) {
        System.out.println(t + " serves " + c);
    }

    public static void main(String[] args) {
        Random rand = new Random(47);
        Queue<Customer> line = new LinkedList<Customer>();
        Test2_GenericsMethod.fill(line, Customer.generator(), 15);

        List<Teller> tellers = new ArrayList<Teller>();
        Test2_GenericsMethod.fill(tellers, Teller.generator, 4);

        for(Customer c : line)
            serve(tellers.get(rand.nextInt(tellers.size())), c);
    }
}



class Customer {
    private static long counter = 1;
    private final long id = counter++;
    private Customer() {}
    public String toString() { return "Customer " + id; }
    // A method to produce Generator objects:
    public static Generator<Customer> generator() {
        return new Generator<Customer>() {   //匿名内部类
            public Customer next() {
                return new Customer();
            }
        };
    }
}

class Teller {
    private static long counter = 1;
    private final long id = counter++;
    private Teller() {}
    public String toString() { return "Teller " + id; }
    // A single Generator object:
    public static Generator<Teller> generator =
            new Generator<Teller>() {   //匿名内部类
                public Teller next() { return new Teller(); }
            };
}