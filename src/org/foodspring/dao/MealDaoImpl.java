package org.foodspring.dao;

import org.apache.log4j.Logger;
import org.foodspring.domain.AssignMeal;
import org.foodspring.domain.Meal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: azim
 * Date: 4/1/13
 * Time: 9:45 AM
 * To change this template use File | Settings | File Templates.
 */

@Repository
public class MealDaoImpl implements MealDao {

    private static Logger log = Logger.getLogger(MealDaoImpl.class.getName());


    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setJdbcTemplate(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public void add(Meal meal) {

        String sql = "INSERT INTO meallist(`items`) VALUES(?)";
        this.jdbcTemplate.update(sql, meal.getItems());
        log.info(new Date() + " : Successfully added new meal.");
    }

    @Override
    public List<Meal> showAll() {
        List<Meal> meals = new ArrayList<Meal>();
        String for_date = new SimpleDateFormat("dd-MMM-yy").format(new Date());

        String sql = "SELECT * FROM meallist";

        meals = this.jdbcTemplate.query(sql, new RowMapper<Meal>() {
            @Override
            public Meal mapRow(ResultSet resultSet, int rowNum) throws SQLException {

                Meal meal = new Meal();
                meal.setId(resultSet.getInt("id"));
                meal.setItems(resultSet.getString("items"));
                return meal;
            }
        });

        if (meals.size() != 0) {
            log.info(new Date() + " : Successfully returned the list of meal packages.");
            return meals;
        }
        log.info(new Date() + " : Meal package list is empty.");
        return null;
    }

    @Override
    public void remove(int id) {

        String sql = "DELETE FROM meallist WHERE id=" + id;
        this.jdbcTemplate.update(sql);
        log.info(new Date() + " : Successfully deleted meal.");
    }

    @Override
    public void assignMeal(AssignMeal mealAssign) {

        String sql = "INSERT INTO assign_meal(`meal_id`,`meal_time`,`description`,`for_date`) VALUES(?, ?, ?, ?)";
        this.jdbcTemplate.update(sql, mealAssign.getMeal_id(), mealAssign.getMeal_time(), mealAssign.getDescription(), mealAssign.getFor_date());
        log.info(new Date() + " : Successfully assigned a meal.");
    }

    @Override
    public List<AssignMeal> showAssignedMeal() {

        List<AssignMeal> mealAssigns = new ArrayList<AssignMeal>();
        String for_date = new SimpleDateFormat("dd-MMM-yy").format(new Date());

        String sql = "SELECT a.id, m.items, a.meal_time, a.description ";
        sql += "FROM assign_meal a JOIN meallist m ";
        sql += "ON(a.meal_id = m.id) ";
        sql += "WHERE a.for_date='" + for_date + "' ";
        sql += "ORDER BY a.id DESC";

        mealAssigns = this.jdbcTemplate.query(sql, new RowMapper<AssignMeal>() {
            @Override
            public AssignMeal mapRow(ResultSet resultSet, int rowNum) throws SQLException {

                AssignMeal mealAssign = new AssignMeal();
                mealAssign.setId(resultSet.getInt("id"));
                mealAssign.setMeal_items(resultSet.getString("items"));
                mealAssign.setMeal_time(resultSet.getString("meal_time"));
                mealAssign.setDescription(resultSet.getString("description"));
                return mealAssign;
            }
        });

        if (mealAssigns.size() != 0) {
            log.info(new Date() + " : Successfully returned the list of meals for the day.");
            return mealAssigns;
        }
        log.info(new Date() + " : Meal list is empty.");
        return null;
    }

    @Override
    public void removeAssign(int id) {
        String sql = "DELETE FROM assign_meal WHERE id=" + id;
        this.jdbcTemplate.update(sql);
        log.info(new Date() + " : Successfully deleted assigned meal.");
    }
}
