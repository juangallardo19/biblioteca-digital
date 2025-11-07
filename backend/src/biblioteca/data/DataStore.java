package biblioteca.data;

import biblioteca.models.Book;
import biblioteca.models.Loan;
import biblioteca.models.User;

import java.time.LocalDate;
import java.util.*;

// Simulación de BD en memoria
public class DataStore {
    private static volatile DataStore instance;
    private final Map<Integer, Book> books = new HashMap<>();
    private final Map<Integer, User> users = new HashMap<>();
    private final List<Loan> loans = new ArrayList<>();

    private DataStore() {}

    public static DataStore getInstance() {
        if (instance == null) {
            synchronized (DataStore.class) {
                if (instance == null) instance = new DataStore();
            }
        }
        return instance;
    }

    // Persistencia básica
    public void saveBook(Book book) { books.put(book.getId(), book); }
    public void saveUser(User user) { users.put(user.getId(), user); }

    public Book getBook(int id) { return books.get(id); }
    public User getUser(int id) { return users.get(id); }

    // Métodos de compatibilidad con código previo (IDs como String)
    public Book getBook(String id) { return books.get(parse(id)); }
    public User getUser(String id) { return users.get(parse(id)); }

    public List<Book> getAllBooks() { return new ArrayList<>(books.values()); }

    private int parse(String id) {
        try { return Integer.parseInt(id); } catch (Exception e) { return -1; }
    }

    // API requerida por la fachada
    public boolean isAvailable(Book book) {
        return book != null && book.isAvailable();
    }

    public Loan createLoan(User user, Book book) {
        if (user == null || book == null || !isAvailable(book)) return null;
        int newId = loans.size() + 1;
        Loan loan = new Loan(newId, user, book, LocalDate.now(), null, true);
        loans.add(loan);
        book.setAvailable(false);
        return loan;
    }

    public void closeLoan(Loan loan) {
        if (loan == null) return;
        loan.setActive(false);
        loan.setReturnDate(LocalDate.now());
        Book b = loan.getBook();
        if (b != null) b.setAvailable(true);
    }

    // Compatibilidad con método previo basado en IDs String
    public Loan createLoan(String bookId, String userId) {
        Book book = getBook(bookId);
        User user = getUser(userId);
        return createLoan(user, book);
    }

    public List<Loan> getLoansByUser(String userId) {
        int uid = parse(userId);
        List<Loan> result = new ArrayList<>();
        for (Loan l : loans) {
            if (l.getUser() != null && l.getUser().getId() == uid) result.add(l);
        }
        return result;
    }
}