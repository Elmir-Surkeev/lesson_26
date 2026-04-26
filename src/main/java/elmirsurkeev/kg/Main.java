package elmirsurkeev.kg;

import elmirsurkeev.kg.moviesearch.Action;
import elmirsurkeev.kg.task1.ActionCat;
import elmirsurkeev.kg.task2.ActionActiveCat;
//import elmirsurkeev.kg.task2.ActionActiveCat;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        ///ActionCat actionCat = new ActionCat();
        ActionActiveCat actionActiveCat = new ActionActiveCat();
        //Action action = new Action();

        actionActiveCat.startApp();
//        Scanner sc = new Scanner(System.in);
//        System.out.println("Введите 1 для просмотра 1 задания из урока 27" +
//                "\n введите 2 для просмотра 2 задания из урока 27" +
//                "\n введите 3 для просмотра 3 задания из урока 27 (26)" +
//                "\n введите 0 для выхода работы из программы");
//
//        while (true) {

//            try {
//                int choice = sc.nextInt();
//                sc.nextLine();
//
//                switch (choice) {
//                    case 0:
//                        break;
//                    case 1:
//                        action.startApp();
//                        break;
//                    case 2:
//                        actionCat.startApp();
//                        break;
//                    case 3:
//                        actionActiveCat.startApp();
//                        break;
//                    default:
//                        System.out.println("Такого функционала у нас нет");
//                        break;
//                }
//
//            } catch (Exception e) {
//                System.out.println("Введите цифру");
//                sc.nextLine();
//            }
//        }

    }
}
