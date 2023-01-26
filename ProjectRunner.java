// Virginia Tech Honor Code Pledge:
//
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions of those who do.
// -- Krishna Nair (krishnanair)
package spacecolonies;

import java.io.FileNotFoundException;
import bsh.ParseException;

/**
 * @author Krishna Nair (krishnanair)
 *
 */
public class ProjectRunner {

    /**
     * new ProjectRunner object
     */
    public ProjectRunner()
    {
        
    }
    
    /**
     * Sets up the environment of the program
     * allowing it to be interacted with
     * @param args
     *          arguments to set up specific instance
     */
    public static void main(String[] args) 
        throws ParseException, FileNotFoundException, SpaceColonyDataException
    {
        if (args.length == 2)
        {
            ColonyReader c1 = new ColonyReader(args[0], args[1]);
        }
        else
        {
            ColonyReader c2 = new ColonyReader("input.txt", "planets.txt");
        }
    }
}
