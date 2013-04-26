package org.foodspring.service;

import org.foodspring.domain.AssignMeal;
import org.foodspring.domain.Meal;

import java.io.Serializable;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: azim
 * Date: 4/1/13
 * Time: 10:12 AM
 * To change this template use File | Settings | File Templates.
 */
public interface MealService extends Serializable {

    public void add(Meal meal);

    public List<Meal> showAll();

    public void remove(int id);

    public void assignMeal(AssignMeal assignMeal);

    public List<AssignMeal> showAssignedMeal();

    public void removeAssign(int id);
}
