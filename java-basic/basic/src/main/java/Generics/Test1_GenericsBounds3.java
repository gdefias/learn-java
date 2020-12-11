package Generics;

import org.apache.poi.ss.formula.functions.T;

import java.util.List;

/**
 * Created by Defias on 2020/07.
 * Description: 泛型 - 边界  复杂的继承关系
 */
public class Test1_GenericsBounds3 {

    public static void main(String[] args) {
        DogBoy dogBoy = new DogBoy();
        EpicBattle.useSuperHearing(dogBoy);
        EpicBattle.superFind(dogBoy);

        // You can do this:
        List<? extends SuperHearing> audioBoys;

        // But you can't do this:  why?
        //List<? extends SuperHearing & SuperSmell> dogBoys;
        //List<U extends SuperHearing & SuperSmell> dogBoys;
    }
}

interface SuperPower {}

interface XRayVision extends SuperPower {
    void seeThroughWalls();
}

interface SuperHearing extends SuperPower {
    void hearSubtleNoises();
}

interface SuperSmell extends SuperPower {
    void trackBySmell();
}

class SuperHero<POWER extends SuperPower> {
    POWER power;

    SuperHero(POWER power) {
        this.power = power;
    }

    POWER getPower() {
        return power;
    }
}

class SuperSleuth<POWER extends XRayVision> extends SuperHero<POWER> {
    SuperSleuth(POWER power) {
        super(power);
    }

    void see() {
        power.seeThroughWalls();
    }
}

class CanineHero<POWER extends SuperHearing & SuperSmell> extends SuperHero<POWER> {
    CanineHero(POWER power) {
        super(power);
    }

    void hear() {
        power.hearSubtleNoises();
    }

    void smell() {
        power.trackBySmell();
    }
}

class SuperHearSmell implements SuperHearing, SuperSmell {
    public void hearSubtleNoises() {}
    public void trackBySmell() {}
}

class DogBoy extends CanineHero<SuperHearSmell> {
    DogBoy() {
        super(new SuperHearSmell());
    }
}

class EpicBattle {
    // Bounds in generic methods:
    static <POWER extends SuperHearing> void useSuperHearing(SuperHero<POWER> hero) {
        hero.getPower().hearSubtleNoises();
    }

    static <POWER extends SuperHearing & SuperSmell> void superFind(SuperHero<POWER> hero) {
        hero.getPower().hearSubtleNoises();
        hero.getPower().trackBySmell();
    }
}