public class Student {
    public String getDegreeProgram() {
        return degreeProgram;
    }

    public void setDegreeProgram(String degreeProgram) {
        this.degreeProgram = degreeProgram;
    }

    public String getStudentNumber() {
        return studentNumber;
    }
    
    public void setStudentNumber(String studentNumber) {
        this.studentNumber = studentNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String toString()
    {
        String output = "";

        output = "\t Name: "+ getName()+ "\t, Surname: "+getSurname()+
                "\r\n\t Student Number: "+ getStudentNumber()+
                "\r\n\t Degree Program: "+ getDegreeProgram();
        
        return output;
    }

    private String degreeProgram; // Saves space when it is a string rather than an object, we can search database for program
    private String studentNumber;
    private String name;
    private String surname;
}
