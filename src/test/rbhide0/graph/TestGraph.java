package rbhide0.graph;

import junit.framework.TestCase;

import java.util.Set;

/**
 * @author Ravi Bhide
 */
public class TestGraph extends TestCase {
    public void test1() {
        // Create graph.
        Graph graph = new Graph();

        // Create vertices.
        Vertex v1 = new DefaultVertex("v1");
        Vertex v2 = new DefaultVertex("v2");
        Vertex v3 = new DefaultVertex("v3");
        Vertex v4 = new DefaultVertex("v4");
        Vertex v5 = new DefaultVertex("v5");

        // Vertex not in the graph.
        Vertex v6 = new DefaultVertex("v6");

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
        assertFalse(graph.containsVertex(v6));

        // Test the getVertices() call.
        Set<Vertex> vertices = graph.getVertices();
        assertEquals(5, vertices.size());
        assertTrue(vertices.contains(v1));
        assertTrue(vertices.contains(v2));
        assertTrue(vertices.contains(v3));
        assertTrue(vertices.contains(v4));
        assertTrue(vertices.contains(v5));
        assertFalse(vertices.contains(v6));

        // Test that getVertices() returns an unmodifiable object.
        try {
            vertices.add(v6);
            // Add operation succeeded, when it shouldn't have.
            assertTrue(false);
        } catch (UnsupportedOperationException e) {
            // This is the expected scenario.
            assertTrue(true);
        } catch (Throwable t) {
            // Not an expected scenario.
            assertTrue(false);
        }

        // Test edges.
        assertTrue(graph.containsEdge(v1, v2));
        assertTrue(graph.containsEdge(v1, v3));
        assertTrue(graph.containsEdge(v1, v4));
        assertTrue(graph.containsEdge(v1, v5));
        assertTrue(graph.containsEdge(v2, v5));
        assertFalse(graph.containsEdge(v2, v3));

        // Test getEdges() call for vertex v1.
        Set<Vertex> edges1 = graph.getEdges(v1);
        assertEquals(4, edges1.size());
        assertTrue(edges1.contains(v2));
        assertTrue(edges1.contains(v3));
        assertTrue(edges1.contains(v4));
        assertTrue(edges1.contains(v5));
        // Test getEdges() call for vertex v2.
        Set<Vertex> edges2 = graph.getEdges(v2);
        assertEquals(2, edges2.size());
        assertTrue(edges2.contains(v1));
        assertTrue(edges2.contains(v5));
        // Test getEdges() call for vertex v3.
        Set<Vertex> edges3 = graph.getEdges(v3);
        assertEquals(1, edges3.size());
        assertTrue(edges3.contains(v1));
        // Test getEdges() call for vertex v4.
        Set<Vertex> edges4 = graph.getEdges(v4);
        assertEquals(1, edges4.size());
        assertTrue(edges4.contains(v1));
        // Test getEdges() call for vertex v5.
        Set<Vertex> edges5 = graph.getEdges(v5);
        assertEquals(2, edges5.size());
        assertTrue(edges5.contains(v1));
        assertTrue(edges5.contains(v2));

        // Test that getEdges() returns an unmodifiable object.
        try {
            edges5.add(v3);
            // If we reach here, there is an error.
            assertTrue(false);
        } catch (UnsupportedOperationException e) {
            // We expect this exception.
            assertTrue(true);
        } catch (Throwable e) {
            // Not what we expected.
            assertTrue(false);
        }

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
