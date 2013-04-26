package org.foodspring.web;

import org.foodspring.domain.User;
import org.foodspring.validator.LoginValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created with IntelliJ IDEA.
 * User: azim
 * Date: 4/3/13
 * Time: 9:30 AM
 * To change this template use File | Settings | File Templates.
 */

@Controller
public class LoginController {

    @Autowired
    private LoginValidator loginValidator;

    @RequestMapping("/login.htm")
    public ModelAndView loginForm() {
        return new ModelAndView("login", "command", new User());
    }

    @RequestMapping(value = "/loginUser", method = RequestMethod.POST)
    public String login(@ModelAttribute("command") User user, HttpServletRequest request, BindingResult result) {
        loginValidator.validate(user, result);
        if (result.hasErrors()) {
            return "login";
        }
        HttpSession session = request.getSession(true);
        session.setAttribute("username", user.getUsername());
        return "redirect:assign.htm";
    }

    @RequestMapping("/logout.htm")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.invalidate();
        return "redirect:assign.htm";
    }
}
