import java.util.*;

public class Advisee extends DataElement
{

	protected int number;
	protected String name;
	protected LinkedList<String> adviseePrefers;
	protected String topic;

		//default constructor
	public Advisee()
    {
        number = 0;
        name = "";
        adviseePrefers = new LinkedList<String>();
    }

		//constructor with parameters
    public Advisee(int x, String n, LinkedList<String> pref)
    {
        number = x;
        name = n;
        adviseePrefers = pref;
    }

		//copy constructor
    public Advisee(Advisee otherElement)
    {
        number = otherElement.number;
        name = otherElement.name;
        adviseePrefers = otherElement.adviseePrefers;
    }

		//Method to set the value of the instance variable number
 		//Postcondition: number = x;
    public void setNumber(int x)
    {
        number = x;
    }

    	//Method to return the value of the instance variable number
		//Postcondition: The value of number is returned
	public int getNumber()
	{
	    return number;
	}

		//Method to set the value of the instance variable name
 		//Postcondition: name = s;
    public void setName(String s)
    {
        name = s;
    }

    public void setTopic(String t)
    {
    	topic = t;
    }

    public String getTopic(String t)
    {
    	return t;
    }

    	//Method to return the value of the instance variable name
		//Postcondition: The value of name is returned
	public String getName()
	{
	    return name;
	}

		//Method to set the value of the instance variable adviseePrefers
		 //Postcondition: adviseePrefers = pref;
    public void addPreferences(LinkedList pref)
    {
        adviseePrefers = pref;
    }

    	//Method to return the value of the instance variable adviseePrefers
		//Postcondition: The value of adviseePrefers is returned
	public LinkedList getPreferences()
	{
	    return adviseePrefers;
	}

    public boolean equals(DataElement otherElement)
    {
        Advisee temp = (Advisee) otherElement;

        return (name.equals(temp.name));
    }

    public int compareTo(DataElement otherElement)
    {
        Advisee temp = (Advisee) otherElement;

        return (name.compareTo(temp.name));
    }

    public void makeCopy(DataElement otherElement)
    {
          Advisee temp = (Advisee) otherElement;

          number = temp.number;
          name = temp.name;
          adviseePrefers = temp.adviseePrefers;
    }

    public DataElement getCopy()
    {
        Advisee temp = new Advisee(number, name,adviseePrefers);
        return temp;
    }

	public void displayPreferences() {
		System.out.println(adviseePrefers);
	}

    public String toString()
    {
        return String.valueOf(number) + " " + name;
    }

}
