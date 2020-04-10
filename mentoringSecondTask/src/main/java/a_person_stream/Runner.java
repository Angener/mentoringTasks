package a_person_stream;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Runner {
    private static List<Person> persons = new ArrayList<>();

    private static final Comparator<Person> byAge = Comparator.comparingInt(Person::getAge);
    private static final Comparator<Person> byName = Comparator.comparing(Person::getName);

    public static void main(String[] args) {

        persons.add(new Person("Vaska", 18));
        persons.add(new Person("Froska", 23));
        persons.add(new Person("Josaph", 24));
        persons.add(new Person("Tralebusina", 76));
        persons.add(new Person("Perkosrack", 68));

        System.out.println("before sorting:");
        persons.forEach(System.out::println);

        persons.sort(byAge);
        System.out.println("Age sort");
        persons.forEach(System.out::println);

        persons.sort(byName);
        System.out.println("Name sort");
        persons.forEach(System.out::println);
    }
}
