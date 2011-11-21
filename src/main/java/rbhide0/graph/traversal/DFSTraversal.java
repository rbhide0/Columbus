package rbhide0.graph.traversal;

import rbhide0.graph.Graph;
import rbhide0.graph.Vertex;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Ravi Bhide
 */
public class DFSTraversal implements Traversal {
    private Graph _graph;
    private Vertex _root;
    
    private Vertex _nextVertex;

    private Map<Vertex, Color> _visitStatus;

    public DFSTraversal(Graph graph, Vertex root) {
        _graph = graph;
        _root = root;
        _nextVertex = root;
        if (_nextVertex == null) {
            // TODO: Pick a random vertex from the graph.
        }
        _visitStatus = new HashMap<Vertex, Color>();
    }

    public boolean hasNext() {
        return _nextVertex != null;
    }

    public Vertex next() {
        Vertex next = _nextVertex;
        
        return next;
    }

    public void remove() {
        // Removal of a vertex from the iterator is not supported.
        throw new UnsupportedOperationException();
    }

    private static enum Color {
        WHITE, // implies "not visited"
        GRAY, // implies "visiting - some neighbors have not been visited"
        BLACK; // implies "visited, including all its neighbors"
    }
}
