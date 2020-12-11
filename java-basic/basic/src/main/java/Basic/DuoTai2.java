package Basic;
/**
 * Created by Defias on 2016/2/27.
 *
 * 多态
 */


public class DuoTai2 {
    public static void main(String[] args){
        // 借助多态，主人可以给很多动物喂食
        Master ma = new Master();
        ma.feed(new Animall(), new Food());
        ma.feed(new Catt(), new Fish());
        ma.feed(new Dogg(), new Bone());
    }
}


// Animal类及其子类
class Animall {
    public void eat(Food f) {
        System.out.println("我是一个小动物，正在吃" + f.getFood());
    }
}

class Catt extends Animall {
    public void eat(Food f) {
        System.out.println("我是一只小猫咪，正在吃" + f.getFood());
    }
}

class Dogg extends Animall {
    public void eat(Food f) {
        System.out.println("我是一只狗狗，正在吃" + f.getFood());
    }
}


// Food及其子类
class Food {
    public String getFood() {
        return "食物";
    }
}
class Fish extends Food {
    public String getFood() {
        return "鱼";
    }
}

class Bone extends Food {
    public String getFood() {
        return "骨头";
    }
}


// Master类
class Master {
    public void feed(Animall an, Food f) {
        an.eat(f);
    }
}