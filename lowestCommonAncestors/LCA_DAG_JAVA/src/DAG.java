

import java.util.*;

@SuppressWarnings("unused")
public class DAG {
	
	private int V;             // of Vertices in graph
	private int E;             // of Edges in graph
	private int[][] adj;       // Adjacency list for vertex v - changed to 2D array
	private int[] outdegree;   // Outdegree of vertex v
	private int[] indegree;    // Indegree of vertex v
	private int[] passed;      // Vertices which has been already passed
	
	// List<Integer> adjListArray[];

	public DAG (int V) {
		if (V < 0)
			throw new IllegalArgumentException("Number of vertices in DAG must be > 0");
		else {
			this.V = V;
			this.E = 0;
			indegree = new int[V];
			outdegree = new int[V];
			passed = new int[V];
		    adj = new int[V][V];
		    for (int i = 0; i<V; i++) { //sets up an empty graph in 2D array
		    	for (int j = 0; j<V; j++) { adj[i][j] = 0; }
		    }
		}
	}
	
	// returns number of Vertices in DAG
	public int V() { return V; }
	
	// returns number of Edges in DAG
	public int E() { return E; }
	
	// throws illegal exception if the vertex put in, is out of bounds
    private void validateVertex (int v) {
        if ( ( v < 0 ) || ( v >= V ) ) {
            throw new IllegalArgumentException("Vertex " + V + " is not b/w 0 and " + (V - 1));
        }
    }
    // adds directed edge from v to w
    public void addEdge (int ver1, int ver2) {
    	validateVertex(ver1);
		validateVertex(ver2);
		adj[ver1][ver2] = 1;
		indegree[ver2]++;
		outdegree[ver1]++;
		E++;
    }
    
    // Removes an edge from v to w
    public void removeEdge(int ver1, int ver2){
        validateVertex(ver1);
        validateVertex(ver2);
        adj[ver1][ver2] = 0;
        indegree[ver2]--;
        outdegree[ver1]--;
        E--;
    }
    
    // returns the number of directed edges out of vertex v
    public int outdegree(int v) {
    	validateVertex(v);
	    return outdegree[v];
    }
    
    // returns the number of directed edges into vertex v
    public int indegree(int v) {
   	    validateVertex(v);
	    return indegree[v];
    }
    
    // returns the vertices adjacent from vertex v
    public int[] adj(int v){
    	validateVertex(v);
    	int[] temp = new int[outdegree[v]]; 
    	int cnt = 0;
    	for(int i = 0; i<V; i++) {
    		if (adj[v][i] == 1) {
    			temp[cnt] = i;
    			cnt++;
    	    }
    	} return temp;
    }
    
    // returns true if the graph is acyclic, else false
    public boolean isAsyclic () {
    	boolean asyclic = false;
    	int cnt = 0;
    	for (int i = 0; i < V ; i++) {
    		passed[cnt] = i;
    		for (int j = 0; j < V ; j++) {
    			for (int k = 0; k < V; k++) {
    				if (passed[k] == j && adj[i][j] == 1) {
    					asyclic = true;
    					return asyclic;
    				}
    			}
    		}
    		cnt++;
    	}
    	return asyclic;
    }
    
    // find the LCA in a directed acyclic graph
    public int findLCA(int ver1, int ver2) {
    	validateVertex(ver1);
    	validateVertex(ver2);
    	isAsyclic();
    	if( E>0 && !isAsyclic() ) {
    		return LCA(ver1,ver2);
    	} else throw new IllegalArgumentException("This graph is not acyclic.");
    }
    
    // helper function for LCA
    private int LCA(int ver1, int ver2) {
    	int[] ver1Arr = new int[E];
    	int[] ver2Arr = new int[E];
    	int ver1Cnt = 0;
    	int ver2Cnt = 0;
    	boolean[] ver1Marked = new boolean[V];
    	boolean[] ver2Marked = new boolean[V];
    	
    	ver1Arr[ver1Cnt] = ver1;
    	ver2Arr[ver2Cnt] = ver2;
    	
    	// mark all vertices as not been passed yet
    	for(int j = 0; j < V; j++) {
    		ver1Marked[j] = false;
    		ver2Marked[j] = false;
    	}
    	
    	for(int i =0; i < V; i++) {
    		ver1Marked[ver1] = true;
    		ver2Marked[ver2] = true;
    		for(int j = 0; j < V; j++) { 
    			if( adj[i][j] == 1 && ver1Marked[i] ) {
    				ver1Cnt++;
    				ver1Arr[ver1Cnt] = j;
    				ver1Marked[j] = true;
    			}
    			if( adj[i][j] == 1 && ver2Marked[i] ) {
    				ver2Cnt++;
    				ver2Arr[ver2Cnt] = j;
    				ver2Marked[j] = true;
    			}
    			if( ver2Arr[ver2Cnt] == ver1Arr[ver1Cnt] ) {
    				return ver2Arr[ver2Cnt];
    			}
    		}
    	}
	return -1; //returns -1 if no ancestors found
   }  
	
}
