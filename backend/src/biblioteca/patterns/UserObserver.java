package biblioteca.patterns;

public class UserObserver implements Observer {
    @Override
    public void update(String msg) {
        System.out.println("Notificación: " + msg);
    }
}