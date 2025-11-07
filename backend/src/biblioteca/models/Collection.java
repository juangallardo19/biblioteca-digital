package biblioteca.models;

import java.time.LocalDate;
import java.util.List;

public class Collection {
    private String name;
    private List<Book> books;
    private String description;
    private LocalDate createdDate;

    public Collection(String name, List<Book> books, String description, LocalDate createdDate) {
        this.name = name;
        this.books = books;
        this.description = description;
        this.createdDate = createdDate;
    }

    public String getName() { return name; }
    public List<Book> getBooks() { return books; }
    public String getDescription() { return description; }
    public LocalDate getCreatedDate() { return createdDate; }

    @Override
    public String toString() {
        return "Collection{" +
                "name='" + name + '\'' +
                ", books=" + (books != null ? books.size() : 0) +
                ", description='" + description + '\'' +
                ", createdDate=" + createdDate +
                '}';
    }
}