package collection_to_stream;

import java.util.*;

public class Runner {
    public static void main(String[] args) {
        Author kingStephen = new Author("Stephen King", (short) 73, new ArrayList<>());
        Book it = new Book("  It ", Collections.singletonList(kingStephen), 323);
        Book cujo = new Book("Cujo ", Collections.singletonList(kingStephen), 743);
        Book firestarter = new Book("Firestarter", Collections.singletonList(kingStephen), 542);
        Author asimovIsaac = new Author("Isaac Asimov", (short) 101, new ArrayList<>());
        Book iRobot = new Book("  I, Robot", Collections.singletonList(asimovIsaac), 342);
        Book foundation = new Book("Foundation ", Collections.singletonList(asimovIsaac), 123);
        Book pebbleInTheSky = new Book("Pebble in the sky", Collections.singletonList(asimovIsaac), 180);
        kingStephen.addBook(it);
        kingStephen.addBook(cujo);
        kingStephen.addBook(firestarter);
        asimovIsaac.addBook(iRobot);
        asimovIsaac.addBook(foundation);
        asimovIsaac.addBook(pebbleInTheSky);

        Author[] authors = {kingStephen, asimovIsaac};
        Book[] books = {it, cujo, firestarter, iRobot, foundation, pebbleInTheSky};

        System.out.println("more than 200 pages:");
        Arrays.stream(books)
                .parallel()
                .filter(b -> b.getNumberOfPages() > 200)
                .peek(b -> b.setTitle(b.getTitle().trim().toUpperCase()))
                .forEach(System.out::println);

        System.out.println("maximum pages:");
        Arrays.stream(books)
                .parallel()
                .peek(b -> b.setNumberOfPages(b.getNumberOfPages() + 2))
                .max(Comparator.comparing(Book::getNumberOfPages))
                .ifPresent(System.out::println);

        System.out.println("minimum pages:");
        Arrays.stream(books)
                .parallel()
                .min(Comparator.comparing(Book::getNumberOfPages))
                .ifPresent(System.out::println);

        Arrays.stream(books)
                .parallel()
                .peek((b) -> System.out.println("SINGLE AUTHOR"))
                .filter(b -> b.getAuthors().size() == 1)
                .forEach(System.out::println);

        System.out.println("Sorted by number of pages:");
        Arrays.stream(books)
                .parallel()
                .sorted(Comparator.comparing(Book::getTitle))
                .forEachOrdered(System.out::println);

        System.out.println("Sorted by title:");
        Arrays.stream(books)
                .parallel()
                .sorted(Comparator.comparing(Book::getNumberOfPages))
                .forEachOrdered(System.out::println);

        System.out.println("Titles list:");
        new ArrayList<>(
                Arrays.stream(books)
                        .parallel()
                        .map(Book::getTitle)
                        .collect(ArrayList::new, ArrayList::add, ArrayList::addAll)
        ).forEach(System.out::println);

        System.out.println("Distinct authors:");
        Arrays.stream(books)
                .parallel()
                .map(Book::getAuthors)
                .distinct()
                .forEach(System.out::println);

        Arrays.stream(authors)
                .flatMap(author -> author.getBooks().stream())
                .max(Comparator.comparing(Book::getNumberOfPages))
                .ifPresent(System.out::println);
    }
}
