package biblioteca.patterns;

import biblioteca.models.Book;

public class DesktopReader implements Reader {
    @Override
    public String read(Book book) {
        return "Reading on desktop: " + (book != null ? book.getTitle() : "(no title)");
    }
}