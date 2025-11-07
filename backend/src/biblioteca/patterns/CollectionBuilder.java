package biblioteca.patterns;

import biblioteca.models.Book;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

// [BUILDER]
public class CollectionBuilder {
    private final List<Book> books = new ArrayList<>();
    private String onlyCategory;

    public CollectionBuilder add(Book book) {
        books.add(book);
        return this;
    }

    public CollectionBuilder onlyCategory(String category) {
        this.onlyCategory = category;
        return this;
    }

    public List<Book> build() {
        if (onlyCategory == null) return new ArrayList<>(books);
        return books.stream()
                .filter(b -> onlyCategory.equalsIgnoreCase(b.getCategory()))
                .collect(Collectors.toList());
    }
}