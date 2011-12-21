package adt.graph;

public class Vertex {
    public int id;
    
    public Vertex(int id) {
        this.id = id;
    }
    
    @Override
    public String toString() {
        return "v" + id;
    }
}
