import java.util.*;
import java.io.*;

public class ModifiedGaleShapley {

	static LinkedList<Advisee> adviseeList = new LinkedList<Advisee>();
	static LinkedList<Adviser> adviserList = new LinkedList<Adviser>();

	static LinkedList<String> advisees = new LinkedList<String>();
	static LinkedList<String> advisers = new LinkedList<String>();

	static final int ADVISERS=5;
	static final int ADVISEES=8;
	static final int TOPICS=10;

	public static void main(String[] args) throws IOException {

		HashMap<String, List<String>> adviseePrefs= new HashMap<String, List<String>>();
		HashMap<String, List<String>> adviserPrefs= new HashMap<String, List<String>>();

		adviserPrefs = loadAdvisers();
		adviseePrefs = loadAdvisees();

		System.out.println(advisers);
		System.out.println(adviserPrefs);

		System.out.println(advisees);
		System.out.println(adviseePrefs);

		System.out.println(adviseeList);
		System.out.println(adviserList);


		Map<String, LinkedList<String>> matches = matching(advisees, adviseePrefs, adviserPrefs);

		for(Map.Entry<String, LinkedList<String>> assignment:matches.entrySet()){
            System.out.println(
                    assignment.getKey() + " is assigned to " + assignment.getValue());
        }
        if(checkMatches(advisees, advisers, matches, adviseePrefs, adviserPrefs)){
            System.out.println("Advisee-Adviser assignments are stable");
        }else{
            System.out.println("Advisee-Adviser assignments are unstable");
        }


        //even if we switch assignments, still stable
        LinkedList <String> tmp = matches.get(advisers.get(0));
        matches.put(advisers.get(0), matches.get(advisers.get(1)));
        matches.put(advisers.get(1), tmp);
        System.out.println(
                advisers.get(0) +" and " + advisers.get(1) + " have switched assignments");
        if(checkMatches(advisees, advisers, matches, adviseePrefs, adviserPrefs)){
             System.out.println("Advisee-Adviser assignments are stable");
        } else {
            System.out.println("Advisee-Adviser assignments are unstable");
        }

        System.out.println();

	}  //main


	private static Map<String, LinkedList<String>> matching( LinkedList<String> students,
            Map<String, List<String>> adviseePrefs,  Map<String, List<String>> adviserPrefs){

        //allows more than one advisees
        Map<String, LinkedList<String>> assignedTo = new TreeMap<String,LinkedList<String>>();
        LinkedList<String> freeAdvisees = new LinkedList<String>();


        freeAdvisees.addAll(students);
        while(!freeAdvisees.isEmpty()){
            String thisAdvisee = freeAdvisees.remove(0);

            List<String> thisAdviseePrefers = adviseePrefs.get(thisAdvisee);
            for(String adviser:thisAdviseePrefers){
                int pos = search(advisers,adviser);
				if(!assignedTo.keySet().containsAll(advisers)){
					if(assignedTo.get(adviser) == null){
                	 LinkedList<String> assigned = new LinkedList<String>();
                	assigned.add(thisAdvisee);
                	assignedTo.put(adviser,assigned);
                    if (pos>=0)
                    	updateCapacity(adviserList,pos);
                    break;
                	}
				}

                //check capacity first
                else{
                	 if (checkCapacity(adviserList, pos)) {
                		LinkedList<String> assigned = assignedTo.get(adviser);
                		assigned.add(thisAdvisee);
                		assignedTo.put(adviser, assigned);
                		updateCapacity(adviserList,pos);
                		break;
                	}
                	else {
                	    LinkedList<String> assigned = assignedTo.get(adviser);
                    	List<String> thisAdviserPrefers = adviserPrefs.get(adviser);
                    	int leastPreferred=0;
                    	String otherAdvisee;
                    	for (int i=1; i<assigned.size(); i++)  {

                    		//find best preference
                    		otherAdvisee = (String) assigned.get(i);
                    		if(thisAdviserPrefers.indexOf(assigned.get(leastPreferred)) >
                            	thisAdviserPrefers.indexOf(otherAdvisee))
                            	leastPreferred=i;
   						}

                            //this adviser prefers this advisee to the advisee he/she is assigned
                           	otherAdvisee = (String) assigned.get(leastPreferred);
                			if(thisAdviserPrefers.indexOf(otherAdvisee) >
                            	thisAdviserPrefers.indexOf(thisAdvisee))   	{
                            	assigned.set(leastPreferred, thisAdvisee);
                        		assignedTo.put(adviser, assigned);
                        		updateCapacity(adviserList,pos);
                        		freeAdvisees.add(otherAdvisee);
                            	break;
                            }

                    }//else no change...keep looking for this advisee

                }

           } //for
        } //while

        return assignedTo;

    } //matching

