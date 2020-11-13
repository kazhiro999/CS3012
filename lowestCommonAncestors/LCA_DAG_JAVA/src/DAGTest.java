
import static org.junit.Assert.*;

import org.junit.Test;

public class DAGTest {
	
	DAG acyclic = new DAG(8);                            // create an acyclic graph
	DAG cycle = new DAG(9);                              // create a cycle graph
	DAG directAcyclic = new DAG(9);                      // creates an direct acyclic graph
	
	@Test(expected = IllegalArgumentException.class)    
	public void testDAG() {
		@SuppressWarnings("unused")
		DAG negativeTest = new DAG(-2);                  
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testIndegree() {
		acylcicGraph();                                  // is acyclic
		cycleGraph();                                    // is cyclic
		assertEquals("fails due the exception thrown", null, acyclic.indegree(-3));
		assertEquals("A straight line of vertices so each will only have one", 1, acyclic.indegree(5));
		assertEquals("2 edges leading into vertex eight", 2, cycle.indegree(8));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testOutdegree() {
		acylcicGraph();                                  // is acyclic
		cycleGraph();                                    // is cyclic
		assertEquals("fails due to the exception thrown for out of bounds number", null, acyclic.outdegree(9));
		assertEquals("0 only has one outdegree", 1, acyclic.outdegree(0));	
		assertEquals("0 for the cycle graph has two outdegree edges", 2, cycle.outdegree(0));
	}
	
	@Test
	public void testAdj() {
		acylcicGraph();                                  // is acyclic
		cycleGraph();                                    // is cyclic
		assertArrayEquals(new int[]{4}, acyclic.adj(3)); 
		assertArrayEquals(new int[]{1,6}, cycle.adj(3)); 
	}
	
	@Test
	public void testE() {
		acylcicGraph();                                  // is acyclic
		cycleGraph();                                    // is cyclic
		directAcyclicGraph();
		assertEquals("Number of edges within the graph should be", 7, acyclic.E());
		assertEquals("Number of edges within the graph should be", 9, cycle.E());
		assertEquals("Number of verices within the graph should be", 9, directAcyclic.E());
	}
	
	@Test
	public void testV() {
		acylcicGraph();
		cycleGraph();
		directAcyclicGraph();
		assertEquals("Number of vertices within the graph should be", 8, acyclic.V());
		assertEquals("Number of vertices within the graph should be", 9, cycle.V());
		assertEquals("Number of verices within the graph should be", 9, directAcyclic.V());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testValidVertex() {
		DAG validTest = new DAG(3);
		//try add negative values, will throw exception
		validTest.addEdge(-1, 2);
		validTest.addEdge(1, -2);
		validTest.addEdge(-1, -2);
		assertEquals("Should contain no edges", 0, validTest.E());
		//will not throw exception, but let it pass through
		validTest.addEdge(1, 2);
		assertEquals("Should contain 1 edge", 1, validTest.E());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testAddEdge() {
		acylcicGraph();                                  // is acyclic
		cycleGraph();                                    // is cyclic
		assertEquals("Number of edges for DAG is", 7, acyclic.E());
		acyclic.addEdge(5, 8);
		assertEquals("Number of edges for DAG should increase to", 8, acyclic.E());
		acyclic.addEdge(-1, -1);
		assertEquals("This should do nothing as an exception is thrown, once the values are validated", 0, acyclic.E());
	}
	
	@Test
	public void testCycle() {
		acylcicGraph();                                  // is acyclic
		cycleGraph();                                    // is cycle
		directAcyclicGraph();                            // acyclic
		assertFalse(acyclic.isAsyclic());                
		assertTrue(cycle.isAsyclic());                   
		assertFalse(directAcyclic.isAsyclic());
		DAG emptyGraph = new DAG(0);
		assertFalse(emptyGraph.isAsyclic());             // No graph therefore no cycle
	}
	
	@Test(expected = IllegalArgumentException.class) 
	public void testLCA() {
		acylcicGraph();
		cycleGraph();
		directAcyclicGraph();
		//test the lca for both acyclic and cycled graph using two vertices
		assertEquals("Can be its own ancestor", 3, acyclic.findLCA(2, 3));
		assertEquals("Because it is cyclic it will throw an exception", 1, cycle.findLCA(1, 4));
		//showing different levels within the graph
		assertEquals("", 7, directAcyclic.findLCA(3, 4));
		assertEquals("", 7, directAcyclic.findLCA(1, 4));
		assertEquals("", 7, directAcyclic.findLCA(5, 2));
		//swapping around the vertices v and w
		assertEquals("", 5, directAcyclic.findLCA(1, 5));
		assertEquals("", 5, directAcyclic.findLCA(5, 1));
		//same v as w
		assertEquals("Can be its own ancestor", 3, acyclic.findLCA(3, 3));
		//empty graph
		@SuppressWarnings("unused")
		DAG emptyG = new DAG(0);
		assertEquals("Should throw exception", null, acyclic.findLCA(3, 3));
	}
	
	public void acylcicGraph() {
		//1->2->3->4->5->6->7
		acyclic.addEdge(0, 1);
		acyclic.addEdge(1, 2);
		acyclic.addEdge(2, 3);
		acyclic.addEdge(3, 4);
		acyclic.addEdge(4, 5);
		acyclic.addEdge(5, 6);
		acyclic.addEdge(6, 7);
	}
	
	public void directAcyclicGraph() {
		//  -> 1 -> 3 -> 5 ->
		//0    ^              7 -> 8             
		//  -> 2 -> 4 -> 6 ->      
		directAcyclic.addEdge(0, 1);
		directAcyclic.addEdge(0, 2);
		directAcyclic.addEdge(1, 3);
		directAcyclic.addEdge(2, 4);
		directAcyclic.addEdge(3, 5);
		directAcyclic.addEdge(4, 6);
		directAcyclic.addEdge(5, 7);
		directAcyclic.addEdge(6, 7);
		directAcyclic.addEdge(7, 8);
	}
	
	public void cycleGraph(){
		//  -> 1 <- 3 -> 6 -> 8 
		//0    v    ^         ^
		//  -> 2 -> 4         7
		cycle.addEdge(0, 1);
		cycle.addEdge(0, 2);
		cycle.addEdge(1, 2);
		cycle.addEdge(2, 4);
		cycle.addEdge(4, 3);
		cycle.addEdge(3, 1);
		cycle.addEdge(3, 6);
		cycle.addEdge(6, 8);
		cycle.addEdge(7, 8);
	}	
}
