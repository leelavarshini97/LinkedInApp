package org.linkedin.application.post;

import lombok.Getter;
import lombok.Setter;
import org.linkedin.application.notification.Observable;

public abstract class Post extends Observable {
    @Getter
    @Setter
    int id;

    public String printDetails(){
        return this.getDetails();
    }

    public abstract String getDetails();

}
