package biblioteca.patterns;

public class DesktopReaderFactory extends ReaderFactory {
    @Override
    public Reader createReader() { return new DesktopReader(); }
}