package rbhide0.graph;

import java.util.*;

/**
 * An undirected graph.
 * @author Ravi Bhide
 */
public class Graph {
    // Adjacency list captures the edge information of the graph.
    private Map<Vertex, Set<Vertex>> _adjacencyList;

    // Sum of the degrees of all vertices in the graph. This is used to
    // find the number of edges in the graph. This is an optimization variable,
    // since the same information can be obtained from the adjacency list.
    private int _sumDegrees;

    public Graph() {
        _adjacencyList = new HashMap<Vertex, Set<Vertex>>();
    }

    public boolean containsVertex(Vertex v) {
        return _adjacencyList.containsKey(v);
    }

    public void addVertex(Vertex v) {
        if (!containsVertex(v)) {
            _adjacencyList.put(v, null);
        }
    }

    /**
     * Returns all the vertices in this graph.
     * @return all vertices in this graph.
     */
    public Set<Vertex> getVertices() {
        return Collections.unmodifiableSet(_adjacencyList.keySet());
    }

    public boolean containsEdge(Vertex v1, Vertex v2) {
        boolean containsEdge = false;
        if (containsVertex(v1) && containsVertex(v2)) {
            Set<Vertex> edges = _adjacencyList.get(v1);
            containsEdge = edges.contains(v2);
        }
        return containsEdge;
    }

    public void addEdge(Vertex v1, Vertex v2) {
        addVertex(v1);
        addVertex(v2);
        addEdgeFrom(v1, v2);
        addEdgeFrom(v2, v1);
    }

    public Set<Vertex> getEdges(Vertex v) {
        Set<Vertex> edges = _adjacencyList.get(v);
        return (edges == null) ? (Set<Vertex>) Collections.EMPTY_SET : Collections.unmodifiableSet(edges);
    }

    public int getNumVertices() {
        return _adjacencyList.size();
    }

    public int getNumEdges() {
        return _sumDegrees / 2;
    }

    public int getDegree(Vertex v) {
        Set<Vertex> edges = _adjacencyList.get(v);
        return (edges == null) ? 0 : edges.size();
    }

    private void addEdgeFrom(Vertex v1, Vertex v2) {
        Set<Vertex> edges = _adjacencyList.get(v1);
        if (edges == null) {
            edges = new HashSet<Vertex>();
            _adjacencyList.put(v1, edges);
        }
        if (!edges.contains(v2)) {
            edges.add(v2);
            _sumDegrees++;
        }
    }
}
