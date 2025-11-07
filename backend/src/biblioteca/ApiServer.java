package biblioteca;

import biblioteca.config.Config;
import biblioteca.data.DataStore;
import biblioteca.models.Book;
import biblioteca.models.Collection;
import biblioteca.models.Loan;
import biblioteca.models.User;
import biblioteca.patterns.*;
import com.google.gson.Gson;
import io.javalin.Javalin;
import io.javalin.http.Context;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ApiServer - REST API Server using Javalin
 * Exposes endpoints to demonstrate all 10 Gang of Four design patterns
 */
public class ApiServer {
    private final LibraryFacade libraryFacade;
    private final Gson gson;
    private final DataStore dataStore;

    public ApiServer() {
        this.dataStore = DataStore.getInstance();
        this.libraryFacade = new LibraryFacade(dataStore);
        this.gson = new Gson();

        // Initialize sample data
        initializeSampleData();
    }

    /**
     * Start the API server on the specified port
     */
    public void start(int port) {
        Javalin app = Javalin.create(config -> {
            // Enable CORS for frontend access
            config.plugins.enableCors(cors -> {
                cors.add(it -> {
                    it.anyHost();
                });
            });
        });

        // === SINGLETON PATTERN ===
        // GET /api/config - Returns global configuration
        app.get("/api/config", ctx -> {
            Config config = Config.getInstance();
            Map<String, Object> response = new HashMap<>();
            response.put("libraryName", config.getLibraryName());
            response.put("maxLoans", config.getMaxLoans());
            response.put("drmEnabled", config.isDrmEnabled());
            ctx.json(response);
        });

        // === CATALOG ENDPOINTS ===
        // GET /api/books - Returns all books
        app.get("/api/books", ctx -> {
            List<Book> books = dataStore.getAllBooks();
            ctx.json(books);
        });

        // GET /api/users - Returns all users
        app.get("/api/users", ctx -> {
            List<User> users = dataStore.getAllUsers();
            ctx.json(users);
        });

        // === FACADE PATTERN ===
        // POST /api/borrow - Borrow a book
        app.post("/api/borrow", ctx -> {
            try {
                DTOs.BorrowRequest request = ctx.bodyAsClass(DTOs.BorrowRequest.class);
                User user = dataStore.getUser(request.getUserId());
                Book book = dataStore.getBook(request.getBookId());

                if (user == null) {
                    ctx.json(DTOs.ApiResponse.error("User not found"));
                    return;
                }
                if (book == null) {
                    ctx.json(DTOs.ApiResponse.error("Book not found"));
                    return;
                }
                if (!book.isAvailable()) {
                    ctx.json(DTOs.ApiResponse.error("Book is not available"));
                    return;
                }

                libraryFacade.borrowBook(user, book);
                List<Loan> userLoans = dataStore.getLoansByUserId(user.getId());
                Loan latestLoan = userLoans.get(userLoans.size() - 1);

                ctx.json(DTOs.ApiResponse.success("Book borrowed successfully", latestLoan));
            } catch (Exception e) {
                ctx.json(DTOs.ApiResponse.error("Error borrowing book: " + e.getMessage()));
            }
        });

        // POST /api/return - Return a book
        app.post("/api/return", ctx -> {
            try {
                DTOs.ReturnRequest request = ctx.bodyAsClass(DTOs.ReturnRequest.class);
                Loan loan = dataStore.getLoanById(request.getLoanId());

                if (loan == null) {
                    ctx.json(DTOs.ApiResponse.error("Loan not found"));
                    return;
                }
                if (!loan.isActive()) {
                    ctx.json(DTOs.ApiResponse.error("Loan is already closed"));
                    return;
                }

                libraryFacade.returnBook(loan);
                ctx.json(DTOs.ApiResponse.success("Book returned successfully", loan));
            } catch (Exception e) {
                ctx.json(DTOs.ApiResponse.error("Error returning book: " + e.getMessage()));
            }
        });

        // GET /api/loans/:userId - Get active loans for a user
        app.get("/api/loans/{userId}", ctx -> {
            try {
                int userId = Integer.parseInt(ctx.pathParam("userId"));
                List<Loan> loans = dataStore.getLoansByUserId(userId);
                ctx.json(loans);
            } catch (Exception e) {
                ctx.json(DTOs.ApiResponse.error("Error getting loans: " + e.getMessage()));
            }
        });

        // === BUILDER PATTERN ===
        // POST /api/collection - Create a custom collection
        app.post("/api/collection", ctx -> {
            try {
                DTOs.CollectionRequest request = ctx.bodyAsClass(DTOs.CollectionRequest.class);

                CollectionBuilder builder = new CollectionBuilder();
                List<Book> selectedBooks = new ArrayList<>();

                for (Integer bookId : request.getBookIds()) {
                    Book book = dataStore.getBook(bookId);
                    if (book != null) {
                        builder.add(book);
                        selectedBooks.add(book);
                    }
                }

                Collection collection = new Collection(
                    request.getName(),
                    selectedBooks,
                    request.getDescription(),
                    LocalDate.now()
                );

                ctx.json(DTOs.ApiResponse.success("Collection created successfully", collection));
            } catch (Exception e) {
                ctx.json(DTOs.ApiResponse.error("Error creating collection: " + e.getMessage()));
            }
        });

        // === STRATEGY PATTERN ===
        // POST /api/recommend - Get book recommendations
        app.post("/api/recommend", ctx -> {
            try {
                DTOs.RecommendRequest request = ctx.bodyAsClass(DTOs.RecommendRequest.class);
                User user = dataStore.getUser(request.getUserId());

                if (user == null) {
                    ctx.json(DTOs.ApiResponse.error("User not found"));
                    return;
                }

                RecommendationStrategy strategy;
                String strategyType = request.getStrategy().toUpperCase();

                switch (strategyType) {
                    case "GENRE":
                        strategy = new GenreStrategy(dataStore, "Fiction");
                        break;
                    case "POPULARITY":
                        strategy = new PopularStrategy(dataStore, 5);
                        break;
                    case "AUTHOR":
                        // For author strategy, use first book's author from user's loans
                        List<Loan> loans = dataStore.getLoansByUserId(user.getId());
                        String author = loans.isEmpty() ? "Unknown" : loans.get(0).getBook().getAuthor();
                        strategy = new PopularStrategy(dataStore, 3); // Fallback to popular
                        break;
                    default:
                        strategy = new PopularStrategy(dataStore, 5);
                }

                List<Book> recommendations = strategy.recommend(user);
                ctx.json(DTOs.ApiResponse.success("Recommendations generated", recommendations));
            } catch (Exception e) {
                ctx.json(DTOs.ApiResponse.error("Error generating recommendations: " + e.getMessage()));
            }
        });

        // === FACTORY METHOD PATTERN ===
        // GET /api/reader/:device - Get reader info by device type
        app.get("/api/reader/{device}", ctx -> {
            try {
                String deviceType = ctx.pathParam("device").toLowerCase();
                ReaderFactory factory;

                switch (deviceType) {
                    case "mobile":
                        factory = new MobileReaderFactory();
                        break;
                    case "desktop":
                        factory = new DesktopReaderFactory();
                        break;
                    case "tablet":
                        factory = new DesktopReaderFactory(); // Use desktop as fallback for tablet
                        break;
                    default:
                        ctx.json(DTOs.ApiResponse.error("Unknown device type"));
                        return;
                }

                Reader reader = factory.createReader();
                Book sampleBook = dataStore.getAllBooks().isEmpty() ? null : dataStore.getAllBooks().get(0);
                String readerInfo = reader.read(sampleBook);

                Map<String, String> response = new HashMap<>();
                response.put("device", deviceType);
                response.put("info", readerInfo);

                ctx.json(DTOs.ApiResponse.success("Reader created", response));
            } catch (Exception e) {
                ctx.json(DTOs.ApiResponse.error("Error creating reader: " + e.getMessage()));
            }
        });

        // === DECORATOR PATTERN ===
        // POST /api/decorate - Apply decorators to book content
        app.post("/api/decorate", ctx -> {
            try {
                DTOs.DecorateRequest request = ctx.bodyAsClass(DTOs.DecorateRequest.class);
                Book book = dataStore.getBook(request.getBookId());

                if (book == null) {
                    ctx.json(DTOs.ApiResponse.error("Book not found"));
                    return;
                }

                BookComponent content = new BasicBook();

                // Apply decorators in order
                for (String decorator : request.getDecorators()) {
                    switch (decorator.toUpperCase()) {
                        case "TRANSLATOR":
                            content = new TranslatorDecorator(content);
                            break;
                        case "DICTIONARY":
                            content = new DictionaryDecorator(content);
                            break;
                        case "HIGHLIGHT":
                            content = new HighlightDecorator(content);
                            break;
                    }
                }

                Map<String, Object> response = new HashMap<>();
                response.put("bookId", book.getId());
                response.put("title", book.getTitle());
                response.put("decoratedContent", content.getContent());
                response.put("decoratorsApplied", request.getDecorators());

                ctx.json(DTOs.ApiResponse.success("Decorators applied", response));
            } catch (Exception e) {
                ctx.json(DTOs.ApiResponse.error("Error applying decorators: " + e.getMessage()));
            }
        });

        // Start server
        app.start(port);
        System.out.println("🚀 API Server started on http://localhost:" + port);
        System.out.println("📖 Endpoints available:");
        System.out.println("   GET  /api/config");
        System.out.println("   GET  /api/books");
        System.out.println("   GET  /api/users");
        System.out.println("   POST /api/borrow");
        System.out.println("   POST /api/return");
        System.out.println("   GET  /api/loans/:userId");
        System.out.println("   POST /api/collection");
        System.out.println("   POST /api/recommend");
        System.out.println("   GET  /api/reader/:device");
        System.out.println("   POST /api/decorate");
    }

