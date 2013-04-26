package org.foodspring.service;

import org.foodspring.dao.MealDao;
import org.foodspring.domain.AssignMeal;
import org.foodspring.domain.Meal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: azim
 * Date: 4/1/13
 * Time: 10:14 AM
 * To change this template use File | Settings | File Templates.
 */

@Service
public class MealServiceImpl implements MealService {

    @Autowired
    private MealDao mealDao;

    @Override
    public void add(Meal meal) {

        mealDao.add(meal);
    }

    @Override
    public List<Meal> showAll() {

        return mealDao.showAll();
    }

    @Override
    public void remove(int id) {
        mealDao.remove(id);
    }

    @Override
    public void assignMeal(AssignMeal assignMeal) {
        mealDao.assignMeal(assignMeal);
    }

    @Override
    public List<AssignMeal> showAssignedMeal() {

        return mealDao.showAssignedMeal();
    }

    @Override
    public void removeAssign(int id) {

        mealDao.removeAssign(id);
    }

    public MealDao getMealDao() {
        return mealDao;
    }

    public void setMealDao(MealDao mealDao) {
        this.mealDao = mealDao;
    }
}
