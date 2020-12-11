package Generics;
import Generics.element_generics.Generator;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Defias on 2020/07.
 * Description: 泛型使用：使用泛型构建复杂模型
 *
 * 一个零售店： 走廊、货架、商品
 */

public class Test0_GenericsModel {
    public static void main(String[] args) {
        System.out.println(new Store(14, 5, 10));
    }
}


class Store extends ArrayList<Aisle> {

    public Store(int nAisles, int nShelves, int nProducts) {
        for(int i = 0; i < nAisles; i++)
            add(new Aisle(nShelves, nProducts));
    }

    public String toString() {
        StringBuilder result = new StringBuilder();
        for(Aisle a : this)
            for(Shelf s : a)
                for(Product p : s) {
                    result.append(p);
                    result.append("\n");
                }
        return result.toString();
    }
}



//走廊（放货架）
class Aisle extends ArrayList<Shelf> {
    public Aisle(int nShelves, int nProducts) {
        for(int i = 0; i < nShelves; i++)
            add(new Shelf(nProducts));
    }
}



//货架（放商品）
class Shelf extends ArrayList<Product> {
    public Shelf(int nProducts) {
        Test2_GenericsMethod.fill(this, Product.generator, nProducts);
    }
}



//商品
class Product {
    private final int id;
    private String description;
    private double price;
    public Product(int IDnumber, String descr, double price){
        id = IDnumber;
        description = descr;
        this.price = price;
        System.out.println(toString());
    }

    public String toString() {
        return id + ": " + description + ", price: $" + price;
    }

    public void priceChange(double change) {
        price += change;
    }

    public static Generator<Product> generator =
            new Generator<Product>() {
                private Random rand = new Random(47);

                public Product next() {
                    return new Product(rand.nextInt(1000), "Test",
                            Math.round(rand.nextDouble() * 1000.0) + 0.99);
                }
            };
}



