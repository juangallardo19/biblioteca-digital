package biblioteca.patterns;

/**
 * MobileReader - Concrete implementation of AbstractReader
 * Uses Template Method pattern - only needs to define the reading prefix
 */
public class MobileReader extends AbstractReader {
    @Override
    protected String getReadingPrefix() {
        return "Reading on mobile";
    }
}
