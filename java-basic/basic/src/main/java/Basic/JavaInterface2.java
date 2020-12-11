package Basic;

/**
 * Created by Defias on 2020/07.
 * Description: 实现多接口
 */
public class JavaInterface2 {
    public static void main(String[] args) {
        Hero h = new Hero();
        Adventure.t(h); // Treat it as a CanFight
        Adventure.u(h); // Treat it as a CanSwim
        Adventure.v(h); // Treat it as a CanFly
        Adventure.w(h); // Treat it as an ActionCharacter
    }
}


interface CanFight {
    void fight();
}

interface CanSwim {
    void swim();
}

interface CanFly {
    void fly();
}

class ActionCharacter {
    public void fight() {}
}

class Hero extends ActionCharacter
        implements CanFight, CanSwim, CanFly {  //实现多接口
    public void swim() {}
    public void fly() {}
}

class Adventure {
    public static void t(CanFight x) { x.fight(); }
    public static void u(CanSwim x) { x.swim(); }
    public static void v(CanFly x) { x.fly(); }
    public static void w(ActionCharacter x) { x.fight(); }
}