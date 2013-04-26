package org.foodspring.web;

import org.foodspring.domain.AssignMeal;
import org.foodspring.domain.Comment;
import org.foodspring.domain.Food;
import org.foodspring.domain.Meal;
import org.foodspring.service.CommentService;
import org.foodspring.service.FoodService;
import org.foodspring.service.MealService;
import org.foodspring.validator.AddCommentFormValidator;
import org.foodspring.validator.AddMealFormValidator;
import org.foodspring.validator.AssignMealFormValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: azim
 * Date: 4/2/13
 * Time: 5:41 PM
 * To change this template use File | Settings | File Templates.
 */

@Controller
public class MealController {

    @Autowired
    private MealService mealService;
    @Autowired
    private FoodService foodService;
    @Autowired
    private CommentService commentService;

    @Autowired
    private AddCommentFormValidator addCommentFormValidator;
    @Autowired
    private AddMealFormValidator addMealFormValidator;
    @Autowired
    private AssignMealFormValidator assignMealFormValidator;

    @RequestMapping("/meallist.htm")
    public ModelAndView mealList(@RequestParam(value = "type", required = false) String type, @RequestParam(value = "id", required = false) String sid) {
        if (type != null && type.equals("remove_meal")) {
            int id = Integer.parseInt(sid);
            mealService.remove(id);
        }
        List<Meal> meals = mealService.showAll();
        return new ModelAndView("meallist", "meals", meals);
    }

    @RequestMapping("/mealform.htm")
    public ModelAndView addMealForm(ModelMap modelMap) {
        List<Food> foods = foodService.showAll();
        modelMap.put("foods", foods);
        return new ModelAndView("mealform", "command", new Meal());
    }

    @RequestMapping(value = "/addMeal", method = RequestMethod.POST)
    public String addFood(@ModelAttribute("command") Meal meal, BindingResult result, ModelMap modelMap) {
        addMealFormValidator.validate(meal, result);
        if (result.hasErrors()) {
            List<Food> foods = foodService.showAll();
            modelMap.put("foods", foods);
            return "mealform";
        }
        mealService.add(meal);
        return "redirect:meallist.htm";
    }

    @RequestMapping("/assign.htm")
    public ModelAndView assign(@RequestParam(value = "type", required = false) String type, @RequestParam(value = "id", required = false) String sid, ModelMap modelMap) {
        if (type != null && type.equals("remove_assign")) {
            int id = Integer.parseInt(sid);
            mealService.removeAssign(id);
        }
        List<AssignMeal> meals = mealService.showAssignedMeal();
        List<Comment> commentList = commentService.showComments();

        modelMap.put("meals", meals);
        modelMap.put("commentList", commentList);
        modelMap.put("command", new Comment());
        return new ModelAndView("assign");
    }

    @RequestMapping("/assignmeal.htm")
    public ModelAndView assignMealForm(ModelMap modelMap) {
        List<Meal> meals = mealService.showAll();
        modelMap.put("meals", meals);
        return new ModelAndView("assignmeal", "command", new AssignMeal());
    }

    @RequestMapping(value = "/assignMeal", method = RequestMethod.POST)
    public String assignMeal(@ModelAttribute("command") AssignMeal assignMeal, BindingResult result, ModelMap modelMap) {
        assignMealFormValidator.validate(assignMeal, result);
        if (result.hasErrors()) {
            List<Meal> meals = mealService.showAll();
            modelMap.put("meals", meals);
            return "assignmeal";
        }
        String for_date = new SimpleDateFormat("dd-MMM-yy").format(new Date());
        assignMeal.setFor_date(for_date);
        mealService.assignMeal(assignMeal);
        return "redirect:assign.htm";
    }

    @RequestMapping(value = "/addComment", method = RequestMethod.POST)
    public ModelAndView addComment(@ModelAttribute("command") Comment comment, BindingResult result, ModelMap modelMap) {
        addCommentFormValidator.validate(comment, result);
        if (result.hasErrors()) {
            List<AssignMeal> meals = mealService.showAssignedMeal();
            List<Comment> commentList = commentService.showComments();

            modelMap.put("meals", meals);
            modelMap.put("commentList", commentList);
            return new ModelAndView("assign");
        }
        String time = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss").format(new Date());
        comment.setTime(time);
        commentService.addComment(comment);
        return new ModelAndView("redirect:assign.htm");
    }
}
