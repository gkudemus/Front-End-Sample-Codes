import java.util.*;

public class Adviser extends DataElement
{
	protected int number;
	protected String name;
	protected LinkedList<String> adviserPrefers;
	protected int capacity;
	protected LinkedList<String> topics;

		//default constructor
	public Adviser()
    {
        number = 0;
        name = "";
        capacity = 0;
        adviserPrefers = new LinkedList<String>();
        topics = new LinkedList<String>();
    }

		//constructor with parameters
    public Adviser(int x, String n, int c, LinkedList<String> pref)
    {
        number = x;
        name = n;
        capacity = c;
        adviserPrefers = pref;
    }

		//copy constructor
    public Adviser(Adviser otherElement)
    {
        number = otherElement.number;
        name = otherElement.name;
        capacity = otherElement.capacity;
        adviserPrefers = otherElement.adviserPrefers;
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

    	//Method to return the value of the instance variable name
		//Postcondition: The value of name is returned
	public String getName()
	{
	    return name;
	}


		//Method to set the value of the instance variable capacity
		 //Postcondition: capacity = c;
    public void setCapacity(int c)
    {
        capacity = c;
    }


		//Method to decrement the value of the instance variable capacity
		 //Postcondition: capacity = capacity -1;
    public void decrementCapacity()
    {
        capacity--;
    }

    	//Method to return the value of the instance variable capacity
		//Postcondition: The value of number is returned
	public int getCapacity()
	{
	    return capacity;
	}

		//Method to set the value of the instance variable adviserPrefers
		 //Postcondition: adviserPrefers = pref;
    public void addPreferences(LinkedList pref)
    {
        adviserPrefers = pref;
    }

    public void addTopicPref(LinkedList topic)
    {
    	topics = topic;
    }

    public LinkedList getTopics()
    {
    	return topics;
    }

    	//Method to return the value of the instance variable adviserPrefers
		//Postcondition: The value of adviserPrefers is returned
	public LinkedList getPreferences()
	{
	    return adviserPrefers;
	}

    public boolean equals(DataElement otherElement)
    {
        Adviser temp = (Adviser) otherElement;

        return (name.equals(temp.name));
    }

    public int compareTo(DataElement otherElement)
    {
        Adviser temp = (Adviser) otherElement;

        return (name.compareTo(temp.name));
    }

    public void makeCopy(DataElement otherElement)
    {
          Adviser temp = (Adviser) otherElement;

          number = temp.number;
          name = temp.name;
          adviserPrefers = temp.adviserPrefers;
    }

    public DataElement getCopy()
    {
        Adviser temp = new Adviser(number, name,capacity, adviserPrefers);
        return temp;
    }

	public void displayPreferences() {
		System.out.println(adviserPrefers);
	}

    public String toString()
    {
        return String.valueOf(number) + "-" + capacity + " " + name;
    }

}
