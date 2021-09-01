import com.db4o.Db4o;
import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.query.Predicate;
import com.db4o.query.Query;
import java.io.IOException;
import java.io.BufferedReader;
import java.util.List;
import java.util.ArrayList;

public class Database
{
    /**This function allows us to access or create a db4o database file or create a new one.*/
    public void open()
	{
        this.database = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(), fileName);
	}

    /**
     * This function will close the database file and release all the resources associated with it
     */
	public void close()
	{
        this.database.close();
	}
    
    /**
     * This function adds a degree program object to the database file
     * @param reader 
     * @throws IOException
     */
    public void addDegreeProgram(BufferedReader reader) throws IOException
    {
        System.out.print("Enter a degree program to add (Type Q to return to menu): ");
        String degreeProgram = reader.readLine();
        
        if( degreeProgram.toLowerCase().trim().equals("q")) return;
        
        System.out.print("Enter the degree department: ");
        String department = reader.readLine();

        System.out.print("Enter the degree faculty: ");
        String faculty = reader.readLine();
        
        DegreeProgram add = new DegreeProgram();
        add.setDegreeName(degreeProgram);
        add.setDepartment(department);
        add.setFaculty(faculty);

        this.database.store(add);
        System.out.println("Successfully add Degree "+add.getDegreeName()+" to the database.");

    }
    
    /**
     * This function adds an undergraduate object to the database file
     * @param reader 
     * @throws IOException
     */
    public void addUndergraduate(BufferedReader reader) throws IOException
    {
        System.out.print("Adding an undergraduate student. Wish to proceed? Y/n: ");
        String ans = reader.readLine();
        
        if( ans.toLowerCase().trim().equals("n")) return;
        
        System.out.print("Enter the student's first name: ");
        String firstName = reader.readLine();
        
        System.out.print("Enter the student's surname: ");
        String surname = reader.readLine();

        System.out.print("Enter the student's student number: ");
        String studentNumber = reader.readLine();
        
        System.out.print("Enter the student's degree program: ");
        String degreeProgram = reader.readLine();
        /** 
         * Search for degree program in DB if not found display 
         * message showing that it does not exist and show a list of 
         * available degree programs. Do this in a loop until the user 
         * enters a valid degree program or they decide to quit 
         */

        DegreeProgram search = new DegreeProgram();
        ObjectSet result = this.database.queryByExample(search);
        boolean isValid = false;
        ArrayList<String> degrees = new ArrayList<String>();
        do
        {
            for (Object i: result)
            {
                String validName = ((DegreeProgram)i).getDegreeName();
                if (!degrees.contains(validName)) degrees.add(validName);

                if (validName.equals(degreeProgram)) 
                {
                    isValid = true;
                    break;
                }    
            }

            if (!isValid){
                System.out.println("Here is a list of valid degrees: ");
                for (String x: degrees)
                {
                    System.out.println("\t "+x);
                }
                System.out.println("================");

                System.out.print("Enter a valid degree program (Enter q to exit): ");
                degreeProgram = reader.readLine();
                if(degreeProgram.toLowerCase().trim().equals("q")) 
                {
                    System.out.println("Undergraduate not added!");
                    return;
                }
            } 
        }
        while (!isValid);
        // ---------------------------------------------------
        /**
         * Same as code above
         */
        System.out.print("Enter the student's year of study: ");
        String yearOfStudy = reader.readLine();
        boolean isValidYear = false;
        do {
            try { 
                int check = Integer.parseInt(yearOfStudy); 
                if (check < 1)
                {
                    System.out.print("Please enter a valid integer over the number zero: "); 
                    yearOfStudy = reader.readLine();
                }
                else isValidYear = true;
            } catch(NumberFormatException e) { 
                System.out.print("Please enter a valid integer over the number zero: "); 
                yearOfStudy = reader.readLine();
            } catch(NullPointerException e) {
                return;
            }
            if(yearOfStudy.toLowerCase().trim().equals("q")) 
            {
                System.out.println("Undergraduate not added!");
                return;
            }        
        } while (!isValidYear);

        System.out.print("Enter the student's average: ");
        String averageMark = reader.readLine();

        boolean isValidAverage = false;
        do {
            try { 
                float check = Float.parseFloat(averageMark); 
                if (check < 0)
                {
                    System.out.print("Please enter a valid integer over or equals the number zero: "); 
                    averageMark = reader.readLine();
                }
                else isValidAverage = true;
            } catch(NumberFormatException e) { 
                System.out.print("Please enter a valid float over or equals the number zero: "); 
                averageMark = reader.readLine();
            } catch(NullPointerException e) {
                return;
            }
            if(averageMark.toLowerCase().trim().equals("q")) 
            {
                System.out.println("Undergraduate not added!");
                return;
            }        
        } while (!isValidAverage);

        
        Undergraduate student = new Undergraduate();
        
        student.setName(firstName);
        student.setSurname(surname);
        student.setStudentNumber(studentNumber);
        student.setDegreeProgram(degreeProgram);
        student.setAverageMark(Float.parseFloat(averageMark));
        student.setYearOfStudy(Integer.parseInt(yearOfStudy));
        this.database.store(student);

        System.out.println("Successfully added the student "+student.getName()+" "+ student.getSurname()+" to the database.");
    }
    
    /**
     * This function adds a postgraduate object to the database file
     * @param reader 
     * @throws IOException
     */
    public void addPostgraduate(BufferedReader reader) throws IOException
    { 
        System.out.print("Adding an postgraduate student. Wish to proceed? Y/n: ");
        String ans = reader.readLine();
        
        if( ans.toLowerCase().trim().equals("n")) return;
        
        System.out.print("Enter the student's first name: ");
        String firstName = reader.readLine();
        
        System.out.print("Enter the student's surname: ");
        String surname = reader.readLine();

        System.out.print("Enter the student's student number: ");
        String studentNumber = reader.readLine();
        
        System.out.print("Enter the student's degree program: ");
        String degreeProgram = reader.readLine();
        /**
         * Search for degree program in DB if not found display 
         * message showing that it does not exist and show a list of 
         * available degree programs. Do this in a loop until the user 
         * enters a valid degree program or they decide to quit 
         */

        DegreeProgram search = new DegreeProgram();
        ObjectSet result = this.database.queryByExample(search);
        boolean isValid = false;
        ArrayList<String> degrees = new ArrayList<String>();
        do
        {
            for (Object i: result)
            {
                String validName = ((DegreeProgram)i).getDegreeName();
                if (!degrees.contains(validName)) degrees.add(validName);

                if (validName.equals(degreeProgram)) 
                {
                    isValid = true;
                    break;
                }    
            }

            if (!isValid){
                System.out.println("Here is a list of valid degrees: ");
                for (String x: degrees)
                {
                    System.out.println("\t "+x);
                }
                System.out.println("================");

                System.out.print("Enter a valid degree program (Enter q to exit): ");
                degreeProgram = reader.readLine();
                if(degreeProgram.toLowerCase().trim().equals("q")) 
                {
                    System.out.println("Postgraduate not added!");
                    return;
                }
            } 
        }
        while (!isValid);

        System.out.print("Enter the student's supervisor initials: ");
        String initials = reader.readLine();

        System.out.print("Enter the student's supervisor surname: ");
        String supervisorSurname = reader.readLine();

        System.out.print("Enter the student's research topic: ");
        String researchTopic = reader.readLine();
        
        Postgraduate student = new Postgraduate();
        
        student.setName(firstName);
        student.setSurname(surname);
        student.setStudentNumber(studentNumber);
        student.setDegreeProgram(degreeProgram);
        student.setSupervisorInitials(initials);
        student.setSupervisorSurname(supervisorSurname);
        student.setResearchTopic(researchTopic);
        this.database.store(student);

        System.out.println("Successfully added the student "+student.getName()+" "+ student.getSurname()+" to the database.");
    }

    /**
     * This displays all the students objects , undergraduates and postgraduates, that are in 
     * the database file. 
     */
    public void listStudents()
    {
        Student search = new Student();
        ObjectSet result = this.database.queryByExample(search);
           
        output(result);
    }
    
    /**
     * This function displays all the students objects in the List.
     * If there are none an appropriate message is displayed.
     * @param set
     */
    public void output(List set)
    {
        if (set.size() == 0)
        {
            System.out.println("There are no students enrolled!");
            return;
        }

        System.out.println("The list of all current students: ");
        for (Object i: set)
        {
            Student current = (Student)i;
            System.out.println(current.toString());
        }
    }

    /**
     * This function display a prompt and request the name of a postgraduate object to locate 
     * in the database file. It will then display all the postgraduates with that name.
     * @param reader 
     * @throws IOException
     */
    public void findPostgraduate(BufferedReader reader) throws IOException
    {
        System.out.print("Please Enter a student's first name: ");
        String name = reader.readLine();
        System.out.print("Please Enter a student's surname name: ");
        String surname = reader.readLine();

        Postgraduate search = new Postgraduate();
        search.setName(name);
        search.setSurname(surname);
        ObjectSet result = this.database.queryByExample(search);
           
        System.out.println("POSTGRADUATES with the name "+name+" "+surname);
        output(result);
    }

    /**
     * This function display a prompt and request the name of a undergraduate object to locate 
     * in the database file. It will then display all the undergraduates with that name.
     * @param reader 
     * @throws IOException
     */
    public void findUndergraduate(BufferedReader reader) throws IOException
    {
        System.out.print("Please Enter a student's first name: ");
        String name = reader.readLine();

        Query search = this.database.query();
        search.constrain(Undergraduate.class);

        search.descend("name").constrain(name);
        ObjectSet result = search.execute();

        System.out.println("UNDERGRADUATES with the name "+name);
        output(result);
    }
    
    /**
     * This function prompts the user to enter a student number. If the number is not in the database file 
     * then it will leave the function else it will allow the user to update that particular student object's 
     * attributes.
     * @param reader 
     * @throws IOException
     */
    public void updateStudent(BufferedReader reader) throws IOException
    {
        System.out.println("UPDATE STUDENT");
        System.out.print("Enter the student's student number: ");
        String studentNumber = reader.readLine();

        Student search = new Student();
        search.setStudentNumber(studentNumber);
        ObjectSet result = this.database.queryByExample(search);

        if(result.isEmpty())
        {
            System.out.println("The student number "+studentNumber+
            " is not in the database.");
            return;
        }

        Student student = (Student)result.get(0);

        System.out.print("The current student is " + student.getName() +
        " " + student.getSurname() + ".\r\nThis student is enrolled for the " + 
        "degree program `" + student.getDegreeProgram() + "`.\r\nDo you " +
        "wish to update their details? (Y/n): " );

        String response = reader.readLine();
        if(response.toLowerCase().trim().equals("n"))
        {
            System.out.println();
            return;
        }
        
        System.out.println("NOTE: If you would like to leave any value unchanged"+
        " just skip the prompt.");

        System.out.print("Enter new first name: ");
        String name = reader.readLine();
        if (name.isBlank()) name = student.getName();

        System.out.print("Enter new surname: ");
        String surname = reader.readLine();
        if (surname.isBlank()) surname = student.getSurname();
        
        System.out.print("Enter new degree program: ");
        String degreeProgram = reader.readLine();
        if (degreeProgram.isBlank()) degreeProgram = student.getDegreeProgram();
        
        DegreeProgram searchDegree = new DegreeProgram();
        ObjectSet degreeResult = this.database.queryByExample(searchDegree);
        boolean isValid = false;
        ArrayList<String> degrees = new ArrayList<String>();
        do
        {
            for (Object i: degreeResult)
            {
                String validName = ((Student)i).getDegreeProgram();
                if (!degrees.contains(validName)) degrees.add(validName);

                if (validName.equals(degreeProgram)) 
                {
                    isValid = true;
                    break;
                }    
            }

            if (!isValid){
                System.out.println("Here is a list of valid degrees: ");
                for (String x: degrees)
                {
                    System.out.println("\t "+x);
                }
                System.out.println("================");

                System.out.print("Enter a valid degree program (Enter q to exit): ");
                degreeProgram = reader.readLine();
                if(degreeProgram.toLowerCase().trim().equals("q")) 
                {
                    System.out.println("Student not updated!");
                    return;
                }
            } 
        }
        while (!isValid);

        student.setName(name);
        student.setSurname(surname);
        student.setDegreeProgram(degreeProgram);
        

        if(student instanceof Undergraduate)
        {
            Undergraduate undergrad = (Undergraduate)student;

            System.out.print("Enter new year of study: ");
            int yearOfStudy;
            String yearOfStudyString = reader.readLine();

            boolean isValidYear = false;
            do {
                try { 
                    int check = Integer.parseInt(yearOfStudyString); 
                    if (check < 1)
                    {
                        System.out.print("Please enter a valid integer over the number zero: "); 
                        yearOfStudyString = reader.readLine();
                    }
                    else isValidYear = true;
                } catch(NumberFormatException e) { 
                    System.out.print("Please enter a valid integer over the number zero: "); 
                    yearOfStudyString = reader.readLine();
                } catch(NullPointerException e) {
                    return;
                }
                if(yearOfStudyString.toLowerCase().trim().equals("q")) 
                {
                    System.out.println("Undergraduate not updated!");
                    return;
                }        
            } while (!isValidYear);
            
            if (yearOfStudyString.isBlank()) yearOfStudy = undergrad.getYearOfStudy();
            else yearOfStudy = Integer.parseInt(yearOfStudyString);
            
            float averageMark;
            System.out.print("Enter new average mark: ");
            String averageMarkString = reader.readLine();
            
            boolean isValidAverage = false;
            do {
                try { 
                    float check = Float.parseFloat(averageMarkString); 
                    if (check < 0)
                    {
                        System.out.print("Please enter a valid integer over or equals the number zero: "); 
                        averageMarkString = reader.readLine();
                    }
                    else isValidAverage = true;
                } catch(NumberFormatException e) { 
                    System.out.print("Please enter a valid float over or equals the number zero: "); 
                    averageMarkString = reader.readLine();
                } catch(NullPointerException e) {
                    return;
                }
                if(averageMarkString.toLowerCase().trim().equals("q")) 
                {
                    System.out.println("Postgraduate not updated!");
                    return;
                }        
            } while (!isValidAverage);
            
            if (averageMarkString.isBlank()) averageMark = undergrad.getAverageMark();
            else averageMark = Float.parseFloat(averageMarkString);

            undergrad.setYearOfStudy(yearOfStudy);
            undergrad.setAverageMark(averageMark);
        }
        else if (student instanceof Postgraduate)
        {
            Postgraduate postgrad = (Postgraduate)student;
            
            System.out.print("Enter new supervisor initials: ");
            String supervisorInitials = reader.readLine();
            if (supervisorInitials.isBlank()) supervisorInitials = postgrad.getSupervisorInitials();
            
            System.out.print("Enter new supervisor surname: ");
            String supervisorSurname = reader.readLine();
            if (supervisorSurname.isBlank()) supervisorSurname = postgrad.getSupervisorSurname();
            
            System.out.print("Enter new research topic: ");
            String researchTopic = reader.readLine();
            if (researchTopic.isBlank()) researchTopic = postgrad.getResearchTopic();
            
            postgrad.setSupervisorInitials(supervisorInitials);
            postgrad.setSupervisorSurname(supervisorSurname);
            postgrad.setResearchTopic(researchTopic);
        }

        System.out.println("Successfully Updated the student's details.");
        System.out.println("--------------------------------------------");

    }
	
    private ObjectContainer database;
	private final String fileName = "database.db4o";
}