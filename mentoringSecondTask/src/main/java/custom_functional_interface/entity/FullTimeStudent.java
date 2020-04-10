package custom_functional_interface.entity;

public class FullTimeStudent extends Student{

    public int groupNumber;
    public String sureName;

    public FullTimeStudent(String faculty, int groupNumber, String sureName) {
        super(faculty);
        this.groupNumber = groupNumber;
        this.sureName = sureName;
    }

    @Override
    public String toString() {
        return "FullTimeStudent{" +
                "groupNumber=" + groupNumber +
                ", sureName='" + sureName + '\'' +
                ", faculty='" + faculty + '\'' +
                '}';
    }
}
