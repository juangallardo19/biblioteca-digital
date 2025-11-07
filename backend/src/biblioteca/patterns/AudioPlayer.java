package biblioteca.patterns;

import biblioteca.models.Book;

public class AudioPlayer implements Reader {
    @Override
    public String read(Book book) {
        return "Reproduciendo audiolibro: " + (book != null ? book.getTitle() : "(sin título)");
    }
}