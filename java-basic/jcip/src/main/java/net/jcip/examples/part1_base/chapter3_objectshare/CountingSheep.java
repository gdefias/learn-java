package net.jcip.examples.part1_base.chapter3_objectshare;

/**
 * CountingSheep

 数绵羊

 */
public class CountingSheep {
    volatile boolean asleep;

    void tryToSleep() {
        while (!asleep)
            countSomeSheep();
    }

    void countSomeSheep() {
        // One, two, three...
    }
}








