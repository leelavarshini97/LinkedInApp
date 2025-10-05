package org.linkedin.application.message;

import lombok.Getter;
import lombok.Setter;
import org.linkedin.application.user.User;

@Getter
@Setter
public class Message {
    User user;
    int id;
    public Message(int id, User user) {
        this.user = user;
        this.id = id;
    }
}
