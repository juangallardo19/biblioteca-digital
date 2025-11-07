package biblioteca.models;

public class AudioBook extends Book {
    public AudioBook() {
        super();
        setFormat("AUDIO");
        setAvailable(true);
    }
}