package org.linkedin.application.notification;

import java.util.ArrayList;
import java.util.List;

public class Observable {
    List<Observer> observers = new ArrayList<>();

    public void addObserver(Observer observer) {
        this.observers.add(observer);
    }

    public void removeObserver(Observer observer){
        this.observers.remove(observer);
    }

    public void notifyObserver(Object content){
        for(Observer observer : this.observers) {
            observer.update(content);
        }
    }

}
