package adt.graph;

import java.util.Map;

public class Result {
    private Map<Vertex, Integer> potential;
    private boolean negativeLoopDetected;
    
    public Result(Map<Vertex, Integer> potential, boolean negativeLoopDetected) {
        this.potential = potential;
        this.negativeLoopDetected = negativeLoopDetected;
    }
    
    public Map<Vertex, Integer> getPotential() {
        return this.potential;
    }
    
    public boolean isNegativeLoopDetected() {
        return negativeLoopDetected;
    }
    
    public int get(Vertex v) {
        return potential.get(v);
    }
}
