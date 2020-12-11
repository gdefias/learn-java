package net.jcip.main;
import net.jcip.examples.part0.UnsafeSequence;

/**
 * Created by Defias on 2017/8/19.
 */



public class Normal implements Runnable {
    private String name;

    public Normal(String name) {
        this.name = name;
    }

    public void run() {
        System.out.println(name + " enter");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(name + " working");

        UnsafeSequence worker = new UnsafeSequence();
        System.out.println(worker.getNext());

        System.out.println(name + " leave");
    }
}
