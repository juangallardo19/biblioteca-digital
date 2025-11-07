package biblioteca;

import biblioteca.config.Config;
import biblioteca.data.DataStore;
import biblioteca.models.Book;
import biblioteca.models.User;
import biblioteca.patterns.LibraryFacade;

public class Main {
    public static void main(String[] args) {
        Config config = Config.getInstance();
        System.out.println("Biblioteca: " + config.getLibraryName());
        System.out.println("Max préstamos: " + config.getMaxLoans());
        System.out.println("DRM habilitado: " + config.isDrmEnabled());

        DataStore db = DataStore.getInstance();
        LibraryFacade facade = new LibraryFacade(db);

        Book book = new Book("123", "El Quijote", "Ficción", "Miguel de Cervantes");
        User user = new User("u1", "Ana");

        db.saveBook(book);
        db.saveUser(user);

        boolean loanCreated = facade.loanBook("123", "u1");
        System.out.println("Préstamo creado: " + loanCreated);
        System.out.println("Libros prestados a Ana: " + facade.getLoansByUser("u1").size());
    }
}