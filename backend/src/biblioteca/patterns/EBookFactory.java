package biblioteca.patterns;

import biblioteca.models.Book;
import biblioteca.models.EBook;

public class EBookFactory implements ContentFactory {
    @Override
    public Book createBook() { return new EBook(); }

    @Override
    public Reader createReader() { return new EBookReader(); }
}