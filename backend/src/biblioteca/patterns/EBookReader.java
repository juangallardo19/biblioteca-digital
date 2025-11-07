package biblioteca.patterns;

/**
 * EBookReader - Concrete implementation of AbstractReader
 * Uses Template Method pattern - only needs to define the reading prefix
 */
public class EBookReader extends AbstractReader {
    @Override
    protected String getReadingPrefix() {
        return "Reading eBook";
    }
}
