import java.util.ArrayList;
import java.util.Scanner;

public class Main4 {
    public static void main(String[] args) {
        ArrayList<Person> people = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        int choice = 0;
        while (true) {
            System.out.println("Menu:");
            System.out.println("1.Add");
            System.out.println("2.Show");
            System.out.println("3.Exit");
            choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    System.out.println("Введите имя и фамилию");
                    people.add(new Person(scanner.next(), scanner.next()));
                    break;
                case 2:
                    people.forEach(System.out::println);
                    break;
                case 3:
                    return;
                default:
                    System.out.println("Wrong input");
            }
        }
    }
}
