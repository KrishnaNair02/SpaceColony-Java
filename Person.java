// Virginia Tech Honor Code Pledge:
//
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions 
// of those who do.
// -- Krishna Nair (krishnanair)
package spacecolonies;

/**
 * Object that holds attributes of a person
 * @author Krishna Nair (krishnanair)
 * @version 11.1.2021
 */
public class Person 
{
    private String name;
    private Skillset skills;
    private String planetPreference;
    
    /**
     * new Person object
     * @param name
     *          name of person
     * @param agri
     *          person's agriculture skill level
     * @param medi
     *          person's medicine skill level
     * @param tech
     *          person's technology skill level
     * @param planet
     *          person's planet preference
     */
    public Person(String name, int agri, int medi, int tech, String planet)
    {
        this.name = name;
        skills = new Skillset(agri, medi, tech);
        planetPreference = planet;
    }
    
    /**
     * returns the planet preference
     * @return the planet preference
     */
    public String getPlanetPreference()
    {
        return planetPreference;
    }
    
    /**
     * tests to see if other person objects are equal
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
        if  (obj == null)
        {
            return false;
        }
        if (this.getClass().equals(obj.getClass()))
        {
            Person otherPerson = (Person) obj;
            if (this.getName().equals(otherPerson.getName()) &&
                this.getSkills().equals(otherPerson.getSkills()) &&
                this.getPlanetPreference().equals(
                    otherPerson.getPlanetPreference()))
            {
                return true;
            }
        }
        return false;
    }
    
    /**
     * converts the person's attributes to a string
     * @return the person's attributes as a string
     */
    public String toString()
    {
        if (planetPreference.length() == 0)
        {
            StringBuilder string = new StringBuilder();
            string.append("No-Planet ");
            string.append(name + " ");
            string.append(skills.toString());
            return string.toString();
        }
        StringBuilder string = new StringBuilder();
        string.append(name + " ");
        string.append(skills.toString());
        string.append(" Wants: ");
        string.append(planetPreference);
        return string.toString();
    }
    
    /**
     * returns the person's skillset
     * @return the person's skillset
     */
    public Skillset getSkills()
    {
        return skills;
    }
    
    /**
     * returns the person's name
     * @return the person's name
     */
    public String getName()
    {
        return name;
    }
}
