// Virginia Tech Honor Code Pledge:
//
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions 
// of those who do.
// -- Krishna Nair (krishnanair)
package spacecolonies;
import list.AList;
import java.util.Arrays;

/**
 * Performs major calculations for program
 * @author Krishna Nair (krishnanair)
 * @version 11.9.2021
 */
public class ColonyCalculator {

    /**
     * Max number of planets
     */
    public static final int NUM_PLANETS = 3;
    /**
     * the minimum skill level requirement
     */
    public static final int MIN_SKILL_LEVEL = 1;
    /**
     * the maximum skill level requirement
     */
    public static final int MAX_SKILL_LEVEL = 5;
    private ArrayQueue<Person> applicantQueue;
    private AList<Person> rejectBus;
    private Planet[] planets;
    
    /**
     * new ColonyCalculator object
     * @param que
     *          the queue of people 
     * @param planetList
     *          the list of planets
     */
    public ColonyCalculator(ArrayQueue<Person> que, Planet[] planetList)
    {
        if (que == null)
        {
            throw new IllegalArgumentException();
        }
        applicantQueue = que;
        planets = planetList;
        rejectBus = new AList<Person>();
    }
    
    /**
     * returns the queue of applicants
     * @return the queue of applicants
     */
    public ArrayQueue<Person> getQueue()
    {
        return applicantQueue;
    }
    
    /**
     * returns the list of planets
     * @return the list of planets
     */
    public Planet[] getPlanets()
    {
        return planets;
    }
    
    /**
     * finds a suitable planet for the given person
     * @param nextPerson
     *          person to find planet for
     * @return suitable planet for the given person
     */
    public Planet getPlanetForPerson(Person nextPerson)
    {
        if (nextPerson == null)
        {
            return null;
        }
        Planet foundPlanet = null;
        String pref = nextPerson.getPlanetPreference();
        int index = getPlanetIndex(pref);
        if (index >= 0)
        {
            foundPlanet = planets[index];
            if (canAccept(foundPlanet, nextPerson))
            {
                return foundPlanet;
            }
            return null;
        }
        else
        {
            return getHighestCapacityPlanet(nextPerson);
        }
    }
    
    /**
     * finds the highest capacity planet 
     * @param person 
     *          person to find planet for
     * @return highest capacity planet
     */
    private Planet getHighestCapacityPlanet(Person person)
    {
        Planet[] sorted = Arrays.copyOf(planets, planets.length);
        Arrays.sort(sorted);
        for (int i = sorted.length - 1; i >= 0; i--)
        {
            if (canAccept(sorted[i], person))
            {
                return sorted[i];
            }
        }
        return null;
    }
    
    /**
     * checks to see if a person is qualified for the planet, and that
     * the planet has slots available
     * @param planet
     *          planet the person is applying for
     * @param person
     *          the person that is applying for a planet
     * @return true or false if the person can live on the planet
     */
    private boolean canAccept(Planet planet, Person person)
    {
        return (!planet.isFull() && planet.isQualified(person));
    }
    
    /**
     * gets the index of the location of the planet in the list
     * @param planet
     *          the planet that is being searched for in the list
     * @return the index of the location of the planet in the list
     */
    public int getPlanetIndex(String planet)
    {
        int ind = -1;
        for (int i = 0; i < planets.length; i++)
        {
            if (planet.equals(planets[i].getName()))
            {
                ind = i;
            }
        }
        return ind;
    }
    
    /**
     * adds the next person to the planet and removes 
     * them from the applicant queue
     * @return true or false if it was accepted or not
     */
    public boolean accept()
    {
        if (!applicantQueue.isEmpty())
        {
            Person applicant = applicantQueue.getFront();
            Planet pref = getPlanetForPerson(applicant);
            if (pref != null)
            {
                pref.addPerson(applicant);
                applicantQueue.dequeue();
                return true;
            }
        }
        return false;
    }
    
    /**
     * removes the next  person from the applicant queue 
     * and puts them on the reject bus
     */
    public void reject()
    {
        Person theReject = applicantQueue.dequeue();
        rejectBus.add(theReject);
    }
}
