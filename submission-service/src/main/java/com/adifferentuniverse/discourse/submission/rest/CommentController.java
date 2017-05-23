package com.adifferentuniverse.discourse.submission.rest;

import com.adifferentuniverse.discourse.submission.model.Comment;
import com.adifferentuniverse.discourse.submission.model.Rating;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

@RestController
public class CommentController {

    public Rating createComment(Principal principal, Comment comment) {
        return null;
    }
    public Rating updateComment(Principal principal, Comment comment) {
        return null;
    }
    public Rating deleteComment(Principal principal, Comment comment) {
        return null;
    }

    public List<Comment> getCommentsForSubmission(Principal principal, Long submissionId) {
        return null;
    }
}
