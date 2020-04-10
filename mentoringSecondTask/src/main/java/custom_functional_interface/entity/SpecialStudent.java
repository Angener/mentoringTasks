package custom_functional_interface.entity;

public class SpecialStudent {
    public String faculty;
    public int groupNumber;
    public String sureName;
    public String specialization;
    public float skill;

    public SpecialStudent(String faculty, int groupNumber, String sureName, String specialization, float skill) {
        this.faculty = faculty;
        this.groupNumber = groupNumber;
        this.sureName = sureName;
        this.specialization = specialization;
        this.skill = skill;
    }

    @Override
    public String toString() {
        return "SpecialStudent{" +
                "faculty='" + faculty + '\'' +
                ", groupNumber=" + groupNumber +
                ", sureName='" + sureName + '\'' +
                ", specialization='" + specialization + '\'' +
                ", skill=" + skill +
                '}';
    }
}
