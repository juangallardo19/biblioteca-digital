package biblioteca.patterns;

import java.util.ArrayList;
import java.util.List;

public class Subject {
    private final List<Observer> observers = new ArrayList<>();

    public void attach(Observer obs) { observers.add(obs); }

    public void notifyObservers(String msg) {
        for (Observer obs : observers) {
            obs.update(msg);
        }
    }
}