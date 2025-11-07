package biblioteca.models;

public class EBook extends Book {
    public EBook() {
        super();
        setFormat("EBOOK");
        setAvailable(true);
    }
}