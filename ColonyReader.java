// Virginia Tech Honor Code Pledge:
//
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions of those who do.
// -- Krishna Nair (krishnanair)
package spacecolonies;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import bsh.ParseException;

/**
 * @author Krishna Nair (krishnanair)
 *
 */
public class ColonyReader {
    

    private Planet[] planets;
    private ArrayQueue<Person> queue;
    
    /**
     * new ColonyReader object
     * @param applicantFileName
     * @param planetFileName
     * @throws ParseException
     * @throws SpaceColonyDataException
     * @throws FileNotFoundException
     */
    public ColonyReader(String applicantFileName, String planetFileName) 
        throws ParseException, SpaceColonyDataException, FileNotFoundException
    {
        queue = readQueueFile(applicantFileName);
        planets = readPlanetFile(planetFileName);
        ColonyCalculator calculator = new ColonyCalculator(queue, planets);
        SpaceWindow wind = new SpaceWindow(calculator); 
    }
    
    /**
     * Converts the file text to a planet list
     * @param fileName
     * @return the planet list
     * @throws SpaceColonyDataException
     * @throws ParseException
     * @throws FileNotFoundException
     */
    private Planet[] readPlanetFile(String fileName) 
        throws SpaceColonyDataException, ParseException, FileNotFoundException
    {
        Planet[] read = new Planet[ColonyCalculator.NUM_PLANETS];
        Scanner scanner = new Scanner(new File(fileName));
        int i = 0;
        while (scanner.hasNextLine() && i < 3)
        {
            String inp = scanner.nextLine();
            String[] split = inp.split(", *");
            if (split.length != 5)
            {
                scanner.close();
                throw new ParseException();
            }
            int id1 = Integer.valueOf(split[1]);
            int id2 = Integer.valueOf(split[2]);
            int id3 = Integer.valueOf(split[3]);
            int id4 = Integer.valueOf(split[4]);
            if (!isInSkillRange(id1, id2, id3))
            {
                scanner.close();
                throw new SpaceColonyDataException("Skills not in range");
            }
            Planet addition = new Planet(split[0], id1, id2, id3, id4);
            read[i] = addition;
            i++;
        }
        scanner.close();
        if (i < 2)
        {
            throw new SpaceColonyDataException("There are less than 3 planets");
        }
        return read;
    }
    
    /**
     * Converts queue file and converts it to an ArrayQueue<Person>
     * @param fileName
     * @return the arrayqueue
     * @throws SpaceColonyDataException
     * @throws ParseException
     * @throws FileNotFoundException
     */
    private ArrayQueue<Person> readQueueFile(String fileName) 
        throws SpaceColonyDataException, ParseException, FileNotFoundException
    {
        ArrayQueue<Person> read = 
            new ArrayQueue<Person>(ArrayQueue.DEFAULT_CAPACITY);
        Scanner scanner = new Scanner(new File(fileName));
        while (scanner.hasNextLine())
        {
            String inp = scanner.nextLine();
            String[] split = inp.split(", *");
            if (split.length < 4)
            {
                scanner.close();
                throw new ParseException();
            }
            String theName = "";
            if (split.length == 5)
            {
                theName = split[4];
            }
            
            int id1 = Integer.valueOf(split[1]);
            int id2 = Integer.valueOf(split[2]);
            int id3 = Integer.valueOf(split[3]);
            if (!isInSkillRange(id1, id2, id3))
            {
                scanner.close();
                throw new SpaceColonyDataException("Skills not in range");
            }
            Person addition = new Person(split[0], id1, id2, id3, theName);
            read.enqueue(addition);
        }
        scanner.close();
        return read;
    }
    
    /**
     * checks to see if the skill set is in range
     * @param num1
     * @param num2
     * @param num3
     * @return true or false if the skill is in the range
     */
    private boolean isInSkillRange(int num1, int num2, int num3)
    {
        if ((num1 >= ColonyCalculator.MIN_SKILL_LEVEL && num1 <= ColonyCalculator.MAX_SKILL_LEVEL) &&
            (num2 >= ColonyCalculator.MIN_SKILL_LEVEL && num2 <= ColonyCalculator.MAX_SKILL_LEVEL) &&
            (num3 >= ColonyCalculator.MIN_SKILL_LEVEL && num3 <= ColonyCalculator.MAX_SKILL_LEVEL))
        {
            return true;
        }
        return false;
    }
}
