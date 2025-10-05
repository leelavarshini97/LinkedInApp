package org.linkedin.application.request;

import lombok.Getter;
import lombok.Setter;
import org.linkedin.application.user.User;

@Getter
@Setter
public class Request {
    RequestStatus status;
    User from;
    User to;

    public Request(User from, User to){
        this.from = from;
        this.to = to;
        this.status = RequestStatus.PENDING;
    }
    public RequestStatus acceptRequest(){
        this.status = RequestStatus.ACCEPTED;
        return this.status;
    }

    public RequestStatus declineRequest(){
        this.status = RequestStatus.DECLINED;
        return this.status;
    }

}
