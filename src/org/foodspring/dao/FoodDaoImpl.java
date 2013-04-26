package org.foodspring.dao;

import org.apache.log4j.Logger;
import org.foodspring.domain.Food;
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
 * Time: 4:50 PM
 * To change this template use File | Settings | File Templates.
 */

@Repository
public class FoodDaoImpl implements FoodDao {

    static Logger log = Logger.getLogger(FoodDaoImpl.class.getName());

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setJdbcTemplate(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public void add(Food food) {

        String sql = "INSERT INTO foodlist(`name`,`description`) VALUES(?, ?)";
        this.jdbcTemplate.update(sql, food.getName(), food.getDescription());
        log.info(new Date() + " : Successfully added new food.");
    }

    @Override
    public List<Food> showAll() {

        List<Food> foods = new ArrayList<Food>();
        String sql = "SELECT * FROM foodlist";
        foods = this.jdbcTemplate.query(sql, new RowMapper<Food>() {
            @Override
            public Food mapRow(ResultSet resultSet, int rowNum) throws SQLException {

                Food food = new Food();
                food.setId(resultSet.getInt("id"));
                food.setName(resultSet.getString("name"));
                food.setDescription(resultSet.getString("description"));

                return food;
            }
        });

        if (foods.size() != 0) {
            log.info(new Date() + " : Successfully returned the list of foods.");
            return foods;
        }
        log.info(new Date() + " : Food list is empty.");
        return null;
    }

    @Override
    public void remove(int id) {

        String sql = "DELETE FROM foodlist WHERE id=" + id;
        this.jdbcTemplate.update(sql);
        log.info(new Date() + " : Successfully deleted food.");
    }
}
