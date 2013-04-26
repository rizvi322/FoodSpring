package org.foodspring.dao;

import org.apache.log4j.Logger;
import org.foodspring.domain.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: azim
 * Date: 3/24/13
 * Time: 5:45 PM
 * To change this template use File | Settings | File Templates.
 */

@Repository
public class CommentDaoImpl implements CommentDao {

    static Logger log = Logger.getLogger(FoodDaoImpl.class.getName());


    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setJdbcTemplate(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public void addComment(Comment comment) {

        String sql = "INSERT INTO comments(`name`,`comment`,`comment_time`) VALUES(?, ?, ?)";
        int count = this.jdbcTemplate.update(sql, comment.getName(), comment.getComment(), comment.getTime());
        log.info(new Date() + " : Successfully added new comment.");
    }

    @Override
    public List<Comment> showComments() {
        List<Comment> comments = new ArrayList<Comment>();
        String sql = "SELECT * FROM comments ORDER BY id DESC LIMIT 5";
        comments = this.jdbcTemplate.query(sql, new RowMapper<Comment>() {

            @Override
            public Comment mapRow(ResultSet resultSet, int rowNum) throws SQLException {

                Comment comment = new Comment();
                comment.setName(resultSet.getString("name"));
                comment.setComment(resultSet.getString("comment"));
                comment.setTime(resultSet.getString("comment_time"));

                return comment;
            }
        });

        if (comments.size() != 0) {
            log.info(new Date() + " : Successfully returned the list of comments.");
            return comments;
        }
        log.info(new Date() + " : comments list is empty.");
        return null;
    }
}
