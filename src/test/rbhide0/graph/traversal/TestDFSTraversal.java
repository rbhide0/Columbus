package rbhide0.graph.traversal;

import junit.framework.TestCase;
import rbhide0.graph.DefaultVertex;
import rbhide0.graph.Graph;
import rbhide0.graph.Vertex;

public class TestDFSTraversal extends TestCase {
    public void test1() {
        // Graph: v1 -> v2 -> v3.
        Graph graph = new Graph();

        // Create vertices.
        Vertex v1 = new DefaultVertex("v1");
        Vertex v2 = new DefaultVertex("v2");
        Vertex v3 = new DefaultVertex("v3");

        // Add vertices.
        graph.addVertex(v1);
        graph.addVertex(v2);
        graph.addVertex(v3);

        // Add edges.
        graph.addEdge(v1, v2);
        graph.addEdge(v2, v3);

        // Basic sanity checking.
        assertEquals(3, graph.getNumVertices());
        assertEquals(2, graph.getNumEdges());

        // DFS traversal should give v1, v2, v3
        // in that order.
        DFSTraversal dfsTraversal = new DFSTraversal(graph, v1);
        assertEquals(true, dfsTraversal.hasNext());
        assertEquals(v1, dfsTraversal.next());
        assertEquals(true, dfsTraversal.hasNext());
        assertEquals(v2, dfsTraversal.next());
        assertEquals(true, dfsTraversal.hasNext());
        assertEquals(v3, dfsTraversal.next());

        // We should now be done with the traversal.
        assertEquals(false, dfsTraversal.hasNext());
    }

    public void test2() {
        // Graph: v1 -> v2 -> {v3, v4}.
        Graph graph = new Graph();

        // Create vertices.
        Vertex v1 = new DefaultVertex("v1");
        Vertex v2 = new DefaultVertex("v2");
        Vertex v3 = new DefaultVertex("v3");
        Vertex v4 = new DefaultVertex("v4");

        // Add vertices.
        graph.addVertex(v1);
        graph.addVertex(v2);
        graph.addVertex(v3);
        graph.addVertex(v4);

        // Add edges.
        graph.addEdge(v1, v2);
        graph.addEdge(v2, v3);
        graph.addEdge(v2, v4);

        // Basic sanity checking.
        assertEquals(4, graph.getNumVertices());
        assertEquals(3, graph.getNumEdges());

        // DFS traversal should give v1, v2, v3
        // in that order.
        DFSTraversal dfsTraversal = new DFSTraversal(graph, v1);
        assertEquals(true, dfsTraversal.hasNext());
        assertEquals(v1, dfsTraversal.next());
        assertEquals(true, dfsTraversal.hasNext());
        assertEquals(v2, dfsTraversal.next());

        assertEquals(true, dfsTraversal.hasNext());
        Vertex dfs3 = dfsTraversal.next();
        assertTrue(dfs3.equals(v3) || dfs3.equals(v4));

        assertEquals(true, dfsTraversal.hasNext());
        Vertex dfs4 = dfsTraversal.next();
        assertTrue(dfs4.equals(v3) || dfs4.equals(v4));
        assertFalse(dfs3.equals(dfs4));

        // We should now be done with the traversal.
        assertEquals(false, dfsTraversal.hasNext());
    }


    public void test3() {
        // Graph:
        // v1 -> {v2, v3}
        // v2 -> v4 -> v5 -> v6
        // v3 -> v7 -> v8 -> v9
        Graph graph = new Graph();

        // Create vertices.
        Vertex v1 = new DefaultVertex("v1");
        Vertex v2 = new DefaultVertex("v2");
        Vertex v3 = new DefaultVertex("v3");
        Vertex v4 = new DefaultVertex("v4");
        Vertex v5 = new DefaultVertex("v5");
        Vertex v6 = new DefaultVertex("v6");
        Vertex v7 = new DefaultVertex("v7");
        Vertex v8 = new DefaultVertex("v8");
        Vertex v9 = new DefaultVertex("v9");

        // Add vertices.
        graph.addVertex(v1);
        graph.addVertex(v2);
        graph.addVertex(v3);
        graph.addVertex(v4);
        graph.addVertex(v5);
        graph.addVertex(v6);
        graph.addVertex(v7);
        graph.addVertex(v8);
        graph.addVertex(v9);

        // Add edges.
        graph.addEdge(v1, v2);
        graph.addEdge(v1, v3);
        graph.addEdge(v2, v4);
        graph.addEdge(v4, v5);
        graph.addEdge(v5, v6);
        graph.addEdge(v3, v7);
        graph.addEdge(v7, v8);
        graph.addEdge(v8, v9);

        // DFS traversal should either give:
        // v1, v2, v4, v5, v6, v3, v7, v8, v9 OR
        // v1, v3, v7, v8, v9, v2, v4, v5, v6.
        // Both are correct. We assert accordingly.
        DFSTraversal dfsTraversal = new DFSTraversal(graph, v1);

        assertTrue(dfsTraversal.hasNext());
        assertEquals(v1, dfsTraversal.next());

        assertTrue(dfsTraversal.hasNext());
        Vertex nextVertex = dfsTraversal.next();
        assertTrue(v2.equals(nextVertex) || v3.equals(nextVertex));
        
        if (v2.equals(nextVertex)) {
            // We expect the following DFS traversal overall.
            // v1, v2, v4, v5, v6, v3, v7, v8, v9
            assertTrue(dfsTraversal.hasNext());
            assertEquals(v4, dfsTraversal.next());
            assertTrue(dfsTraversal.hasNext());
            assertEquals(v5, dfsTraversal.next());
            assertTrue(dfsTraversal.hasNext());
            assertEquals(v6, dfsTraversal.next());
            assertTrue(dfsTraversal.hasNext());
            assertEquals(v3, dfsTraversal.next());
            assertTrue(dfsTraversal.hasNext());
            assertEquals(v7, dfsTraversal.next());
            assertTrue(dfsTraversal.hasNext());
            assertEquals(v8, dfsTraversal.next());
            assertTrue(dfsTraversal.hasNext());
            assertEquals(v9, dfsTraversal.next());
        } else if (v3.equals(nextVertex)) {
            // We expect the following DFS traversal overall.
            // v1, v3, v7, v8, v9, v2, v4, v5, v6.
            assertTrue(dfsTraversal.hasNext());
            assertEquals(v7, dfsTraversal.next());
            assertTrue(dfsTraversal.hasNext());
            assertEquals(v8, dfsTraversal.next());
            assertTrue(dfsTraversal.hasNext());
            assertEquals(v9, dfsTraversal.next());
            assertTrue(dfsTraversal.hasNext());
            assertEquals(v2, dfsTraversal.next());
            assertTrue(dfsTraversal.hasNext());
            assertEquals(v4, dfsTraversal.next());
            assertTrue(dfsTraversal.hasNext());
            assertEquals(v5, dfsTraversal.next());
            assertTrue(dfsTraversal.hasNext());
            assertEquals(v6, dfsTraversal.next());
        }

        // We have traversed the entire tree.
        // We should have no vertex left at this point.
        assertFalse(dfsTraversal.hasNext());
    }
}
