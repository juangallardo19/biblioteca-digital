package biblioteca.patterns;

import biblioteca.models.User;

public class NotificationService {
    public void notify(User user, String message) {
        System.out.println("Notificación a " + (user != null ? user.getName() : "usuario") + ": " + message);
    }
}