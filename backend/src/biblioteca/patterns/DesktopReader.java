package biblioteca.patterns;

/**
 * DesktopReader - Concrete implementation of AbstractReader
 * Uses Template Method pattern - only needs to define the reading prefix
 */
public class DesktopReader extends AbstractReader {
    @Override
    protected String getReadingPrefix() {
        return "Reading on desktop";
    }
}
