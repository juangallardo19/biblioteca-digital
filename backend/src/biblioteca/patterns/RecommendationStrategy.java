package biblioteca.patterns;

import biblioteca.data.DataStore;
import biblioteca.models.Book;
import biblioteca.models.User;
import java.util.ArrayList;
import java.util.List;

// [STRATEGY]
public interface RecommendationStrategy {
    List<Book> recommend(User user);
}