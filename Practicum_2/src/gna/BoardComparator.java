package gna;

import libpract.PriorityFunc;

import java.util.Comparator;
import java.util.PriorityQueue;

public class BoardComparator implements Comparator<Board> {
    Boolean hamming;

    public BoardComparator(PriorityFunc hamming){
        this.hamming = hamming == PriorityFunc.HAMMING;
    }

    /**
     *
     * @param o1 the first object to be compared.
     * @param o2 the second object to be compared.
     * @return a negative integer, zero, or a positive integer as the
     *         first argument is less than, equal to, or greater than the
     *         second.
     * @throws NullPointerException if an argument is null and this
     *         comparator does not permit null arguments
     * @throws ClassCastException if the arguments' types prevent them from
     *         being compared by this comparator.
     */
    @Override
    public int compare(Board o1, Board o2) {
        if(o1 == null|| o2 == null) throw new NullPointerException("[Solver] Found an empty board in the priorityqueue.");
        if(o2.getClass() != o2.getClass()) throw new ClassCastException("[Solver] Found a wrong class for the board in the priorityqueue.");
        if(this.hamming){
            return Integer.compare(o1.hamming(), o2.hamming());
        }else{
            return Integer.compare(o1.manhattan(), o2.manhattan());
        }
    }

}
