package biblioteca.patterns;

import biblioteca.models.Book;

public class AudioPlayer implements Reader {
    @Override
    public String read(Book book) {
        return "Playing audiobook: " + (book != null ? book.getTitle() : "(no title)");
    }
}