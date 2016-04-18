package graph;
import java.util.HashSet;
import java.util.Set;
/**
 *
 * An implementation of Graph that uses a linked representation 
 * (vertex and edge objects). 
 * <p>
 * It is called 'AdjacencyMapGraph' because each vertex stores an adjacency 
 * Map of edges incident upon it (as opposed to an adjacency list).
 *<p>
 * Operations such as inserting or deleting an edge involve updating the
 * adjacency maps of the vertices incident upon it.
 *  
 * @author Stephan Jamieson
 * @version 1/4/2016
 */
 public class AdjacencyMapGraph<V, E> implements Graph<V, E> {
	 
	private Set<AdjacencyMapVertex<V, E>> vertices;
	private Set<AdjacencyMapEdge<E, V>> edges;
	
	/**
	 * Create a new graph.
	 */
	public AdjacencyMapGraph() {
		this.vertices = new HashSet<AdjacencyMapVertex<V, E>>();
		this.edges = new HashSet<AdjacencyMapEdge<E, V>>();
	}
	
	public Iterable<AdjacencyMapVertex<V, E>> getVertices() { return vertices; }

	public Iterable<AdjacencyMapEdge<E, V>> getEdges() { return edges; }
   
	public Iterable<AdjacencyMapEdge<E, V>> getIncidentOn(final Vertex<V> vertex) {
		// Your code here
		return null;
	}

	public Iterable<AdjacencyMapVertex<V, E>> getNeighbours(Vertex<V> v) {
		// Your code here
		return null;
	}
		
	public Pair<AdjacencyMapVertex<V, E>> getVertices(final Edge<E> edge) {
		// Your code here
		return null;
	}
    
	public AdjacencyMapVertex<V, E> getOpposite(final Vertex<V> vertex, final Edge<E> edge){
		// Your code here
		return null;
	}
    
	public boolean areAdjacent(final Vertex<V> v, final Vertex<V> w) {
		// Your code here
		return false;
	}
   
	public V replace(final Vertex<V> vertex, final V value) {
		// Your code here
		return null;
	}

	public E replace(final Edge<E> edge, final E value) {
		// Your code here
		return null;
	}
   
	public AdjacencyMapVertex<V, E> insert(final V value) {
		// Your code here
		return null;
	}

	public AdjacencyMapEdge<E, V> insert(Vertex<V> v, Vertex<V> w, E value) {
		// Your code here
		return null;
	}
   
	public V remove(Vertex<V> vertex) {
		// Your code here
		return null;
	}	

	public E remove(Edge<E> edge)  {
		// Your code here
		return null;
	}
	
	public void clearMarks() {
		// Your code here
	}		
	/*
	 * The following methods should be used to create edges and vertices.
	 * They ensure that the objects created are marked as belonging to
	 * this graph object.
	 */
	 
	 
	private AdjacencyMapVertex<V, E> createVertex(final V value) {
		final AdjacencyMapVertex<V, E> vertex = new AdjacencyMapVertex<V, E>(value);
		vertex.setOwner(this);
		return vertex;
	}

	private AdjacencyMapEdge<E, V> createEdge(final AdjacencyMapVertex<V,E> v, 
		final AdjacencyMapVertex<V, E> w, final E value) {
			final AdjacencyMapEdge<E, V> edge = new AdjacencyMapEdge<E, V>(v, w, value);
			edge.setOwner(this);
			return edge;
	}

	/*
	 * The Graph<V, E> interface uses Edge<E> and Vertex<V> data types.
	 * Internally, an AdjacencyMapGraph<V, E> uses AdjacencyMapVertex<V, E>
	 * and AdjacencyMapEdge<E, V> data types. The following methods
	 * may be used to safely cast from one to the other.
	 */
	 
	@SuppressWarnings("unchecked")
	private AdjacencyMapEdge<E, V> cast(final Edge<E> edge) {
		assert(edge.belongsTo(this));
		return (AdjacencyMapEdge<E, V>)edge;		
	}

	/**
	 *
	 */
	@SuppressWarnings("unchecked")	
	private AdjacencyMapVertex<V, E> cast(final Vertex<V> vertex) {
		assert(vertex.belongsTo(this));
		return (AdjacencyMapVertex<V, E>)vertex;
	}
	
	/**
	 * Obtain a dump (for debugging purposes) of the graph representation.
	 */ 
	public void dump() {
		dumpVertices();
		dumpEdges();
		System.out.println();
	} 
	
	/**
	 * Obtain a dump (for debugging purposes) of the vertex representations.
	 */ 
	public void dumpVertices() {
		System.out.print("\nVERTICES");
		for (AdjacencyMapVertex<V, E> vertex : vertices) {
			vertex.dump();
		}
	}
	
	/**
	 * Obtain a dump (for debugging purposes) of the edge representations.
	 */ 
	public void dumpEdges() {
		System.out.print("\nEDGES");
		for (AdjacencyMapEdge<E, V> edge : edges) {
			System.out.print("\n"+edge);
		}
	}	
	
 }
