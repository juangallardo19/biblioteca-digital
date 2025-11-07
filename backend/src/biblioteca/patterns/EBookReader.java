package biblioteca.patterns;

import biblioteca.models.Book;

public class EBookReader implements Reader {
    @Override
    public String read(Book book) {
        return "Leyendo eBook: " + (book != null ? book.getTitle() : "(sin título)");
    }
}