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
 public class AdjacencyMapGraph<V, E> implements Graph<V, E> 
{
	 
	private Set<AdjacencyMapVertex<V, E>> vertices;
	private Set<AdjacencyMapEdge<E, V>> edges;
	
	//Create a new graph.
	public AdjacencyMapGraph() 
    {
		this.vertices = new HashSet<AdjacencyMapVertex<V, E>>();
		this.edges = new HashSet<AdjacencyMapEdge<E, V>>();
	}//end
	
    //getvertices
	public Iterable<AdjacencyMapVertex<V, E>> getVertices() 
    { 
        return vertices; 
    }//end

    //getedges
	public Iterable<AdjacencyMapEdge<E, V>> getEdges() 
    { 
        return edges; 
    }//end
   
	public Iterable<AdjacencyMapEdge<E, V>> getIncidentOn(final Vertex<V> vertex) 
    {
        //need to cast it as it works on an adjacency map vertex and not a vertex
		return this.cast(vertex).getIncidentOn();
	}//end
    
    //getNeighbours
	public Iterable<AdjacencyMapVertex<V, E>> getNeighbours(Vertex<V> v) 
    {
	    //need to cast it as it works on an adjacency map vertex and not a vertex
		return this.cast(v).getNeighbours();
	}//end
	
    //getVertices
	public Pair<AdjacencyMapVertex<V, E>> getVertices(final Edge<E> edge) 
    {
		//need to cast it as it works on an adjacency map vertex and not a edge
		return this.cast(edge).getVertices();
	}//end
    
    //getopposite
	public AdjacencyMapVertex<V, E> getOpposite(final Vertex<V> vertex, final Edge<E> edge)
    {
		//need to cast it as it works on an adjacency map vertex and not a edge
		return cast(edge).getOpposite(cast(vertex));
	}//end
    
    //areAdjacent
	public boolean areAdjacent(final Vertex<V> v, final Vertex<V> w) 
    {
		return cast(v).isAdjacentTo(cast(w));
	}//end
   
    //replace vertex
	public V replace(final Vertex<V> vertex, final V value) 
    {
		V ans = vertex.getValue();
        //these are the vertices
        Iterable<AdjacencyMapVertex<V, E>> vertices = this.getVertices();
        //String temp : crunchifyList
        for (AdjacencyMapVertex<V, E> vertice : vertices)
        {
            if (vertice.equals(cast(vertex)))
            {
                
                vertex.setValue(value);
                //need to break the for loop to not return false at the end if the last ones are actually not equal
                break;
            }//end if 
        }//end for 
		return ans;
	}//end

    //replace edges
	public E replace(final Edge<E> edge, final E value) 
    {
		E ans = edge.getValue();
        //these are the vertices
        Iterable<AdjacencyMapEdge<E, V>> e = this.getEdges();
        //String temp : crunchifyList
        for (AdjacencyMapEdge<E, V> ed : e)
        {
            if (ed.equals(cast(edge)))
            {
                edge.setValue(value);
                //need to break the for loop to not return false at the end if the last ones are actually not equal
                break;
            }//end if 
        }//end for 
		return ans;
	}//end
   
    //insert
	public AdjacencyMapVertex<V, E> insert(final V value) 
    {
		AdjacencyMapVertex<V, E> AMVertex = this.createVertex(value);
        this.vertices.add(AMVertex);
		return AMVertex;
	}//end
    
    //insert vertex
	public AdjacencyMapEdge<E, V> insert(Vertex<V> v, Vertex<V> w, E value) 
    {
		AdjacencyMapEdge<E, V> AMEdge = this.createEdge(cast(v),cast(w),value);
        this.edges.add(AMEdge);
        cast(v).insert(AMEdge);
        cast(w).insert(AMEdge);
		return AMEdge;
	}//end
   
    //remove vertex
	public V remove(Vertex<V> vertex) 
    {
		Iterable<AdjacencyMapVertex<V, E>> v = cast(vertex).getNeighbours();
        for (AdjacencyMapVertex<V, E> ve : v)
        {
            ve.remove(cast(vertex));
        }//end for  
        Iterable<AdjacencyMapEdge<E, V>> two = cast(vertex).getIncidentOn();
        
        for(AdjacencyMapEdge<E, V> ver : two)
        {
           this.edges.remove(cast(ver));
        }//end outter for
		this.vertices.remove(cast(vertex));
        return vertex.getValue();
	}//end	

    //remove edge
	public E remove(Edge<E> edge)  
    {
		//Pair<AdjacencyMapVertex<V, E>> paired = cast(edge).getVertices();
        edges.remove(cast(edge));
        return null;	
    }//end
	
    //clear all the marks

	public void clearMarks() 
    {
		Iterable<AdjacencyMapEdge<E, V>> e = this.getEdges();
        Iterable<AdjacencyMapVertex<V, E>> v = this.getVertices();
	
        for (AdjacencyMapEdge<E, V> ed : e)
        {
            ed.clearMark();
        }//end for 

        for (AdjacencyMapVertex<V, E> ve : v)
        {
            ve.clearMark();
        }//end for 
	}//end		
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
