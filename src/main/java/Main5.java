import java.util.ArrayList;
import java.util.Comparator;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;

public class Main5 {
    public static void main(String[] args) {
        ArrayList<Person> people = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        String choice = "";
        while (true) {
            System.out.println("Menu:");
            System.out.println("1.Add");
            System.out.println("2.Show");
            System.out.println("3.Show sorted unique");
            System.out.println("4.Exit");
            choice = scanner.next();
            switch (choice) {
                case "1":
                    System.out.println("Введите имя и фамилию");
                    people.add(new Person(scanner.next(), scanner.next()));
                    break;
                case "2":
                    people.forEach(System.out::println);
                    break;
                case "3":
                    people.stream().filter(distinctByKey(Person::getLastName))
                            .sorted(Comparator.comparing(Person::getLastName))
                            .forEach(System.out::println);
                    break;
                case "4":
                    return;
                default:
                    System.out.println("Wrong input");
            }
        }
    }

    public static <T> Predicate<T> distinctByKey(
            Function<? super T, ?> keyExtractor) {

        Map<Object, Boolean> seen = new ConcurrentHashMap<>();
        return t -> seen.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
    }
}
