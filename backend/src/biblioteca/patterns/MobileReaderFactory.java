package biblioteca.patterns;

public class MobileReaderFactory extends ReaderFactory {
    @Override
    public Reader createReader() { return new MobileReader(); }
}