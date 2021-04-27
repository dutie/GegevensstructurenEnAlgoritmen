package gna;

import libpract.PriorityFunc;

import java.util.Comparator;
import java.util.PriorityQueue;

public class PriorityQueueBoard {
    private PriorityFunc priorityFunction;
    private PriorityQueue<Board> priorityQueue;

    public PriorityQueueBoard(PriorityQueue<Board> priorityQueue, PriorityFunc priorityFunction) {
        this.priorityQueue = priorityQueue;
        this.priorityFunction = priorityFunction;
    }

    public PriorityFunc getPriorityFunction() {
        return priorityFunction;
    }

    public void setPriorityFunction(PriorityFunc priorityFunction) {
        this.priorityFunction = priorityFunction;
    }

    public PriorityQueue<Board> getPriorityQueue() {
        return priorityQueue;
    }

    public void setPriorityQueue(PriorityQueue<Board> priorityQueue) {
        this.priorityQueue = priorityQueue;
    }
}
