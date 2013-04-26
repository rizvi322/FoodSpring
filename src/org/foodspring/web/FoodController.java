package org.foodspring.web;

import org.foodspring.domain.Food;
import org.foodspring.service.FoodService;
import org.foodspring.validator.AddFoodFormValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: azim
 * Date: 4/2/13
 * Time: 2:08 PM
 * To change this template use File | Settings | File Templates.
 */

@Controller
public class FoodController {

    @Autowired
    private FoodService foodService;
    @Autowired
    private AddFoodFormValidator addFoodFormValidator;

    @RequestMapping("/foodlist.htm")
    public ModelAndView foodList(@RequestParam(value = "type", required = false) String type, @RequestParam(value = "id", required = false) String sid) {
        if (type != null && type.equals("remove_food")) {
            int id = Integer.parseInt(sid);
            foodService.remove(id);
        }
        List<Food> foods = foodService.showAll();
        return new ModelAndView("foodlist", "foods", foods);
    }

    @RequestMapping("/addfood.htm")
    public ModelAndView addFoodForm() {
        return new ModelAndView("addfood", "command", new Food());
    }

    @RequestMapping(value = "/addFood", method = RequestMethod.POST)
    public String addFood(@ModelAttribute("command") Food food, BindingResult result) {
        addFoodFormValidator.validate(food, result);
        if (result.hasErrors()) {
            return "addfood";
        }
        foodService.add(food);
        return "redirect:foodlist.htm";
    }
}
