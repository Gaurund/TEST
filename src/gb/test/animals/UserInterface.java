package gb.test.animals;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class UserInterface {
    private static final String FILE_NAME = "registry.txt";
    public static Scanner scanner = new Scanner(System.in);
    private final List<String> showAllInput = Arrays.asList("show", "s", "print", "список", "каталог", "реестр", "1");
    private final List<String> addingInput = Arrays.asList("add", "a", "put", "p", "добавить", "ввод", "2");
    private final List<String> searchInput = Arrays.asList("search", "find", "f", "найти", "поиск", "3");
    private final List<String> quitInput = Arrays.asList("quit", "q", "й", "выход", "авада кедавра", "4");

    private final List<String> yesInput = Arrays.asList("yes", "y", "а тож!", "да", "д", "хочу");
    private final List<String> noInput = Arrays.asList("no", "not", "n", "nope", "нет", "н");
    private final Registry registry;

    public UserInterface() {
        this.registry = new Registry();
    }

    public void run() {
        registry.restoreRegistry(FILE_NAME);
        greetings();

        while (true) {
            System.out.println("\nВаш выбор: ");
            String input = scanner.nextLine().strip().toLowerCase();
            if (showAllInput.contains(input)) {
                showAll();
            } else if (quitInput.contains(input)) {
                System.out.println("До новых встреч...");
                break;
            } else if (addingInput.contains(input)) {
                put();
            } else if (searchInput.contains(input)) {
                search();
            } else {
                System.out.println("\nВвод некорректен. Повторите. ");
            }
        }
        registry.storeRegistry(FILE_NAME);
    }

    private void greetings() {
        System.out.println("\nРеестр животных\n1. Просмотр реестра.\n2. Ввести новое животное.\n3. Найти животное. \n4. Выйти.");
    }

    private void showAll() {
        if (!registry.isEmpty()) registry.showAll();
        else System.out.println("\nКаталог пуст.\n");
    }

    private void put() {
        System.out.println("\nВведите имя:");
        String name = scanner.nextLine().strip();
        System.out.println("\nДомашнее или вьючное животное?");
        String type = scanner.nextLine().strip().toLowerCase();
        System.out.println("\nК какому виду относиться?");
        String specie = scanner.nextLine().strip().toLowerCase();
        System.out.println("\nВведите через пробел команды которые умеет выполнять питомец:");
        String[] tricksArr = scanner.nextLine().strip().toLowerCase().split(" ");
        ArrayList<String> tricks = new ArrayList<>(List.of(tricksArr));
        registry.put(name, type, specie, tricks);
    }

    private void search() {
        System.out.println("\nВведите имя животного.");
        String name = scanner.nextLine().strip().toLowerCase();
        Animal result = registry.getByName(name);

        if (result != null) {
            System.out.println("\nНайдено: " + result + "\nХотите научить его новым трюкам? (Y/n)");
            String answer = scanner.nextLine().strip().toLowerCase();
            if (yesInput.contains(answer)) {
                System.out.println("\nВведите название новой команды для " + result.getName());
                String newTrick = scanner.nextLine().strip().toLowerCase();
                result.putNewTrick(newTrick);
                System.out.println("\nНовый трюк " + newTrick + " добавлен!");
            } else if (noInput.contains(answer)) {
                System.out.println("\nТогда вернёмся в меню...");
            } else {
                System.out.println("\nНадо так понимать, что не хотите...");
            }
        } else {
            System.out.println("\nПростите. Ничего не нашлось.\n");
        }

    }
}
