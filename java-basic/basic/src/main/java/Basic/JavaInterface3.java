package Basic;

/**
 * Created by Defias on 2020/07.
 * Description: 通过继承来扩展接口
 */

public class JavaInterface3 {
    public static void main(String[] args) {

        DangerousMonster barney = new DragonZilla();
        HorrorShow.u(barney);
        HorrorShow.v(barney);
        Vampire vlad = new VeryBadVampire();
        HorrorShow.u(vlad);
        HorrorShow.v(vlad);
        HorrorShow.w(vlad);
    }
}


interface Monster {
    void menace();
}

interface DangerousMonster extends Monster {  //接口继承接口
    void destroy();
}

interface Lethal {
    void kill();
}

class DragonZilla implements DangerousMonster {
    public void menace() {}
    public void destroy() {}
}

interface Vampire extends DangerousMonster, Lethal {   //接口的多继承： 接口继承多个接口
    void drinkBlood();
}

class VeryBadVampire implements Vampire {
    public void menace() {}
    public void destroy() {}
    public void kill() {}
    public void drinkBlood() {}
}


class HorrorShow {
    static void u(Monster b) {
        b.menace();
    }

    static void v(DangerousMonster d) {
        d.menace();
        d.destroy();
    }

    static void w(Lethal l) {
        l.kill();
    }
}