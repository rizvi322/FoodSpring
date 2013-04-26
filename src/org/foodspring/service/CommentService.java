package org.foodspring.service;

import org.foodspring.domain.Comment;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: azim
 * Date: 3/25/13
 * Time: 9:58 AM
 * To change this template use File | Settings | File Templates.
 */
public interface CommentService {

    public void addComment(Comment comment);

    public List<Comment> showComments();

}
