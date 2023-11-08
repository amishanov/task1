import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;

public class Main7 {
    public static void main(String[] args) {
        ArrayList<Person> people = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        Menu menu = new Menu(scanner);
        while (true) {
            menu.show();
            Exec currExec = menu.input();
            try {
                currExec.exec(people);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private interface Exec {
        void exec(List<Person> data) throws Exception;
    }

    private static class MenuItem {
        // Наименование пункта меню
        private String name;
        // Доступное действие
        private Exec exec;

        public MenuItem(String name, Exec exec) {
            this.name = name;
            this.exec = exec;
        }
    }

    private static class Menu {
        private List<MenuItem> items;
        private Scanner scanner;
        private int currState;

        public Menu(Scanner scanner) {
            this.scanner = scanner;
            items = new ArrayList<>();
            itemsInit();
        }

        /**
         * Инициализирует пункты меню
         */
        private void itemsInit() {
            items.add(new MenuItem("1.Add",
                    data -> {
                        System.out.println("Введите имя и фамилию");
                        data.add(new Person(scanner.next(), scanner.next()));
                    }));
            items.add(new MenuItem("2.Show",
                    data -> data.forEach(System.out::println)));
            items.add(new MenuItem("3.Show sorted unique",
                    data -> data.stream().filter(distinctByKey(Person::getLastName))
                            .sorted(Comparator.comparing(Person::getLastName))
                            .forEach(System.out::println)));
            items.add(new MenuItem("4.Save to file",
                    data -> writeToFile(data)));
            items.add(new MenuItem("5.Exit",
                    data -> System.exit(0)));
        }

        public void show() {
            items.forEach(a -> System.out.println(a.name));
        }

        public Exec input() {
            currState = scanner.nextInt() - 1;
            return items.get(currState).exec;
        }

        private void writeToFile(List<Person> data) {
            try (FileOutputStream file = new FileOutputStream("people.txt");
                 ObjectOutputStream oos = new ObjectOutputStream(file)) {
                oos.writeObject(data);
                oos.flush();
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    public static <T> Predicate<T> distinctByKey(
            Function<? super T, ?> keyExtractor) {
        Map<Object, Boolean> seen = new ConcurrentHashMap<>();
        return t -> seen.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
    }
}


