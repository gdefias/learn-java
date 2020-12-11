package net.jcip.examples.part2_structapplication.chapter8_threadpool;

import java.util.Set;

/**
 * Puzzle

 表示"搬箱子"之类谜题的抽象类

 */
public interface Puzzle <P, M> {
    P initialPosition();

    boolean isGoal(P position);

    Set<M> legalMoves(P position);

    P move(P position, M move);
}
