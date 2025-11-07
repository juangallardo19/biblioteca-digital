package biblioteca.patterns;

import biblioteca.models.Book;

public class MobileReader implements Reader {
    @Override
    public String read(Book book) {
        return "Reading on mobile: " + (book != null ? book.getTitle() : "(no title)");
    }
}