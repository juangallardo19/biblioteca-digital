package biblioteca.patterns;

import biblioteca.data.DataStore;
import biblioteca.models.Book;
import biblioteca.models.Loan;
import biblioteca.models.User;

// [FACADE]
public class LibraryFacade {
    private final DataStore data;
    private final NotificationService notif;

    public LibraryFacade(DataStore data) {
        this.data = data;
        this.notif = new NotificationService();
    }

    public void borrowBook(User user, Book book) {
        if (data.isAvailable(book)) {
            Loan loan = data.createLoan(user, book);
            notif.notify(user, "Libro prestado!");
        }
    }

    public void returnBook(Loan loan) {
        data.closeLoan(loan);
        notif.notify(loan.getUser(), "Libro devuelto!");
    }
}