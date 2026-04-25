package elmirsurkeev.kg.task1;

import java.util.Comparator;
import java.util.Scanner;

public class ActionCat {
    private final Scanner sc = new Scanner(System.in);
    public void startApp() {

        var cats = Cat.makeCats(10);
        Printer.print(cats);



        // А сюда добавьте код, который будет сортировать коллекцию котов
        // используйте лямбда-выражения и ссылки на методы
        cats.sort((c1, c2) -> c1.getBreed().compareTo(c2.getBreed()));
        System.out.println("Сортировка по породе кота");
        Printer.print(cats);

        cats.sort((c1, c2) -> c1.getName().compareTo(c2.getName()));
        System.out.println("Сортировка по имени");
        Printer.print(cats);

        cats.sort((c1, c2) -> Integer.compare(c1.getAge(), c2.getAge()));
        System.out.println("Сортировка по возрасту в порядке возрастания");
        Printer.print(cats);

        cats.sort((c1, c2) -> Integer.compare(c2.getAge(), c1.getAge()));
        System.out.println("Сортировка по возрасту в порядке возрастания");
        Printer.print(cats);

        System.out.println("Введите цвет для удаления" +
                "\n TABBY, SILVER, GRAY, PEACH, GINGER, TORTIE");

        String enterName = sc.nextLine();

        cats.removeIf((cat -> cat.equals(enterName.toUpperCase())));
        Printer.print(cats);

        System.out.println("Сортировка где длина имени больше 5 символов");
        cats.removeIf((cat -> cat.getName().length() > 5));
        Printer.print(cats);

    }

}
