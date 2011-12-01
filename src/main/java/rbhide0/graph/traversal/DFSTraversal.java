package rbhide0.graph.traversal;

import rbhide0.graph.Graph;
import rbhide0.graph.Vertex;
import rbhide0.util.Stack;

import java.util.*;

/**
 * @author Ravi Bhide
 *
 * TODO: make this traversal work on disconnected graphs as well.
 */
public class DFSTraversal implements Traversal {
    private Graph _graph;
    private Vertex _start;

    private Map<Vertex, Color> _vertexColor;
    private Stack<Vertex> _stack;

    private Vertex _next;
    private boolean _nextDelivered;

    public DFSTraversal(Graph graph, Vertex start) {
        _graph = graph;
        _start = start;

        _vertexColor = new HashMap<Vertex, Color>();
        _stack = new Stack<Vertex>();

        _nextDelivered = true;
    }

    public boolean hasNext() {
        if (_nextDelivered) {
            // Previous computation was delivered.
            // Compute the next vertex in the traversal.
            _next = getNext();
            if (_next != null) {
                _nextDelivered = false;
            }
        }

        return (_next != null);
    }

    public Vertex next() {
        if (_next != null) {
            _vertexColor.put(_next, Color.GRAY);
            _stack.push(_next);
        }
        _nextDelivered = true;
        return _next;
    }

    private Vertex getNext() {
        // Choose our next vertex (called "current vertex").
        Vertex unvisitedNeighbor = null;

        // Choose the first available, unvisited neighbor of a previous vertex.
        while (_stack.size() > 0) {
            Vertex prevVertex = _stack.peek();
            // Look up all neighbors of the previous vertex.
            Set<Vertex> neighbors = _graph.getEdges(prevVertex);
            for (Vertex neighbor : neighbors) {
                // Do we know the color of this neighbor?
                Color color = _vertexColor.get(neighbor);
                if ((color != Color.BLACK) && (color != Color.GRAY)) {
                    // This neighbor has not been visited yet.
                    unvisitedNeighbor = neighbor;
                    break;
                }
            }

            // Have we found an unvisited neighbor?
            if (unvisitedNeighbor == null) {
                // No - all neighbors of the previous vertex were visited.
                // Mark this vertex as being done.
                _vertexColor.put(prevVertex, Color.BLACK);
                _stack.pop(); // Pop this vertex off the stack.
            } else {
                // Yes - we have found an unvisited neighbor.
                break;
            }
        }

        if (unvisitedNeighbor == null) {
            // Check if we are done or yet to begin.
            if (!_vertexColor.containsKey(_start)) {
                // We are yet to begin.
                unvisitedNeighbor = _start;
            }
        }

        return unvisitedNeighbor;
    }

    public void remove() {
        // Removal of a vertex from the iterator is not supported.
        throw new UnsupportedOperationException();
    }

    private static enum Color {
        WHITE, // implies "not visited"
        GRAY, // implies "visiting, i.e. in the stack - some neighbors have not been visited"
        BLACK; // implies "visited, including all its neighbors"
    }
}
