package biblioteca.patterns;

import biblioteca.models.User;

public class NotificationService {
    public void notify(User user, String message) {
        System.out.println("Notification to " + (user != null ? user.getName() : "user") + ": " + message);
    }
}