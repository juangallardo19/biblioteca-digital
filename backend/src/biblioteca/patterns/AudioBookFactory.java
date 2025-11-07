package biblioteca.patterns;

import biblioteca.models.AudioBook;
import biblioteca.models.Book;

public class AudioBookFactory implements ContentFactory {
    @Override
    public Book createBook() { return new AudioBook(); }

    @Override
    public Reader createReader() { return new AudioPlayer(); }
}