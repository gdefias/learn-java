package net.jcip.examples.part2_structapplication.chapter8_threadpool;

import net.jcip.annotations.Immutable;

import java.util.LinkedList;
import java.util.List;

/**
 * PuzzleNode

 用于谜题解决框架的链表节点

 */
@Immutable
public class PuzzleNode <P, M> {
    final P pos;
    final M move;
    final PuzzleNode<P, M> prev;

    public PuzzleNode(P pos, M move, PuzzleNode<P, M> prev) {
        this.pos = pos;
        this.move = move;
        this.prev = prev;
    }

    List<M> asMoveList() {
        List<M> solution = new LinkedList<M>();
        for (PuzzleNode<P, M> n = this; n.move != null; n = n.prev)
            solution.add(0, n.move);
        return solution;
    }
}
