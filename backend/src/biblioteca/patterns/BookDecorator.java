package biblioteca.patterns;

// [DECORATOR]
interface BookComponent {
    String getContent();
}

class BasicBook implements BookComponent {
    @Override
    public String getContent() {
        return "Contenido del libro";
    }
}

class TranslatorDecorator implements BookComponent {
    private final BookComponent book;

    public TranslatorDecorator(BookComponent book) {
        this.book = book;
    }

    @Override
    public String getContent() {
        return "[TRADUCIDO] " + book.getContent();
    }
}