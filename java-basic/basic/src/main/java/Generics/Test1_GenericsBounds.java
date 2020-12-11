package Generics;

/**
 * Created by Defias on 2020/07.
 * Description: 泛型 - 边界
 *
 */

public class Test1_GenericsBounds {
    public static void main(String[] args) {
        Solid<Bounded> solid = new Solid<Bounded>(new Bounded());
        System.out.println(solid.color());
        System.out.println(solid.getY());
        System.out.println(solid.weight());
    }
}


interface HasColor {
    java.awt.Color getColor();
}

class Colored<T extends HasColor> {
    T item;
    Colored(T item) {
        this.item = item;
    }

    T getItem() {
        return item;
    }
    // The bound allows you to call a method:
    java.awt.Color color() {
        return item.getColor();
    }
}

class Dimension {
    public int x, y, z;
}

// This won't work -- class must be first, then interfaces
//class ColoredDimension<T extends HasColor & Dimension> {

// Multiple bounds: 多边界限定  即使HasColor是接口，也放置在关键字extends后面
// 放置顺序：先放类、然后再放接口，用&连接
class ColoredDimension<T extends Dimension & HasColor> {
    T item;
    ColoredDimension(T item) {
        this.item = item;
    }

    T getItem() {
        return item;
    }

    java.awt.Color color() {
        return item.getColor();
    }

    int getX() {
        return item.x;
    }

    int getY() {
        return item.y;
    }

    int getZ() {
        return item.z;
    }
}

interface Weight {
    int weight();
}

// As with inheritance, you can have only one concrete class but multiple interfaces
class Solid<T extends Dimension & HasColor & Weight> {
    T item;
    Solid(T item) {
        this.item = item;
    }

    T getItem() {
        return item;
    }

    int getX() {
        return item.x;
    }

    int getY() {
        return item.y;
    }

    int getZ() {
        return item.z;
    }

    java.awt.Color color() {
        return item.getColor();
    }

    int weight() {
        return item.weight();
    }
}

class Bounded extends Dimension implements HasColor, Weight {
    public java.awt.Color getColor() {
        return null;
    }

    public int weight() {
        return 0;
    }
}