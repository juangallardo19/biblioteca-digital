package biblioteca.models;

/**
 * AudioBook - Book subclass for audio format
 * Uses protected constructor to eliminate duplicated initialization code
 */
public class AudioBook extends Book {
    public AudioBook() {
        super("AUDIO");
    }
}
