package elmirsurkeev.kg.task1;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.function.Predicate;

public class ActionCat {
    private final Scanner sc = new Scanner(System.in);
    public void startApp() {

        var cats = Cat.makeCats(10);
        System.out.println("Вывод созданных котов");
        Printer.print(cats);

        while(true){
            try{
                System.out.println("Введите желаемое действие" +
                        "\n 1 по породе кота" +
                        "\n 2 по имени" +
                        "\n 3 по возрасту в порядке возрастания" +
                        "\n 4 по возрасту в порядке убывания" +
                        "\n 5 для сортировки c удалением выбранного цвета" +
                        "\n 6 по имени чья длина больше 5 символов" +
                        "\n 0 для выхода" );
                int choice = sc.nextInt();
                sc.nextLine();
                switch (choice){
                    case 1:
                        cats.sort((c1, c2) -> c1.getBreed().compareTo(c2.getBreed()));
                        System.out.println("Сортировка по породе кота");
                        Printer.print(cats);
                        break;
                    case 2:
                        cats.sort((c1, c2) -> c1.getName().compareTo(c2.getName()));
                        System.out.println("Сортировка по имени");
                        Printer.print(cats);
                        break;
                    case 3:
                        cats.sort((c1, c2) -> Integer.compare(c1.getAge(), c2.getAge()));
                        System.out.println("Сортировка по возрасту в порядке возрастания");
                        Printer.print(cats);
                        break;
                    case 4:
                        cats.sort((c1, c2) -> Integer.compare(c2.getAge(), c1.getAge()));
                        System.out.println("Сортировка по возрасту в порядке возрастания");
                        Printer.print(cats);
                        break;
                    case 5:
                        List<Cat> copyCat = new ArrayList<>(cats);
                        //для сравнения enum нужно использовать ==
                        // ведь важно чтобы булевое значение было, классическим equalsIgnoreCase не получится
                        System.out.println("Введите цвет для удаления" +
                                "\n TABBY, SILVER, GRAY, PEACH, GINGER, TORTIE");

                        while (true){

                            try {
                                String enterName = sc.nextLine().trim().toUpperCase();
                                copyCat.removeIf(cat -> cat.getColor() == Cat.Color.valueOf(enterName));
                                Printer.print(copyCat);
                                break;

                            }catch (Exception e){
                                System.out.println("Введите корректное значение");
                            }
                        }
                        break;
                    case 6:
                        //Копия чтобы не трогать основной лист котов
                        List<Cat> copyCatForName = new ArrayList<>(cats);
                        System.out.println("Сортировка где длина имени больше 5 символов");
                        copyCatForName.removeIf((cat -> cat.getName().length() <= 5));
                        Printer.print(copyCatForName);
                        break;
                    case 0:
                        return;
                    default:
                        System.out.println("Введите корректное действие (цифры 0-6)");
                }
            }catch (Exception e){
                System.out.println("Введите цифру");
                sc.nextLine();
            }
        }

    }

}
