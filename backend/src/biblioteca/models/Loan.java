package biblioteca.models;

import java.time.LocalDate;

public class Loan {
    private int id;
    private User user;
    private Book book;
    private LocalDate loanDate;
    private LocalDate returnDate;
    private boolean active;

    public Loan(int id, User user, Book book, LocalDate loanDate, LocalDate returnDate, boolean active) {
        this.id = id;
        this.user = user;
        this.book = book;
        this.loanDate = loanDate;
        this.returnDate = returnDate;
        this.active = active;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    public Book getBook() { return book; }
    public void setBook(Book book) { this.book = book; }

    public LocalDate getLoanDate() { return loanDate; }
    public void setLoanDate(LocalDate loanDate) { this.loanDate = loanDate; }

    public LocalDate getReturnDate() { return returnDate; }
    public void setReturnDate(LocalDate returnDate) { this.returnDate = returnDate; }

    public boolean isActive() { return active; }
    public void setActive(boolean active) { this.active = active; }

    @Override
    public String toString() {
        return "Loan{" +
                "id=" + id +
                ", user=" + (user != null ? user.getName() : "null") +
                ", book=" + (book != null ? book.getTitle() : "null") +
                ", loanDate=" + loanDate +
                ", returnDate=" + returnDate +
                ", active=" + active +
                '}';
    }
}