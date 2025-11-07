package biblioteca.patterns;

import biblioteca.models.Book;
import biblioteca.utils.StringUtils;

/**
 * AbstractReader - Template Method Pattern
 * Base class for all readers, eliminates duplicated code in Reader implementations
 *
 * Template Method: read() defines the algorithm skeleton
 * Hook Method: getReadingPrefix() is implemented by subclasses
 */
public abstract class AbstractReader implements Reader {

    /**
     * Template method - defines the algorithm structure
     * This eliminates the duplicated read() logic across 4 Reader classes
     */
    @Override
    public final String read(Book book) {
        String title = StringUtils.safeGetTitle(book);
        return getReadingPrefix() + ": " + title;
    }

    /**
     * Hook method - implemented by concrete readers
     * Each subclass only needs to define its specific prefix
     */
    protected abstract String getReadingPrefix();
}
