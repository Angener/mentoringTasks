package custom_functional_interface;

import custom_functional_interface.entity.ExtramuralStudent;
import custom_functional_interface.entity.FullTimeStudent;
import custom_functional_interface.entity.SpecialStudent;
import custom_functional_interface.entity.Student;

import java.util.*;

public class Runner {

    private static List<ExtramuralStudent> extramuralStudents = new ArrayList<>();
    private static List<FullTimeStudent> fullTimeStudents = new ArrayList<>();

    private static CustomInterface<List<ExtramuralStudent>,
            List<FullTimeStudent>,
            String,
            List<Student>> finder = (extramuralStudents, fullTimeStudents, faculty) -> {
        List<Student> foundStudents = new ArrayList<>();

        foundStudents.addAll(extramuralStudents
                .stream()
                .filter(student -> student.faculty.equals(faculty))
                .collect(ArrayList::new, ArrayList::add, ArrayList::addAll));

        foundStudents.addAll((fullTimeStudents
                .stream()
                .filter(student -> student.faculty.equals(faculty))
                .collect(ArrayList::new, ArrayList::add, ArrayList::addAll)));
        return foundStudents;
    };

    private static CustomInterface<FullTimeStudent, String, Float, SpecialStudent> qualifier =
            (student, qualification, skill) ->
                    new SpecialStudent(student.faculty, student.groupNumber,
                            student.sureName, qualification, skill);

    public static void main(String[] args) {
        fillStudentLists();
        new ArrayList<>(finder.get(extramuralStudents, fullTimeStudents, "Flight"))
                .forEach(System.out::println);

        System.out.println(qualifier.get(fullTimeStudents.get(1), "Pilot", 5.5f));
    }

    private static void fillStudentLists() {
        extramuralStudents.add(new ExtramuralStudent("Radio", 1, "Gew"));
        extramuralStudents.add(new ExtramuralStudent("Radio", 1, "Rew"));
        extramuralStudents.add(new ExtramuralStudent("Flight", 3, "Vew"));
        extramuralStudents.add(new ExtramuralStudent("Radio", 42, "Gawee"));
        extramuralStudents.add(new ExtramuralStudent("Radio", 53, "Fgye"));
        extramuralStudents.add(new ExtramuralStudent("Radio", 23, "awfb"));
        extramuralStudents.add(new ExtramuralStudent("Flight", 15, "lsih"));
        extramuralStudents.add(new ExtramuralStudent("Radio", 1, "jjksb"));
        extramuralStudents.add(new ExtramuralStudent("Radio", 3, "fswa"));
        extramuralStudents.add(new ExtramuralStudent("Radio", 45, "awfi"));
        extramuralStudents.add(new ExtramuralStudent("Radio", 6, "siufb"));
        fullTimeStudents.add(new FullTimeStudent("Radio", 32, "Lok"));
        fullTimeStudents.add(new FullTimeStudent("Radio", 22, "Pou"));
        fullTimeStudents.add(new FullTimeStudent("Flight", 32, "Huio"));
        fullTimeStudents.add(new FullTimeStudent("Flight", 22, "Jfe"));
        fullTimeStudents.add(new FullTimeStudent("Flight", 32, "IGvm"));
        fullTimeStudents.add(new FullTimeStudent("Flight", 22, "fkI"));
        fullTimeStudents.add(new FullTimeStudent("Radio", 22, "POuh"));
        fullTimeStudents.add(new FullTimeStudent("Radio", 32, "Bft"));
    }
}
