package rbhide0.util;

import junit.framework.TestCase;

import rbhide0.graph.DefaultVertex;

public class TestStack extends TestCase {
    public void test1() {
        Stack<DefaultVertex> stack = new Stack<DefaultVertex>();
        DefaultVertex v1 = new DefaultVertex("v1");
        DefaultVertex v2 = new DefaultVertex("v2");
        DefaultVertex v3 = new DefaultVertex("v3");

        stack.push(v1);
        stack.push(v2);
        stack.push(v3);

        assertEquals(3, stack.size());
        assertEquals(v3, stack.peek());
        assertEquals(3, stack.size());
        assertEquals(v3, stack.pop());

        assertEquals(2, stack.size());
        assertEquals(v2, stack.peek());
        assertEquals(2, stack.size());
        assertEquals(v2, stack.pop());

        assertEquals(1, stack.size());
        assertEquals(v1, stack.peek());
        assertEquals(1, stack.size());
        assertEquals(v1, stack.pop());

        try {
            // Nothing in the stack.
            // Peeking should throw an exception.
            stack.peek();
            assertTrue(false);
        } catch (Throwable t) {
            assertTrue(true);
        }

        try {
            // Nothing in the stack.
            // Popping should throw an exception.
            stack.pop();
            assertTrue(false);
        } catch (Throwable t) {
            assertTrue(true);
        }
    }
}
