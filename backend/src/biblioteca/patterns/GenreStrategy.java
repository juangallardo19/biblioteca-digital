package biblioteca.patterns;

import biblioteca.data.DataStore;
import biblioteca.models.Book;
import biblioteca.models.User;

import java.util.ArrayList;
import java.util.List;

public class GenreStrategy implements RecommendationStrategy {
    private final DataStore data;
    private final String genre;

    public GenreStrategy(DataStore data, String genre) {
        this.data = data;
        this.genre = genre;
    }

    @Override
    public List<Book> recommend(User user) {
        List<Book> result = new ArrayList<>();
        for (Book b : data.getAllBooks()) {
            if (b.getGenre() != null && b.getGenre().equalsIgnoreCase(genre)) {
                result.add(b);
            }
        }
        return result;
    }
}