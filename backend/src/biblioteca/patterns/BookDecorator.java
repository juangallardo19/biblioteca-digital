package biblioteca.patterns;

// [DECORATOR PATTERN]
// Allows adding functionalities dynamically to books

interface BookComponent {
    String getContent();
}

class BasicBook implements BookComponent {
    @Override
    public String getContent() {
        return "Book content";
    }
}

class TranslatorDecorator implements BookComponent {
    private final BookComponent book;

    public TranslatorDecorator(BookComponent book) {
        this.book = book;
    }

    @Override
    public String getContent() {
        return "[TRANSLATED] " + book.getContent();
    }
}

class DictionaryDecorator implements BookComponent {
    private final BookComponent book;

    public DictionaryDecorator(BookComponent book) {
        this.book = book;
    }

    @Override
    public String getContent() {
        return "[WITH DICTIONARY] " + book.getContent();
    }
}

class HighlightDecorator implements BookComponent {
    private final BookComponent book;

    public HighlightDecorator(BookComponent book) {
        this.book = book;
    }

    @Override
    public String getContent() {
        return "[HIGHLIGHTED] " + book.getContent();
    }
}
