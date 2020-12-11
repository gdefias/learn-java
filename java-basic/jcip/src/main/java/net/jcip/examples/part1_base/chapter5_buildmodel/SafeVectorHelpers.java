package net.jcip.examples.part1_base.chapter5_buildmodel;

import java.util.Vector;

/**
 * SafeVectorHelpers

 使用客户端加锁的Vector上的复合操作

 */
public class SafeVectorHelpers {
    public static Object getLast(Vector list) {
        synchronized (list) {
            int lastIndex = list.size() - 1;
            return list.get(lastIndex);
        }
    }

    public static void deleteLast(Vector list) {
        synchronized (list) {
            int lastIndex = list.size() - 1;
            list.remove(lastIndex);
        }
    }
}
