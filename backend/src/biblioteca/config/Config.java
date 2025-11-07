package biblioteca.config;

/**
 * Config - Singleton Pattern
 *
 * This pattern ensures that a single shared instance of the class
 * exists throughout the entire application.
 *
 * Key features:
 * - Private constructor: prevents creating instances from outside.
 * - Static getInstance() method: returns the single instance.
 * - Thread-safe implementation with double-checked locking.
 *
 * Usage example:
 *   Config config = Config.getInstance();
 *   System.out.println(config.getLibraryName());
 */
public class Config {
    private static volatile Config instance;

    // Singleton attributes with initial values
    private String libraryName = "Digital Library";
    private int maxLoans = 3;
    private boolean drmEnabled = true;

    // Private constructor
    private Config() {}

    // Returns the single instance
    public static Config getInstance() {
        if (instance == null) {
            synchronized (Config.class) {
                if (instance == null) {
                    instance = new Config();
                }
            }
        }
        return instance;
    }

    // Getters and setters
    public String getLibraryName() { return libraryName; }
    public void setLibraryName(String libraryName) { this.libraryName = libraryName; }

    public int getMaxLoans() { return maxLoans; }
    public void setMaxLoans(int maxLoans) { this.maxLoans = maxLoans; }

    public boolean isDrmEnabled() { return drmEnabled; }
    public void setDrmEnabled(boolean drmEnabled) { this.drmEnabled = drmEnabled; }
}