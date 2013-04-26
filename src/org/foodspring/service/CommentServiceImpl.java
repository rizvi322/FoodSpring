package org.foodspring.service;

import org.foodspring.dao.CommentDao;
import org.foodspring.domain.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: azim
 * Date: 3/25/13
 * Time: 9:58 AM
 * To change this template use File | Settings | File Templates.
 */

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentDao commentDao;

    @Override
    public void addComment(Comment comment) {

        commentDao.addComment(comment);
    }

    @Override
    public List<Comment> showComments() {

        return commentDao.showComments();
    }

    public CommentDao getCommentDao() {
        return commentDao;
    }

    public void setCommentDao(CommentDao commentDao) {
        this.commentDao = commentDao;
    }
}
