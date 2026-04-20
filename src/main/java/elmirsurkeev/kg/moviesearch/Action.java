package elmirsurkeev.kg.moviesearch;

import com.google.gson.Gson;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Action {
    public MovieData data;
    Scanner sc = new Scanner(System.in);
    public void readJson() {
        Gson gson = new Gson();
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("movies.json");

        if (inputStream == null) {
            System.out.println("Файл movies.json не найден в resources.");
            return;
        }

        try (Reader reader = new InputStreamReader(inputStream, StandardCharsets.UTF_8)) {
            this.data = gson.fromJson(reader, MovieData.class);

            if (data != null && data.movies != null) {
                System.out.println("Прочитано фильмов: " + data.movies.size());
            } else {
                System.out.println("JSON пуст или имеет неверную структуру.");
            }
        } catch (Exception e) {
            System.out.println("Ошибка при парсинге JSON:");
            e.printStackTrace();
        }


    }

    public void startApp() {
        readJson();

        System.out.println(" Выберите деиствие " +
                            "\n 1 для просмотра всех фильмов" +
                            "\n 2 для поиска по записи" +
                            "\n 3 для сортировки коллекций: по году выпуска, по жанру, по режжисеру");

        while (true) {
            int choice = sc.nextInt();
            switch (choice) {
                case 1:
                    //просмотр всех фильмов
                    showAllFilms(this.data);
                    break;
                case 2:
                    //вызов метода по записи
                    break;
                case 3:
                    //сортировка
                    break;
                default:
                    System.out.println("Введите корректное значение");
            }
        }
    }

    public void showAllFilms(MovieData data) {
        if (data == null) {
            System.out.println("Список фильмов пуст");
            return;
        }

        System.out.println("Список фильмов");

        for (Movie  movie : data.movies) {
            System.out.println(movie.getName());
//            System.out.println(movie.getYear());
//            System.out.println(movie.getDescription());
//
//            if (movie.getDirector() != null) {
//                System.out.println(movie.getDirector().getFullName());
//            }
//
//            if (movie.getCast() != null) {
//                System.out.println("Актеры: ");
//                for (Cast cast : movie.getCast()) {
//                    System.out.println(cast.getFullName());
//                    System.out.println(cast.getRole());
//                }
//            }

        }

    }
}
