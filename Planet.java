// Virginia Tech Honor Code Pledge:
//
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions 
// of those who do.
// -- Krishna Nair (krishnanair)
package spacecolonies;

/**
 * Planet objects where people will move to
 * @author Krishna Nair (krishnanair)
 * @version 11.9.2021
 */
public class Planet implements Comparable<Planet> {

    private String name;
    private Skillset minSkills;
    private Person[] population;
    private int populationSize;
    private int capacity;
    
    /**
     * new Planet object
     * @param planetName
     *          name of the planet
     * @param planetAgri
     *          planets agriculture requirement
     * @param planetMedi
     *          planet's medicine requirement
     * @param planetTech
     *          planet's technology requirement
     * @param planetCap
     *          planet's max capacity
     */
    public Planet(String planetName, int planetAgri, 
        int planetMedi, int planetTech, int planetCap)
    {
        capacity = planetCap;
        name = planetName;
        populationSize = 0;
        minSkills = new Skillset(planetAgri, planetMedi, planetTech);
        population = new Person[capacity];
    }
    
    /**
     * sets the name of the planet
     * @param input
     *          new name of the planet
     */
    public void setName(String input)
    {
        name = input;
    }
    
    /**
     * returns the name of the planet
     * @return the name of the planet
     */
    public String getName()
    {
        return name;
    }
    
    /**
     * returns the skill requirements of the planet
     * @return the skill requirements of the planet
     */
    public Skillset getSkills()
    {
        return minSkills;
    }
    
    /**
     * returns the population of people on the planet
     * @return the population of people on the planet
     */
    public Person[] getPopulation()
    {
        return population;
    }
    
    /**
     * returns the population size
     * @return the population size
     */
    public int getPopulationSize()
    {
        return populationSize;
    }
    
    /**
     * returns the planet's capacity
     * @return the planet's capacity
     */
    public int getCapacity()
    {
        return capacity;
    }
    
    /**
     * returns the amount of slots left on the planet
     * @return the amount of slots left on the planet
     */
    public int getAvailability()
    {
        return (capacity - populationSize);
    }
    
    /**
     * returns true or false if the planet is full or not
     * @return true or false if the planet is full or not
     */
    public boolean isFull()
    {
        return (capacity == populationSize);
    }
    
    /**
     * compares two planets to one another
     * @param other
     *          other planet being compared to
     * @return 1  or -1 based on order
     * 
     */
    public int compareTo(Planet other)
    {
        if (getCapacity() > other.getCapacity())
        {
            return 1;
        }
        else if (getCapacity() < other.getCapacity())
        {
            return -1;
        }
        else
        {
            if (getAvailability() > other.getAvailability())
            {
                return 1;
            }
            else if (getAvailability() < other.getAvailability())
            {
                return -1;
            }
            else
            {
                if (getSkills().compareTo(other.getSkills()) > 0)
                {
                    return 1;
                }
                else if (getSkills().compareTo(other.getSkills()) < 0)
                {
                    return -1;
                }
                else
                {
                    if (getName().compareTo(other.getName()) > 0)
                    {
                        return 1;
                    }
                    else if (getName().compareTo(other.getName()) < 0)
                    {
                        return -1;
                    }
                    else
                    {
                        return 0;
                    }
                }
            }
        }
    }
    
    /**
     * check to see if given person can live on this planet
     * @param newbie
     *          person being checked to see if they're qualified
     * @return true or false if the person can or cannot live on the planet
     */
    public boolean isQualified(Person newbie)
    {
        return newbie.getSkills().getAgriculture()
            >= minSkills.getAgriculture() &&
            newbie.getSkills().getMedicine()
            >= minSkills.getMedicine() &&
            newbie.getSkills().getTechnology()
            >= minSkills.getTechnology();
    }
    
    /**
     * adds the given person to the planet
     * @param newbie
     *          person being added to the planet
     * @return true or false if the person was added or not
     */
    public boolean addPerson(Person newbie)
    {
        if (!isFull() && isQualified(newbie))
        {
            population[populationSize] = newbie;
            populationSize++;
            return true;
        }
        return false;
    }
    
    /**
     * compares two planet objects to check for equality
     * @param obj
     *          object that this is being compared to
     * @return true or false based on equality
     */
    public boolean equals(Object obj)
    {
        if (this == obj)
        {
            return true;
        }
        if (obj == null)
        {
            return false;
        }
        if (this.getClass().equals(obj.getClass()))
        {
            Planet otherPlanet = (Planet) obj;
            if (getName().equals(otherPlanet.getName()) &&
                getSkills().getAgriculture() 
                == otherPlanet.getSkills().getAgriculture() &&
                getSkills().getMedicine() 
                == otherPlanet.getSkills().getMedicine() &&
                getSkills().getTechnology() 
                == otherPlanet.getSkills().getTechnology() &&
                getCapacity() 
                == otherPlanet.getCapacity() &&
                getPopulationSize() == otherPlanet.getPopulationSize())
            {
                return true;
            }
        }
        return false;
    }
    
    /**
     * displays the attributes to the planet as a string
     * @return string for of the planet's attribute requirements
     */
    public String toString()
    {
        StringBuilder string = new StringBuilder();
        string.append(name + ", population " + populationSize + " ");
        string.append("(cap: " + capacity + "), Requires A >= ");
        string.append(minSkills.getAgriculture() + ", M >= ");
        string.append(minSkills.getMedicine() + ", T >= ");
        string.append(minSkills.getTechnology() );
        return string.toString();
    }
}
