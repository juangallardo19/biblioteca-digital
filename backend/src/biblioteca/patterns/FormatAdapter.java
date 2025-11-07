package biblioteca.patterns;

import biblioteca.models.Book;

// [ADAPTER]
public class FormatAdapter implements Reader {
    private final LegacyReader legacyReader;

    public FormatAdapter(LegacyReader legacyReader) {
        this.legacyReader = legacyReader;
    }

    @Override
    public String read(Book book) {
        // Adaptamos el libro a un formato de texto plano que entiende LegacyReader
        String legacyContent = book.getTitle() + " - " + book.getAuthor();
        return legacyReader.readText(legacyContent);
    }

    // Servicio legado que no entiende Book directamente
    public static class LegacyReader {
        public String readText(String content) {
            return "[Legacy] " + content;
        }
    }

    // === Prompt 5 ===
    // Interfaz que expone un formato de lectura unificado
    interface BookFormat {
        String read();
    }

    // Simulación de biblioteca externa de PDF
    static class PDFLibrary {
        public String readPDF() {
            return "Leyendo contenido PDF externo";
        }
    }

    // Adaptador que envuelve la librería PDF y la expone como BookFormat
    static class PDFAdapter implements BookFormat {
        private final PDFLibrary pdfLib;

        PDFAdapter() {
            this.pdfLib = new PDFLibrary();
        }

        @Override
        public String read() {
            // Adapta el método externo a nuestra interfaz
            return pdfLib.readPDF();
        }
    }
}