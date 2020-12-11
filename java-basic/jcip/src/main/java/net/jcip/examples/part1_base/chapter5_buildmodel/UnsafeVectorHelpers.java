package net.jcip.examples.part1_base.chapter5_buildmodel;

import java.util.Vector;

/**
 * UnsafeVectorHelpers

 Vector上可能导致混乱结果的复合操作

 */
public class UnsafeVectorHelpers {
    public static Object getLast(Vector list) {
        int lastIndex = list.size() - 1;
        return list.get(lastIndex);
    }

    public static void deleteLast(Vector list) {
        int lastIndex = list.size() - 1;
        list.remove(lastIndex);
    }
}
