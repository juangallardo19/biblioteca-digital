package biblioteca.patterns;

import biblioteca.models.Book;

public class DesktopReader implements Reader {
    @Override
    public String read(Book book) {
        return "Leyendo en escritorio: " + (book != null ? book.getTitle() : "(sin título)");
    }
}