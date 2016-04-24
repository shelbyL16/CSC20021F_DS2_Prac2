import java.io.FileReader;
import graph.Vertex;
import graph.Graph;
import utils.ClusterBuilder;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.*;

public class DoubletSolver
{
   public static void main(final String[] args) throws Exception 
   {
        //strings
        String instructions = "Enter a doublet (two words separated by a comma), or 'quit': ";
        String quit = "quit";
        String sorry = "Sorry, insufficient data.";
        String input = "";
        String[] in;
        String word1;
        String word2;

        //create a new collection
        List<String> ans = Collections.emptyList();

        //get the file name
        final String name= args[0];
        //create a doublet object with the file name given
        Doublets doublet = new Doublets(name);
        //create a scanner
        Scanner scan = new Scanner(System.in);
       
        System.out.print(instructions);
        input = scan.nextLine();
        
        while (!(input.equals(quit)))
        {
            input = input.replaceAll("\\s+","");
            in = input.split(",");
            word1 = in[0];
            word2 = in[1];
 
            List<String> l = doublet.solve(word1,word2);
            if (l != null)
            {
                for (int i = 0; i<l.size();i++)
                {
                    System.out.println(l.get(i).toUpperCase());
                }//end for
            }//end else

            if (l == null)
            {
                System.out.println(sorry);
            }//end else
            
            System.out.println(instructions);
            input = scan.nextLine();
        }//end while 
    }//end main
}//end class
