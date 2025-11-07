package biblioteca.patterns;

import biblioteca.models.User;
import biblioteca.utils.StringUtils;

public class NotificationService {
    public void notify(User user, String message) {
        System.out.println("Notification to " + StringUtils.safeGetUserName(user) + ": " + message);
    }
}
