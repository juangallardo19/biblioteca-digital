package biblioteca.patterns;

import biblioteca.models.Book;

public class EBookReader implements Reader {
    @Override
    public String read(Book book) {
        return "Reading eBook: " + (book != null ? book.getTitle() : "(no title)");
    }
}