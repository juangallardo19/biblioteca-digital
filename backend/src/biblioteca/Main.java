package biblioteca;

import biblioteca.config.Config;
import biblioteca.data.DataStore;
import biblioteca.models.Book;
import biblioteca.models.User;
import biblioteca.patterns.LibraryFacade;

/**
 * Main - Entry point for the Digital Library application
 * Demonstrates all 10 Gang of Four design patterns
 */
public class Main {
    public static void main(String[] args) {
        printBanner();

        // SINGLETON PATTERN - Config
        Config config = Config.getInstance();
        System.out.println("\n=== SINGLETON PATTERN: Config ===");
        System.out.println("Library: " + config.getLibraryName());
        System.out.println("Max loans: " + config.getMaxLoans());
        System.out.println("DRM enabled: " + config.isDrmEnabled());

        // SINGLETON PATTERN - DataStore
        DataStore db = DataStore.getInstance();
        System.out.println("\n=== Data Store initialized ===");

        // FACADE PATTERN - Library operations
        LibraryFacade facade = new LibraryFacade(db);
        System.out.println("\n=== FACADE PATTERN: Library Facade ===");

        // Create sample data for demonstration
        Book book = new Book(100, "Don Quixote", "Miguel de Cervantes", "Fiction", true, "EPUB");
        User user = new User(100, "Ana", "ana@example.com", "BASIC", 2);

        db.saveBook(book);
        db.saveUser(user);

        // Test loan creation
        facade.borrowBook(user, book);
        System.out.println("Books loaned to Ana: " + db.getLoansByUserId(user.getId()).size());

        // Start API Server
        System.out.println("\n" + "=".repeat(50));
        System.out.println("🚀 Starting REST API Server...");
        System.out.println("=".repeat(50));

        ApiServer apiServer = new ApiServer();
        apiServer.start(7000);

        System.out.println("\n" + "=".repeat(50));
        System.out.println("📚 Digital Library System Ready!");
        System.out.println("=".repeat(50));
        System.out.println("🌐 Open the frontend in your browser to interact");
        System.out.println("📖 API Documentation:");
        System.out.println("   - GET  /api/config");
        System.out.println("   - GET  /api/books");
        System.out.println("   - GET  /api/users");
        System.out.println("   - POST /api/borrow");
        System.out.println("   - POST /api/return");
        System.out.println("   - GET  /api/loans/:userId");
        System.out.println("   - POST /api/collection");
        System.out.println("   - POST /api/recommend");
        System.out.println("   - GET  /api/reader/:device");
        System.out.println("   - POST /api/decorate");
        System.out.println("=".repeat(50));
    }

    private static void printBanner() {
        System.out.println("\n" + "=".repeat(60));
        System.out.println(" ".repeat(10) + "📚 DIGITAL LIBRARY SYSTEM 📚");
        System.out.println(" ".repeat(5) + "Demonstrating 10 Gang of Four Design Patterns");
        System.out.println("=".repeat(60));
    }
}
