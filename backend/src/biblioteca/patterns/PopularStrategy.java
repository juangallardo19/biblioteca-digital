package biblioteca.patterns;

import biblioteca.data.DataStore;
import biblioteca.models.Book;
import biblioteca.models.User;

import java.util.ArrayList;
import java.util.List;

public class PopularStrategy implements RecommendationStrategy {
    private final DataStore data;
    private final int limit;

    public PopularStrategy(DataStore data, int limit) {
        this.data = data;
        this.limit = limit;
    }

    @Override
    public List<Book> recommend(User user) {
        List<Book> all = new ArrayList<>(data.getAllBooks());
        return all.subList(0, Math.min(limit, all.size()));
    }
}