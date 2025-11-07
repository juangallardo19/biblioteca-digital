package biblioteca.patterns;

import biblioteca.models.Book;

public class MobileReader implements Reader {
    @Override
    public String read(Book book) {
        return "Leyendo en móvil: " + (book != null ? book.getTitle() : "(sin título)");
    }
}