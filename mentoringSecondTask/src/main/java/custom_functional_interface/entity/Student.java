package custom_functional_interface.entity;

public class Student{
    public String faculty;

    public Student(String faculty){
        this.faculty = faculty;
    }

    @Override
    public String toString() {
        return "Student{" +
                "faculty='" + faculty + '\'' +
                '}';
    }
}
