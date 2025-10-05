package org.linkedin.controller.request;
import lombok.Getter;
import lombok.Setter;
import org.linkedin.application.post.*;

@Getter
@Setter
public class PostRequest {
    String type;
    Post post;
    Job job;
    Event event;
    Course course;
    Poll poll;
}
