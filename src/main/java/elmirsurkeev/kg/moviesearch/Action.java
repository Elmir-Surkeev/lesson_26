package elmirsurkeev.kg.moviesearch;

import com.google.gson.Gson;

import javax.xml.crypto.Data;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.util.*;

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
        if (data == null) {
            System.out.println("Ошибка: Данные не загружены.");
            return;
        }

        while (true) {
            System.out.println("\n================ МЕНЮ УПРАВЛЕНИЯ ================");
            System.out.println("1. Просмотр всех фильмов");
            System.out.println("2. Поиск фильма по названию");
            System.out.println("3. Сортировки (Имя / Год / Режиссер)");
            System.out.println("4. Найти фильмы по АКТЕРУ");
            System.out.println("5. Список всех актеров и их ролей");
            System.out.println("6. Найти фильмы по ГОДУ");
            System.out.println("7. Найти фильмы по РЕЖИССЕРУ");
            System.out.println("0. Выход");
            System.out.print("Выберите действие: ");

            int choice = sc.nextInt();
            sc.nextLine();

            if (choice == 0) break;

            switch (choice) {
                case 1 -> showAllFilms(data);
                case 2 -> searchMovieByName(data);
                case 3 -> sortedFilms(data);
                case 4 -> searchByActor(data);
                case 5 -> listAllActors(data);
                case 6 -> searchByYear(data);
                case 7 -> searchByDirector(data);
                default -> System.out.println("Некорректный ввод. Попробуйте снова.");
            }
        }
    }

    public void showAllFilms(MovieData data) {

        System.out.println("Список фильмов");

        for (Movie movie : data.movies) {
            System.out.println("=".repeat(50));
            System.out.println("Фильм - " + movie.getName()+ " " + movie.getYear());

            if (movie.getDirector() != null) {
                System.out.println("Режиссёр: " + movie.getDirector().getFullName());
            }

            if (movie.getCast() != null && !movie.getCast().isEmpty()) {
                System.out.println("Актёры: ");
                for (Cast cast : movie.getCast()) {
                    System.out.println("   - " + cast.getFullName() + " - " + cast.getRole());
                }
            }
        }
    }

    public void searchMovieByName(MovieData data) {
        System.out.println("Введите название");
        sc.nextLine();
        boolean found = false;
        String name = sc.next();
        for (Movie movie : data.movies) {
            if (normalizeString(movie.getName()).contains(normalizeString(name))) {
                {
                    System.out.println(movie.getName());
                    System.out.println(movie.getYear());
                    found = true;
                }
            }
            if (!found) {
                System.out.println("К сожалению такого фильма у нас нету");
            }
        }
    }


    private String normalizeString (String text){
            return text.trim()
                    .replaceAll("\\s+", " ")
                    .toLowerCase();
        }


    public void sortedFilms(MovieData data) {
        System.out.println("\n[ Сортировка по названию фильма ]");
        data.movies.sort((m1, m2) -> m1.getName().compareToIgnoreCase(m2.getName()));

        System.out.printf("| %-45s | %-6s | %-20s |%n", "Название", "Год", "Режиссер");

        for (Movie movie : data.movies) {
            printMovieRow(movie);
        }

        System.out.println("\n[ Сортировка по году (от старых к новым) ]");
        data.movies.sort((m1, m2) -> Integer.compare(m1.getYear(), m2.getYear()));

        for (Movie movie : data.movies) {
            printMovieRow(movie);
        }
        System.out.println("\n[ Сортировка по режиссеру ]");
        data.movies.sort((m1, m2) -> m1.getDirector().getFullName().compareToIgnoreCase(m2.getDirector().getFullName()));

        for (Movie movie : data.movies) {
            printMovieRow(movie);
        }
    }

    private void printMovieRow(Movie m) {
        System.out.printf("| %-45s | %-6d | %-20s |%n",
                m.getName(),
                m.getYear(),
                m.getDirector().getFullName());
    }

    public void searchByActor(MovieData data) {
        System.out.println("Введите имя актера");
        String actorName = sc.next();

        System.out.println("\nПоиск фильмов с актером: " + actorName);
        boolean found = false;

        for (Movie movie : data.movies) {
            for (Cast member : movie.getCast()) {
                if (member.getFullName().equalsIgnoreCase(actorName)) {
                    System.out.println(movie.getName() + " Роль: " + member.getRole());
                    found = true;
                }
            }
        }
        if (!found) System.out.println("Актер не найден.");
    }
    public void listAllActors(MovieData data) {
        System.out.println("Полный список актеров отсортирован");
        TreeSet<String> actors = new TreeSet<>();

        for (Movie movie : data.movies) {
            for (Cast member : movie.getCast()) {
                actors.add(member.getFullName() + "роль: " + member.getRole());
            }
        }

        for (String actorInfo : actors) {
            System.out.println(actorInfo);
        }
    }
    public void searchByYear(MovieData data) {
        System.out.println("Введите год");
        int year = sc.nextInt();
        System.out.println("\nФильмы " + year + " года:");
        data.movies.stream()
                .filter(m -> m.getYear() == year)
                .forEach(m -> System.out.println("-" + m.getName()));
    }

    public void searchByDirector(MovieData data) {
        System.out.println("Введите имя режиссера");
        String  directorName = sc.next();

        Map<String, List<Movie>> directorMap = new HashMap<>();

        for (Movie movie : data.movies) {
            String name = movie.getDirector().getFullName();
            directorMap.computeIfAbsent(name, k -> new ArrayList<>()).add(movie);
        }

        List<Movie> results = directorMap.get(directorName);
        if (results != null) {
            results.forEach(m -> System.out.println("Найден фильм: " + m.getName()));
        } else {
            System.out.println("Режиссер не найден.");
        }
    }

}
