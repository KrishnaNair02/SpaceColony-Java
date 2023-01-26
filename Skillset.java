// Virginia Tech Honor Code Pledge:
//
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions 
// of those who do.
// -- Krishna Nair (krishnanair)
package spacecolonies;

/**
 * Class that holds a set of skills
 * @author Krishna Nair (krishnanair)
 * @version 11.7.2021
 */
public class Skillset implements Comparable<Skillset> {

    private int agriculture;
    private int medicine;
    private int technology;
    
    /**
     * new Skillset object
     * @param ag
     *          agriculture skill level
     * @param med
     *          medicine skill level
     * @param tech
     *          technology skill level
     */
    public Skillset(int ag, int med, int tech)
    {
        agriculture = ag;
        medicine = med;
        technology = tech;
    }
    
    /**
     * checks to see if all of this's skills are less than or
     * equal to other's skills
     * @param other
     *          skillset that this is being compared to
     * @return true or false 
     */
    public boolean isLessThanOrEqualTo(Skillset other)
    {
        return this.agriculture <= other.agriculture && this.medicine
            <= other.medicine && this.technology <= other.technology;
    }
    
    /**
     * compares to skillsets to check for equality
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
            Skillset otherSkills = (Skillset) obj;
            if (this.agriculture == otherSkills.agriculture &&
                this.technology == otherSkills.technology &&
                this.medicine == otherSkills.medicine)
            {
                return true;
            }
        }
        return false;
    }
    
    /**
     * compares two skillsets
     * @param skills
     *          the skillset that this is being compared to
     * @return 1 or -1 if this skillset is higher 
     * or lower than the other
     * 
     */
    public int compareTo(Skillset skills)
    {
        int thisSkillSum = this.agriculture + this.medicine +
            this.technology;
        int otherSkillSum = skills.agriculture + 
            skills.technology + skills.medicine;
        if (thisSkillSum < otherSkillSum)
        {
            return -1;
        }
        else if (thisSkillSum == otherSkillSum)
        {
            return 0;
        }
        else
        {
            return 1;
        }
    }
    
    /**
     * returns the agriculture skill level
     * @return the agriculture skill level
     */
    public int getAgriculture()
    {
        return agriculture;
    }
    
    /**
     * returns the medicine skill level
     * @return the medicine skill level
     */
    public int getMedicine()
    {
        return medicine;
    }
    
    /**
     * returns the technology skill level
     * @return the technology skill level
     */
    public int getTechnology()
    {
        return technology;
    }
    
    /**
     * converts the skillset to a string
     * @return the skillset as a string
     */
    public String toString()
    {
        StringBuilder string = new StringBuilder();
        string.append("A: ");
        string.append(getAgriculture());
        string.append(", M: ");
        string.append(getMedicine());
        string.append(", T: ");
        string.append(getTechnology());
        return string.toString();
    }
}