	private static boolean checkMatches(LinkedList<String> advisees, LinkedList<String> advisers,
        Map<String, LinkedList<String>>  matches, Map<String, List<String>> adviseePrefs,
            Map<String, List<String>> adviserPrefs) {

         //are all advisers assigned?
            if(!matches.keySet().containsAll(advisers))
              return false;

          //are all advisees assigned?

        	LinkedList allAssigned = new LinkedList();
        	Set set = matches.entrySet();
    		Iterator iter = set.iterator();
		    while(iter.hasNext()){
      				Map.Entry me = (Map.Entry)iter.next();
      				LinkedList a = (LinkedList) me.getValue();
      				for(int i=0; i<a.size();i++) {
      					String val = (String) a.get(i);
      					allAssigned.add(val);
      				}
		    }

        	if (!advisees.containsAll(allAssigned))
        		return false;


        Map<LinkedList<String>,String> invertedMatches = new HashMap <LinkedList<String>, String>();
        for(Map.Entry<String, LinkedList<String>> assignment:matches.entrySet()){
             invertedMatches.put(assignment.getValue(), assignment.getKey());
        }

        for(Map.Entry<String, LinkedList<String>> assignment:matches.entrySet()){
            String teacher = assignment.getKey();
            LinkedList<String> teacherPrefers = (LinkedList) adviserPrefs.get(teacher);

            LinkedList<String> teacherLikesBetter= new LinkedList<String>();
            for (int i=0; i<teacherPrefers.size(); i++)  {
                 String otherAdvisee = (String) teacherPrefers.get(i);
                 int ix = teacherPrefers.indexOf(otherAdvisee);
            	 for(int j = 0; j<ix; j++)
            	 	if (!teacherLikesBetter.contains(otherAdvisee)
            	 		&& !teacherLikesBetter.contains(teacherPrefers.get(j) ) )
            	 		{
                			teacherLikesBetter.add(teacherPrefers.get(j));
                		//	System.out.println("otherAdvisee: "+otherAdvisee+" teacherPrefers.get(j): "+teacherPrefers.get(j));
            	 		}
						//System.out.println("end of first for");

        	}

       	    LinkedList <String> students = (LinkedList) assignment.getValue();
            List<String> studentLikesBetter = new LinkedList<String>();
            for (int x=0; x<students.size(); x++) {
                List<String> studentPrefers = adviseePrefs.get(students.get(x));
                for (int i=0; i<studentPrefers.size(); i++)  {
                 	String otherAdviser = (String) studentPrefers.get(i);
                 	int ix = studentPrefers.indexOf(otherAdviser);
            	 	for(int j = 0; j<ix; j++)
            	 		if (!studentLikesBetter.contains(otherAdviser)
            	 			&& !studentLikesBetter.contains(studentPrefers.get(j) ) )
            	 			{
                				studentLikesBetter.add(studentPrefers.get(j));
                			//	System.out.println("otherAdviser: "+otherAdviser+" studentPrefers.get(j): "+studentPrefers.get(j));
            	 			}

                }
                //System.out.println("End of 2nd for");
        	}



        	for(String advisee : teacherLikesBetter){
                String adviseesTentative = invertedMatches.get(advisee);
                List<String> thisStudentPrefers = adviseePrefs.get(advisee);

                if(thisStudentPrefers.indexOf(adviseesTentative) >
                        thisStudentPrefers.indexOf(assignment.getKey())){
                    System.out.printf("%s likes %s better than %s and %s"
                            + " likes %s better than their current assignment\n",
                       assignment.getKey(), advisee, assignment.getValue(),
                       advisee, assignment.getKey());
                    return false;
                }
                //System.out.println("Advisee: "+advisee+" adviseesTentative: "+adviseesTentative);
            }



            for(String adviser : studentLikesBetter){
                List<String>advisersTentative = matches.get(teacher);
                List<String> thisTeacherPrefers = adviserPrefs.get(adviser);
                if(thisTeacherPrefers.indexOf(advisersTentative) >
                        thisTeacherPrefers.indexOf(assignment.getValue())){
                    System.out.printf("%s likes %s better than %s and %s"
                            + " likes %s better than his/her current assignment\n",
                       assignment.getValue(), adviser, assignment.getKey(),
                       adviser, assignment.getValue());
                    return false;
                }
                /*for(String AT : advisersTentative)
                {
                	System.out.println("adviser: "+adviser+" Adviserstentative: "+AT);
                }*/
            }


        }
        return true;
    } // checkMatches


