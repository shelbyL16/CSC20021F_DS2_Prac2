import java.io.*;
import graph.*;
import utils.ClusterBuilder;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Map.*;
import java.util.*;

    //Solver for Lewis Carrol's Doublets game.
    public class Doublets 
    {
 
    	private Map<String, Vertex<String>> wordList;
    	private Graph<String, String> graph;
	
	//Create a Doublets object for solving problems over the words in the given file. @throws FileNotFoundException
	public Doublets(final String wordFile) throws FileNotFoundException 
    {
            
        //need to implememt the variables that have been created        
        graph = new AdjacencyMapGraph<String , String>();
	    wordList = new HashMap<String, Vertex<String>>();
        
        //need to create a file object now
        File f = new File(wordFile);

        //need to create a cluster builder object now as well - need to create a file reader object for the cluster builder
        FileReader file = new FileReader(f);        
        ClusterBuilder cluster = new ClusterBuilder(file);
        
        List<String> listStr;
        
        //need to go thorugh the cluster
        while (cluster.hasNext())
        {
            listStr = cluster.next();
            
            //need to loop through the list of String
            for (String one: listStr)
            {
                //need to create a string vertex
                Vertex<String> v;
               
                //check to see if it doesnt contain it
                if (!wordList.containsKey(listStr))
                {
                    v = graph.insert(one);
                    wordList.put(one, v);
                }//end if 
                else //if it contains it
                {
                    v = wordList.get(one);
                }//end else
               
                for(Vertex<String> adjacent : wordList.values())
                {
                   
                    if (v!= adjacent && !graph.areAdjacent(v, adjacent) && listStr.contains(v.getValue()))
                    {
                        Edge<String> e = graph.insert(v, adjacent,"");
                    }//end if
                }//end for nested
            }//end for loop    
        }//end while
        
        
        
	}//end

	/**
	 * Obtain the solution to the doublet (wordOne, wordTwo). Returns null if one or other of the words 
       is not in the lexicon. Returns an empty list if there is no solution. Otherwise returns a 
       list of words beginning with wordOne and ending in wordTwo, with zero or more intervening words.
	   The list is such that (i) any adjacent pair of words in the list differ by at most one letter, and 
       (ii) it represents the shortest route from wordOne to wordTwo.
	 */
	public List<String> solve(final String wordOne, final String wordTwo) 
    { 
		// Your code here.
		return null;
	}//end
			
	// Your code here.
 }//end class
