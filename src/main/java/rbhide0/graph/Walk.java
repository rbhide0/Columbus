package rbhide0.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A walk is a sequence of steps. Each step is a step on a vertex.
 * A walk can optionally be constrained to remember the last n steps,
 * in which case, it will forget the oldest steps first.
 *
 * NOTE: This is not a thread-safe object. It can be made thread-safe
 * with little effort, at the cost of some runtime performance. But if
 * you need a thread-safe object, you are probably doing something wrong.
 *
 * @author Ravi Bhide
 */
public class Walk {
    // Walk is a queue of vertices.
    // Head of the queue is the last element in the list.
    // Tail of the queue is the first element in the list.
    private List<Vertex> _walk;
    private Map<Vertex, Integer> _numVisitsMap;

    private int _maxSteps;

    public Walk() {
        _walk = new ArrayList<Vertex>();
        _numVisitsMap = new HashMap<Vertex, Integer>();
        _maxSteps = Integer.MAX_VALUE;
    }

    /**
     * Sets the maximum number of steps to remember for this walk.
     * NOTE: if there are more than these many steps, the oldest steps
     * are forgotten first.
     * @param numSteps the maximum number of steps to remember for this walk
     */
    public void setMaxSteps(int numSteps) {
        _maxSteps = numSteps;
    }

    /**
     * Returns the maximum number of steps that are remembered by this walk.
     * @return the maximum number of steps that are remembered by this walk
     */
    public int getMaxSteps() {
        return _maxSteps;
    }

    /**
     * Visits the given vertex.
     * @param v vertex to visit
     */
    public void visit(Vertex v) {
        // Do we have too many vertices in the walk?
        if (_walk.size() >= _maxSteps - 1) {
            // Remove older elements from the queue.
            int numElementsToRemove = _walk.size() - _maxSteps + 1;
            for (int i=0; i<numElementsToRemove; i++) {
                // Repeatedly remove first element from the list.
                // Usually this loop will be run no more than once.
                remove(0);
            }
            // Queue now has the right cardinality.
        }

        add(v);
    }

    /**
     * Returns true if the given vertex has been visited, false otherwise.
     * @param v vertex to visit
     * @return true if the given vertex has been visited, false otherwise
     */
    public boolean hasVisited(Vertex v) {
        return _numVisitsMap.containsKey(v);
    }

    // Private method to remove a vertex from any step in our walk.
    private void remove(int stepNumber) {
        // Validate step number.
        if ((stepNumber >= 0) && (stepNumber < _walk.size())) {
            // Get the vertex at the given step number.
            Vertex v = _walk.remove(stepNumber);
            // Decrement the number of visits to this vertex.
            Integer numVisits = _numVisitsMap.get(v);
            numVisits--; // this is assumed to be be non-null.
            // If this vertex is being completely removed from the walk,
            // remove its information from the num-visits map.
            if (numVisits == 0) {
                _numVisitsMap.remove(v);
            } else {
                _numVisitsMap.put(v, numVisits);
            }
        }
    }

    // Private method to add vertex to the end of our walk.
    private void add(Vertex v) {
        // Add vertex at the end of our walk.
        _walk.add(v);

        // Increment the number of visits to this vertex.
        Integer numVisits = _numVisitsMap.get(v);
        if (numVisits == null) {
            numVisits = 1;
        } else {
            numVisits++;
        }
        _numVisitsMap.put(v, numVisits);
    }
}
