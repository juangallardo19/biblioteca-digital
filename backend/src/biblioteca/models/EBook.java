package biblioteca.models;

/**
 * EBook - Book subclass for digital book format
 * Uses protected constructor to eliminate duplicated initialization code
 */
public class EBook extends Book {
    public EBook() {
        super("EBOOK");
    }
}
