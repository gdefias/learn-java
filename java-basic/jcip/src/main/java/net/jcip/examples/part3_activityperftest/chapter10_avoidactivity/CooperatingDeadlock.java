package net.jcip.examples.part3_activityperftest.chapter10_avoidactivity;

import net.jcip.annotations.GuardedBy;
import net.jcip.examples.part1_base.chapter4_objectcomb.Point;

import java.util.HashSet;
import java.util.Set;

/**
 * CooperatingDeadlock

 在相互协作对象之间的锁顺序死锁

 setLocation与getImage以相反的顺序获取锁，可能会发生死锁

 */
public class CooperatingDeadlock {
    // Warning: deadlock-prone!

    //出租车
    class Taxi {
        @GuardedBy("this") private Point location, destination;
        private final Dispatcher dispatcher;

        public Taxi(Dispatcher dispatcher) {
            this.dispatcher = dispatcher;
        }

        public synchronized Point getLocation() {
            return location;
        }

        public synchronized void setLocation(Point location) {
            this.location = location;
            if (location.equals(destination))
                dispatcher.notifyAvailable(this);
        }

        public synchronized Point getDestination() {
            return destination;
        }

        public synchronized void setDestination(Point destination) {
            this.destination = destination;
        }
    }

    //出租车车队
    class Dispatcher {
        @GuardedBy("this") private final Set<Taxi> taxis;
        @GuardedBy("this") private final Set<Taxi> availableTaxis;

        public Dispatcher() {
            taxis = new HashSet<Taxi>();
            availableTaxis = new HashSet<Taxi>();
        }

        public synchronized void notifyAvailable(Taxi taxi) {
            availableTaxis.add(taxi);
        }

        public synchronized Image getImage() {
            Image image = new Image();
            for (Taxi t : taxis)
                image.drawMarker(t.getLocation());
            return image;
        }
    }

    class Image {
        public void drawMarker(Point p) {
        }
    }
}
