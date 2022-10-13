package com.codingdojo.troy.bookclubtm.controllers;

// .. don't forget to include all your imports! ..

import com.codingdojo.troy.bookclubtm.models.LoginUser;
import com.codingdojo.troy.bookclubtm.models.User;
import com.codingdojo.troy.bookclubtm.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
public class HomeController {


    // Add once service is implemented:
    @Autowired
    private UserService userServ;

    @GetMapping("/")
    public String index(Model model) {

        // Bind empty User and LoginUser objects to the JSP
        // to capture the form input
        model.addAttribute("newUser", new User());
        model.addAttribute("newLogin", new LoginUser());
        return "index.jsp";
    }

    @PostMapping("/register")
    public String register(@Valid @ModelAttribute("newUser") User newUser,
                           BindingResult result, Model model, HttpSession session) {

        // TO-DO Later -- call a register method in the service
        // to do some extra validations and create a new user!
        User user = userServ.register(newUser, result);
        if(result.hasErrors()) {
            // Be sure to send in the empty LoginUser before
            // re-rendering the page.
            model.addAttribute("newLogin", new LoginUser());
            return "index.jsp";
        }

        // TO-DO Later: Store their ID from the DB in session,
        // in other words, log them in.
        session.setAttribute("userId", user.getId());
        session.setAttribute("user", user);

        return "redirect:/books";
    }

    @PostMapping("/login")
    public String login(@Valid @ModelAttribute("newLogin") LoginUser newUser,
                        BindingResult result, Model model, HttpSession session) {

        // Add once service is implemented:
         User user = userServ.login(newUser, result);

        if(result.hasErrors()) {
            model.addAttribute("newUser", new User());
            return "index.jsp";
        }

        // TO-DO Later: Store their ID from the DB in session,
        // in other words, log them in.
        session.setAttribute("userId", user.getId());
        session.setAttribute("user", user);

        return "redirect:/books";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {

        // Set userId to null and redirect to login/register page
        session.setAttribute("userId", null);

        return "redirect:/";
    }
}
