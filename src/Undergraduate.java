public class Undergraduate extends Student {
    public int getYearOfStudy() {
        return yearOfStudy;
    }

    public void setYearOfStudy(int yearOfStudy) {
        this.yearOfStudy = yearOfStudy;
    }

    public float getAverageMark() {
        return averageMark;
    }

    public void setAverageMark(float averageMark) {
        this.averageMark = averageMark;
    }

    public String toString()
    {
        String output = super.toString();

        output += "\r\n\t Year of Study: "+ Integer.toString(getYearOfStudy()) +
                "\r\n\t Average Mark: " + Float.toString(getAverageMark()) +
                "\r\n--------------------------------------------";

        return output;
    }

    private int yearOfStudy;

    private float averageMark;
}
