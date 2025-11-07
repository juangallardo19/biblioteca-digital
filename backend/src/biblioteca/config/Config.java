package biblioteca.config;

/**
 * Config - Singleton
 *
 * Este patrón garantiza que exista una única instancia compartida
 * de la clase a lo largo de toda la aplicación.
 *
 * Características clave:
 * - Constructor privado: impide crear instancias desde fuera.
 * - Método estático getInstance(): devuelve la única instancia.
 * - Implementación thread-safe con double-checked locking.
 *
 * Ejemplo de uso:
 *   Config config = Config.getInstance();
 *   System.out.println(config.getLibraryName());
 */
public class Config {
    private static volatile Config instance;

    // Atributos del Singleton con valores iniciales
    private String libraryName = "Biblioteca Digital";
    private int maxLoans = 3;
    private boolean drmEnabled = true;

    // Constructor privado
    private Config() {}

    // Devuelve la única instancia
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

    // Getters y setters
    public String getLibraryName() { return libraryName; }
    public void setLibraryName(String libraryName) { this.libraryName = libraryName; }

    public int getMaxLoans() { return maxLoans; }
    public void setMaxLoans(int maxLoans) { this.maxLoans = maxLoans; }

    public boolean isDrmEnabled() { return drmEnabled; }
    public void setDrmEnabled(boolean drmEnabled) { this.drmEnabled = drmEnabled; }
}