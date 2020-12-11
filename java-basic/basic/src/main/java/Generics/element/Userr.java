package Generics.element;

/**
 * Created by Defias on 2020/07.
 * Description:
 */
public class Userr {
    private static int count=0;
    public String name;
    public int id;
    private int height;
    private int age;

    public Userr(String name) {
        this.name = name;
        this.id = count++;
        this.height = 168;
        this.age = 18;
    }

    public Userr(String name, int height) {
        this.name = name;
        this.id = count++;
        this.height = height;
        this.age = 18;
    }

    public Userr(String name, int height, int age) {
        this.name = name;
        this.id = count++;
        this.height = height;
        this.age = age;
    }

    public int getHeight() {
        return height;
    }

    public int getAge() {
        return age;
    }


    public String toString() {
        return name;
    }
}
