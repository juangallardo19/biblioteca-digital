package biblioteca.models;

public class Book {
    private int id;
    private String title;
    private String author;
    private String genre;
    private boolean available;
    private String format;

    public Book() {}

    public Book(int id, String title, String author, String genre, boolean available, String format) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.available = available;
        this.format = format;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; }

    public String getGenre() { return genre; }
    public void setGenre(String genre) { this.genre = genre; }

    public boolean isAvailable() { return available; }
    public void setAvailable(boolean available) { this.available = available; }

    public String getFormat() { return format; }
    public void setFormat(String format) { this.format = format; }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", genre='" + genre + '\'' +
                ", available=" + available +
                ", format='" + format + '\'' +
                '}';
    }
}