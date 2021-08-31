import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Tester
{
    public static void main(String[] args)
    {
        db = new Database();
		db.open();
		mainmenu();
        try
        {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String input = reader.readLine();
            while(!input.equals("8"))
            {
                if(input.equals("1"))
                    db.addDegreeProgram(reader);
                if(input.equals("2"))
                    db.addUndergraduate(reader);
                if(input.equals("3"))
                    db.addPostgraduate(reader);
                if(input.equals("4"))
                    db.listStudents();
                if(input.equals("5"))
                    db.findPostgraduate(reader);
                if(input.equals("6"))
                    db.findUndergraduate(reader);
                if(input.equals("7"))
                    db.updateStudent(reader);
                mainmenu();
                input = reader.readLine();
            }
        }
        catch(IOException e)
        {
            System.out.println("Error: " + e);
        }
		db.close();
    }
    
    public static void mainmenu()
	{
            System.out.println("MAIN MENU");
            System.out.println("=========");
            System.out.println("1.	Add degree program");
            System.out.println("2.	Add undergraduate student");
            System.out.println("3.	Add postgraduate student");
            System.out.println("4.	List students");
            System.out.println("5.	Find postgraduate student(QBE)");
            System.out.println("6.	Find undergraduate student(SODA POP)");
            System.out.println("7.	Update student");
            System.out.println("8.	Exit");
	}
    public static Database db;
}