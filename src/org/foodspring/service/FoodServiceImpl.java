package org.foodspring.service;

import org.foodspring.dao.FoodDao;
import org.foodspring.domain.Food;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: azim
 * Date: 3/22/13
 * Time: 10:26 AM
 * To change this template use File | Settings | File Templates.
 */

@Service
public class FoodServiceImpl implements FoodService {

    @Autowired
    private FoodDao foodDao;

    @Override
    public void add(Food food) {
        foodDao.add(food);
    }

    @Override
    public List<Food> showAll() {
        return foodDao.showAll();
    }

    @Override
    public void remove(int id) {
        foodDao.remove(id);
    }

    public FoodDao getFoodDao() {
        return foodDao;
    }

    public void setFoodDao(FoodDao foodDao) {
        this.foodDao = foodDao;
    }
}