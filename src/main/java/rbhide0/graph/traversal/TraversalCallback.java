package rbhide0.graph.traversal;

import rbhide0.graph.Vertex;

/**
 * @author Ravi Bhide
 */
public interface TraversalCallback {
    /**
     * Called when the given vertex is being visited for the first time.
     * @param v
     */
    public void begin(Vertex v);

    /**
     * Called when the given vertex is being visited,
     * for the first or subsequent times.
     * @param v
     */
    public void visit(Vertex v);

    /**
     * Called when the given vertex is
     * @param v
     */
    public void end(Vertex v);
}
