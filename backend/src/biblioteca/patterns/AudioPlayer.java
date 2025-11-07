package biblioteca.patterns;

/**
 * AudioPlayer - Concrete implementation of AbstractReader
 * Uses Template Method pattern - only needs to define the reading prefix
 */
public class AudioPlayer extends AbstractReader {
    @Override
    protected String getReadingPrefix() {
        return "Playing audiobook";
    }
}
