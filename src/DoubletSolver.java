import java.io.FileReader;
import graph.Vertex;
import graph.Graph;
import utils.ClusterBuilder;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Map;

public class DoubletSolver
{
    public static void main (String[]args)
    {
        //declaring the variables        
        Scanner scanner = new Scanner();
        //accepting the name of the file
        String nFile = args[0];
        Doublets btdubs = new Doublets(nFile);
        String quit = "quit";
        
        //printing the instructions
        System.out.println("Enter a doublet (two words separated by a comma), or 'quit':");
        while (!(scanner.next()).equalsIgnoreCase(quit))
        {
            System.out.println();
            String[] words = scanner.split(',')'
            
            String one = words[0];
            String two = words[1];
            btdubs.solve(one,two);
            System.out.println("Enter a doublet (two words separated by a comma), or 'quit':");
            if((scanner.next()).equalsIgnoreCase(quit))
            {
                break;
            }//end
        }//end while
    }//end main method 
    	
}//end class