    /**
     * Initialize sample data for demonstration
     */
    private void initializeSampleData() {
        // Sample books
        Book book1 = new Book(1, "1984", "George Orwell", "Fiction", true, "EPUB");
        Book book2 = new Book(2, "One Hundred Years of Solitude", "Gabriel García Márquez", "Fiction", true, "PDF");
        Book book3 = new Book(3, "The Little Prince", "Antoine de Saint-Exupéry", "Fiction", true, "EPUB");
        Book book4 = new Book(4, "Don Quixote", "Miguel de Cervantes", "Fiction", true, "PDF");
        Book book5 = new Book(5, "Harry Potter and the Philosopher's Stone", "J.K. Rowling", "Fantasy", true, "EPUB");

        dataStore.saveBook(book1);
        dataStore.saveBook(book2);
        dataStore.saveBook(book3);
        dataStore.saveBook(book4);
        dataStore.saveBook(book5);

        // Sample users
        User user1 = new User(1, "Juan", "juan@example.com", "BASIC", 2);
        User user2 = new User(2, "Maria", "maria@example.com", "PREMIUM", 5);

        dataStore.saveUser(user1);
        dataStore.saveUser(user2);

        System.out.println("✅ Sample data initialized:");
        System.out.println("   - 5 books added");
        System.out.println("   - 2 users added");
    }
}
