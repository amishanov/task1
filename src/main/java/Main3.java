import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

public class Main3 {
    public static void main(String[] args) {
        ArrayList<Person> people = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Введите имя и фамилию или 0 для выхода");
            String firstName = scanner.next();
            if (firstName.equals("0"))
                return;
            String lastName = scanner.next();
            Person person = new Person(firstName, lastName);
            people.add(person);
            people.stream().sorted(Comparator.comparing(Person::getLastName))
                    .forEach(System.out::println);
        }
    }
}
