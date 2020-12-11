package net.jcip.examples.part1_base.chapter4_objectcomb;

import net.jcip.annotations.NotThreadSafe;

/**
 * MutablePoint
 * Mutable Point class similar to java.awt.Point
 *
 * 坐标
 */


@NotThreadSafe
public class MutablePoint {
    public int x, y;

    public MutablePoint() {
        x = 0;
        y = 0;
    }

    public MutablePoint(MutablePoint p) {
        this.x = p.x;
        this.y = p.y;
    }
}
