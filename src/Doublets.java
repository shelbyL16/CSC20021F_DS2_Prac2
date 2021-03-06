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
        
        //initializing
        List<String> listStr;
       
        //need to go thorugh the cluster
        while (cluster.hasNext())
        {
            Vertex<String> v;
            Edge<String> e;
            listStr = cluster.next();
            
            //need to loop through the list of String
            for (String one: listStr)
            {
                //check to see if it doesnt contain it
                if (!wordList.containsKey(one))
                {
                    //need to put it in once
                    v = graph.insert(one);
                    wordList.put(one, v);
                }//end if 
                else //if it contains it
                {
                    v = wordList.get(one);
                }//end else
               
            for (String word: listStr)
            {
                for(String adjacent : listStr)
                {
                   if(wordList.containsKey(adjacent))
                   {
                        if (!one.equals(adjacent) && !graph.areAdjacent(wordList.get(one), wordList.get(adjacent)))
                        {
                             graph.insert(wordList.get(one), wordList.get(adjacent),"");
                        }//end inner if
                   }//end if outside
                }//end for nested
            }//end for loop 
            }//end   
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
        //create the vertices from the words
        Vertex<String> vTwo = wordList.get(wordTwo);
        Vertex<String> vOne = wordList.get(wordOne);
        List<String> path = new ArrayList<String>();
        
        //if it contains either the one or the other
        if ((wordList.containsKey(wordOne)) && (wordList.containsKey(wordOne))) 
        {
            List<Vertex<String>> ans = this.shortestPath(vOne, vTwo);
        
            if (ans != null) {
    
                for (Vertex<String> v : ans)
                {
                    path.add(v.getValue());
                }//end for loop

            return path;
            }
        }//end if
        
        return null;
  	}//end

    public  List<Vertex<String>> shortestPath(final Vertex<String> vOne, final Vertex<String> vTwo) {
			
	    //calculating shortest path given method by stephan and changed it abit
		final List<List<Vertex<String>>> paths = new ArrayList<List<Vertex<String>>>();
		final List<Vertex<String>> initialPath = new ArrayList<Vertex<String>>();
        
        //mark the first vertex and add to the initial path
		vOne.mark();
		initialPath.add(vOne);
        //add initial path the paths	
        paths.add(initialPath);	
        //create a path and a new ArrayList
		List<Vertex<String>> path;
        List<Vertex<String>> n = new ArrayList<Vertex<String>>();

        //while loop will always be true
		while (true) 
        {
            if (paths.size() == 0)
            {
                
                return null;
            }//end 

            //if not zero then do all of this
			path = paths.remove(0);
            //get the end
			final Vertex<String> end = path.get(path.size()-1);//found to the last vertex
			if (end==vTwo) 
            {
				break;
			}//end if
			else //if not
            {
                //get all the neighbours
				for(Vertex<String> neighbour : graph.getNeighbours(end)) 
                {
					final List<Vertex<String>> newPath = new ArrayList<Vertex<String>>(path);
					if (!neighbour.isMarked()) 
                    {
						neighbour.mark();
						newPath.add(neighbour);
						paths.add(newPath);
					}//end if
				}//end for
			}//end else
		}//end while
        //clear marks at the end
		graph.clearMarks();
		if (path.size()==1) 
        {
			return null;
		}//end if
		else 
        {
			return path;
		}//end else
	}	
 }//end class
