package rbhide0.graph;

import junit.framework.TestCase;

/**
 * @author Ravi Bhide
 */
public class TestGraph extends TestCase {
    // Test implementation of a Vertex.
    public static class DefaultVertex implements Vertex {
        private String _label;

        public DefaultVertex(String label) {
            _label = label;
        }

        public Object getLabel() {
            return _label;
        }

        public Object getSatelliteData() {
            return this;
        }

        public boolean equals(Object v) {
            boolean equals = false;
            if (v instanceof DefaultVertex) {
                if (v != null) {
                    if (_label == null) {
                        equals = (((DefaultVertex) v)._label == null);
                    } else {
                        equals = _label.equals(((DefaultVertex) v)._label);
                    }
                }
            }
            return equals;
        }

        public int hashCode() {
            return _label == null ? 0 : _label.hashCode();
        }
    }

    public void test1() {
        // Create graph.
        Graph graph = new Graph();

        // Create vertices.
        Vertex v1 = new DefaultVertex("v1");
        Vertex v2 = new DefaultVertex("v2");
        Vertex v3 = new DefaultVertex("v3");
        Vertex v4 = new DefaultVertex("v4");
        Vertex v5 = new DefaultVertex("v5");

        // Add vertices.
        graph.addVertex(v1);
        graph.addVertex(v2);
        graph.addVertex(v3);
        graph.addVertex(v4);
        graph.addVertex(v5);

        // Add edges.
        graph.addEdge(v1, v2);
        graph.addEdge(v1, v3);
        graph.addEdge(v1, v4);
        graph.addEdge(v1, v5);
        graph.addEdge(v2, v5);

        // Test vertices.
        assertTrue(graph.containsVertex(v1));
        assertTrue(graph.containsVertex(v2));
        assertTrue(graph.containsVertex(v3));
        assertTrue(graph.containsVertex(v4));
        assertTrue(graph.containsVertex(v5));

        // Test edges.
        assertTrue(graph.containsEdge(v1, v2));
        assertTrue(graph.containsEdge(v1, v3));
        assertTrue(graph.containsEdge(v1, v4));
        assertTrue(graph.containsEdge(v1, v5));
        assertTrue(graph.containsEdge(v2, v5));
        assertFalse(graph.containsEdge(v2, v3));

        // Test statistics.
        assertEquals(5, graph.getNumVertices());
        assertEquals(5, graph.getNumEdges());

        // Test the degree of each vertex.
        assertEquals(4, graph.getDegree(v1));
        assertEquals(2, graph.getDegree(v2));
        assertEquals(1, graph.getDegree(v3));
        assertEquals(1, graph.getDegree(v4));
        assertEquals(2, graph.getDegree(v5));
    }
}
