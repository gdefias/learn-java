package Basic;
/**
 * Created by Defias on 2016/2/27.
 *
 * super  方法的重写（覆盖）
 *
 * 方法的重写（覆盖）：
 * 1. 覆盖方法的返回类型、方法名称、参数列表必须与原方法的相同
 * 2. 覆盖方法不能比原方法访问性差（即访问权限不允许缩小）
 * 3. 覆盖方法不能比原方法抛出更多的异常
 * 4. 被覆盖的方法不能是final类型，因为final修饰的方法是无法覆盖的
 * 5. 被覆盖的方法不能为private，否则在其子类中只是新定义了一个方法，并没有对其进行覆盖
 * 6. 被覆盖的方法不能为static。如果父类中的方法为静态的，而子类中的方法不是静态的，但是两个方法除了这一点外其他都满足覆盖条件，那么会
 * 发生编译错误；反之亦然。即使父类和子类中的方法都是静态的，并且满足覆盖条件，但是仍然不会发生覆盖，因为静态方法是在编译的时候把静态方
 * 法和类的引用类型进行匹配
 *
 * 重写（覆盖）和重载的不同：
 * 1. 方法覆盖要列表必须参数一致，而方法重载要求参数不一致
 * 2. 方法覆盖要求返回类型必须一致，方法重载对此没有要求
 * 3. 方法覆盖只能是子类覆盖父类的方法，方法重载则可以是同一个类中的所有方法（包括从父类中继承而来的方法）
 * 4. 方法覆盖对方法的访问权限和抛出的异常有特殊的要求，而方法重载在这方面没有任何限制
 * 5. 父类的一个方法只能被子类覆盖一次，而一个方法可以在所有的类中可以被重载多次
 *
 */


public class JavaOverrideSuper {
    public static void main(String[] args) {
        DDog obj = new DDog("花花", 3);
        obj.say();
        obj.move();
    }
}

class DDog extends AAnimal {
    int age;

    public DDog(String name, int age) {
        super(name);  //使用super来显示调用父类的构造方法
        //注意：java继承中对构造函数是不继承的，只是调用（隐式或显式）
        //此处若没有super语句来显示调用，父类又没提供构造方法时，将会出现编译报错
        this.age = age;
    }

    public void move() {  //覆盖move()方法
        super.move();  // 调用父类的方法
        System.out.println("Dogs can walk and run");
        System.out.println("Please remember: " + super.getDesc()); // private的数据成员对子类也是不可见的，需要调用getter方法
    }

    public void say() { //覆盖say()方法
        System.out.println("我是一只可爱的小狗，我的名字叫" + name + "，我" + age + "岁了");
    }
}



class AAnimal {
    String name;
    private String desc = "Animals are human's good friends";

    public AAnimal(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void move() {
        System.out.println("Animals can move");
    }

    public void say(){
        System.out.println("我是一只小动物，我的名字叫" + name + "，我会发出叫声Animal");
    }

}








