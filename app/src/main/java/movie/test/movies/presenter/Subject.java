package movie.test.movies.presenter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by muhammad ali
 * on 16/05/2016.
 */
public class Subject {
    private static Subject ourInstance = new Subject();
    private List<Observer> observers = new ArrayList<>();


    public static Subject getInstance() {
        return ourInstance;
    }

    private Subject() {
    }


    public void attach(Observer observer) {
        observers.add(observer);
    }


    public void notifyAllObserver(Object object) {
        for (Observer observer : observers) {
            observer.update(object);
        }
    }

}
