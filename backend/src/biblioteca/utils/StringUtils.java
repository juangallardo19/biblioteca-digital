package biblioteca.utils;

import biblioteca.models.Book;
import biblioteca.models.User;

/**
 * StringUtils - Utility class for null-safe string operations
 * Eliminates duplicated null-checking code throughout the application
 */
public class StringUtils {

    /**
     * Get book title safely, returning a default value if null
     */
    public static String safeGetTitle(Book book) {
        return book != null ? book.getTitle() : "(no title)";
    }

    /**
     * Get user name safely, returning a default value if null
     */
    public static String safeGetUserName(User user) {
        return user != null ? user.getName() : "user";
    }

    /**
     * Get string safely with custom default
     */
    public static String safeGet(String value, String defaultValue) {
        return value != null ? value : defaultValue;
    }

    /**
     * Get string safely, returning empty string if null
     */
    public static String safeGet(String value) {
        return safeGet(value, "");
    }
}
