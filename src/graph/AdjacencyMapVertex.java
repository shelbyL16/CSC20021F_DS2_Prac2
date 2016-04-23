package graph;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
/**https://docs.oracle.com/javase/8/docs/api/java/util/Map.html
 * 
 * An AdjacencyMapVertex<V, E> is the concrete type of Vertex<V> object
 * used in the AdjacencyMapGraph<V, E> implementation of Graphh<V,E>.
 * <p>
 * As its name suggests, an AdjacencyMapVertex stores a map of the edges
 * incident upon it. 
 * <p>
 * The map contains (key, value) pairs of AdjacencyMapVertex
 * and AdjacencyMapEdge. The key is a neighbour, and the value is the
 * edge that connects them. 
 * 
 * 
 * @author Stephan Jamieson 
 * @version 1/4/2016
 */
class AdjacencyMapVertex<V, E> extends Vertex<V> 
{
    //this is a map, therefore can use map classes
    private Map<AdjacencyMapVertex<V, E>, AdjacencyMapEdge<E, V>> edges;

	//Create an AdjacencyMapVertex with the given value.
    AdjacencyMapVertex(final V value) 
    { 
		super(value, null);
		this.edges = new HashMap<AdjacencyMapVertex<V, E>, AdjacencyMapEdge<E, V>>();
	}//end adjacency map vertex
	
     
	//Obtain the edges incident on this vertex.
	Iterable<AdjacencyMapEdge<E, V>> getIncidentOn()
    { 
        return edges.values(); 
    }//end
	
	//Obtain this vertices neighbours (i.e. those vertices to which it is connected by an edge.)
	Iterable<AdjacencyMapVertex<V, E>> getNeighbours() 
    { 
        return edges.keySet(); 
    }//end    
	
	
	//Determine whether this vertex is adjacent to that given. (i.e. that the given vertex is in the adjacency map.) 
	boolean isAdjacentTo(final AdjacencyMapVertex<V, E> other) 
    { 
		boolean ans = false;
        //this are the vertices
        Iterable<AdjacencyMapVertex<V, E>> vertices = this.getNeighbours();
        //String temp : crunchifyList
        for (AdjacencyMapVertex<V, E> vertice : vertices)
        {
            if (vertice.equals(other))
            {
                ans = true;
                //need to break the for loop to not return false at the end if the last ones are actually not equal
                break;
            }//end if 
        }//end for 
		return ans;
	}//end
	
	//Insert the given edge into the adjacency map.
	void insert(final AdjacencyMapEdge<E, V> edge)
    { 
        //need to apply opposite to an edge
        edges.put(edge.getOpposite(this),edge);
	}//end
	
	// Remove the given edge (and associated vertex) from the adjacency
	void remove(final AdjacencyMapEdge<E, V> edge)
    { 
        //vertices of the edge (left and right)
		Pair<AdjacencyMapVertex<V, E>> vertex = edge.getVertices(); 
        
        //get left and right
        AdjacencyMapVertex<V, E> leftV = vertex.getLeft();
        AdjacencyMapVertex<V, E> rightV = vertex.getRight();
        
        //need to remove the edge - this order matters otherwise stack overflow - lol jk
        edges.remove(edge); 
        
        //get incident from left and right
        Iterable<AdjacencyMapEdge<E, V>> leftE = leftV.getIncidentOn();
        Iterable<AdjacencyMapEdge<E, V>> rightE = rightV.getIncidentOn(); 
        


        // Remove left vertex if it has no edges
        if (leftE==null)
        {
            edges.remove(leftV);
        }//end if 

        // Remove right vertex if it has no edges 
        if (rightE==null)
        { 
            edges.remove(rightV);
        }//end if 
        
        
	}//end
	
	//Remove the given vertex (and associated edge) from the adjacency map.
	void remove(final AdjacencyMapVertex<V, E> vertex) 
    {
		//remove the key, no key for it in map therefore cannot reference it
        edges.remove(vertex);
	}//end
	
	//Obtain a String representation of this vertex of the form 'vertex(value)'.
	public String toString() 
    { 
        return "vertex("+getValue()+")"; 
    }//end
	
	//Obtain a dump (for debugging purposes) of the internal representation of this vertex.
	public void dump() 
    {
		System.out.print("\nVertex: value="+getValue()+" edges=[");
		
		Iterator<AdjacencyMapVertex<V, E>> iterator = edges.keySet().iterator();
    
		if (iterator.hasNext()) 
        {
			System.out.print(edges.get(iterator.next()));
			while (iterator.hasNext()) 
            {
				System.out.print(", "+edges.get(iterator.next()));
			}//end while
		}//end  if
		System.out.print(']');
	}//end dump
		
}//end class
