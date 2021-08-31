public class Postgraduate extends Student{
    public String getSupervisorInitials() {
        return supervisorInitials;
    }

    public void setSupervisorInitials(String supervisorInitials) {
        this.supervisorInitials = supervisorInitials;
    }

    public String getSupervisorSurname() {
        return supervisorSurname;
    }

    public void setSupervisorSurname(String supervisorSurname) {
        this.supervisorSurname = supervisorSurname;
    }

    public String getResearchTopic() {
        return researchTopic;
    }

    public void setResearchTopic(String researchTopic) {
        this.researchTopic = researchTopic;
    }

    public String toString()
    {
        String output = super.toString();

        output += "\r\n\t Supervisor Initials: "+ getSupervisorInitials() +
                "\t, Supervisor Surname: " + getSupervisorSurname() +
                "\r\n\t Research Topic: " + getResearchTopic() +
                "\r\n--------------------------------------------";

        return output;
    }

    private String supervisorInitials;

    private String supervisorSurname;

    private String researchTopic;
}
