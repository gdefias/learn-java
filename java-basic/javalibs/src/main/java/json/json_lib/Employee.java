package json.json_lib;

/**
 * Created by Defias on 2017/3/10.
 *
 * java bean
 *
 */

public class Employee {
    private String name;
    private String sex;
    private int age;

    public Employee() {
        this("unkown", "unkown", 0);
    }

    public Employee(String name, String sex, int age) {
        this.name = name;
        this.sex = sex;
        this.age = age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return this.name;
    }

    public String getSex() {
        return this.sex;
    }

    public int getAge() {
        return this.age;
    }

}
