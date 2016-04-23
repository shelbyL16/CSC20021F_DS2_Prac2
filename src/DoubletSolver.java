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
        String in;
        String in2;

        //create a new collection
        List<String> ans = Collections.emptyList();

        //get the file name
        final String name= args[0];
        //create a doublet object with the file name given
        Doublets doublet = new Doublets(name);
        //create a scanner
        Scanner scan = new Scanner(System.in);
       
        System.out.print(instructions);
        while (true)
        {
            in = (scan.next()).replaceAll(",", "");

            //need to break the while
            if (in.equals(quit))
            {
                break;
            }//end if
           
            else
            {
               in2 = scan.next();
                
                if (ans!=Collections.EMPTY_LIST)
                {
                    if (ans!=null && ans.isEmpty()==false)
                    {
                        for (String a: ans)
                        {
                            System.out.println(a);
                        }//end for
                    }//end if inner
                    else
                    {
                        System.out.println(sorry);
                    }//end else
                }//end if outer
                else 
                {
                    System.out.println(sorry);
                }//end else sorry
            }//end big else
           
            System.out.print(instructions);
        }//end while 
    }//end main
}//end class
