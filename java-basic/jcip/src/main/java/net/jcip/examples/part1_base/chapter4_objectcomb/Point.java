package net.jcip.examples.part1_base.chapter4_objectcomb;

import net.jcip.annotations.Immutable;

/**
 * Point
 *
 * 坐标
 */

//不可变类

@Immutable
public class Point {
    public final int x, y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
