package custom_functional_interface.entity;

public class ExtramuralStudent extends Student {

    public int groupNumber;
    public String sureName;

    public ExtramuralStudent(String faculty, int groupNumber, String sureName) {
        super(faculty);
        this.groupNumber = groupNumber;
        this.sureName = sureName;
    }

    @Override
    public String toString() {
        return "ExtramuralStudent{" +
                "groupNumber=" + groupNumber +
                ", sureName='" + sureName + '\'' +
                ", faculty='" + faculty + '\'' +
                '}';
    }
}
