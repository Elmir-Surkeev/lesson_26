package elmirsurkeev.kg.moviesearch;

import com.google.gson.Gson;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;

public class Action {
    public void readJson() {
        Gson gson = new Gson();
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("movies.json");

        if (inputStream == null) {
            System.out.println("Файл movies.json не найден в resources.");
            return;
        }

        try (Reader reader = new InputStreamReader(inputStream, StandardCharsets.UTF_8)) {
            MovieData data = gson.fromJson(reader, MovieData.class);

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
    }
}