	public static HashMap loadAdvisees () throws IOException  {

			BufferedReader students = new BufferedReader( new FileReader("Advisees.in"));
			HashMap<String, List<String>> prefers= new HashMap<String, List<String>>();
			String input="";
			int count=0;

			while (students.ready() ) {
				input = students.readLine();
				Advisee group = new Advisee();
				StringTokenizer tokens = new StringTokenizer(input);
				LinkedList<String> pref = new LinkedList<String>();
				count++;
				String groupName = tokens.nextToken();
				String field = tokens.nextToken();
				group.setName(groupName);
				group.setTopic(field);
				group.setNumber(count);

				/*for(int i=0; i<ADVISERS; i++) {
					String s = tokens.nextToken();
					pref.add(s);
				}*/
				int c = 0;
				while(tokens.hasMoreTokens())
				{
					c+=1;
					String s = tokens.nextToken();
					pref.add(s);
				}
				int index=0;
				for(;c<TOPICS;c++)
				{
					index=0;
					while(index<TOPICS)
					{
						for(Adviser adv : adviserList)
						{
							LinkedList<String> topicField = adv.getTopics();
							if(topicField.indexOf(field) == index && !(pref.contains(adv.getName())))
							{
								pref.add(adv.getName());
								//System.out.println("yey!");
							}

						}
						index+=1;
					}
				}

				group.addPreferences(pref);
				prefers.put(group.getName(),pref);
				adviseeList.add(group);
				advisees.add(group.getName());
			}

		return prefers;
	} //loadAdvisees

	public static HashMap loadAdvisers () throws IOException  {

			BufferedReader teachers = new BufferedReader( new FileReader("Advisers.in"));
			HashMap<String, List<String>> prefers= new HashMap<String, List<String>>();

			String input="";
			int count=0;


			while (teachers.ready() ) {
				input = teachers.readLine();
				Adviser teacher = new Adviser();
				StringTokenizer tokens = new StringTokenizer(input);
				LinkedList<String> pref = new LinkedList<String>();
				LinkedList<String> topics = new LinkedList<String>();
				count++;
				String teacherName = tokens.nextToken();
				int capacity = Integer.parseInt(tokens.nextToken());
				teacher.setName(teacherName);
				teacher.setNumber(count);
				teacher.setCapacity(capacity);

				for(int i=0; i<ADVISEES; i++) {
					String s = tokens.nextToken();
					pref.add(s);
				}

				for(int i=0;i<TOPICS;i++)
				{
					String s=tokens.nextToken();
					topics.add(s);
				}


				teacher.addPreferences(pref);
				teacher.addTopicPref(topics);
				prefers.put(teacher.getName(),pref);
				adviserList.add(teacher);
				advisers.add(teacher.getName());

		}

		return prefers;
	} //loadAdvisers

	private static int search(LinkedList list, String name) {
		for (int i=0; i<list.size(); i++) {
			String n = (String) list.get(i);
			if (n.equals(name))
				return i;
		}

		return -1;
	}


	private static void updateCapacity(LinkedList list, int i) {

		Adviser adv = (Adviser) list.get(i);
		adv.decrementCapacity();

	}

	private static boolean checkCapacity(LinkedList list, int i) {

		Adviser adv = (Adviser) list.get(i);
		if (adv.getCapacity() > 0)
			return true;
		return false;
	}


}  //ModifiedGaleShapley