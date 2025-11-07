package biblioteca.patterns;

import biblioteca.models.Book;

// [ABSTRACT FACTORY]
public interface ContentFactory {
    Book createBook();
    Reader createReader();
}