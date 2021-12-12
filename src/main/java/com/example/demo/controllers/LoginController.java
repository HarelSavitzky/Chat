package com.example.demo.controllers;

import com.example.demo.beans.SpringSession;
import com.example.demo.repo.MessageRepository;
import com.example.demo.repo.User;
import com.example.demo.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;


/**
 * The type Login controller.
 */
@Controller
public class LoginController {

    @Resource(name="sessionBean")
    private SpringSession sessionData;

    @Autowired
    private UserRepository userRepository;

    private UserRepository getRepo() {
        return userRepository;
    }

    /**
     * Main string.
     *
     * @param user the user
     * @return the string
     */
    @GetMapping("/")
    public String main(User user) {

        if (sessionData.getUserName() == null)
            return "login";

        return "redirect:/chatroom";
    }

    /**
     * Gets signup.
     *
     * @return the signup
     */
    @GetMapping("/signup")
    public String getSignup() {
        return "redirect:/";
    }

    /**
     * Post signup string.
     *
     * @param user   the user
     * @param result the result
     * @param model  the model
     * @return the string
     */
    @PostMapping("/signup")
    public String postSignup(@Valid User user, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "login";
        }

        User temp = getRepo().findByUserName(user.getUserName());
        if(temp != null){
            String err = "This user name is taken. choose another one please";
            model.addAttribute("error",err);
            return "login";
        }
        synchronized (userRepository) {
            getRepo().save(user);
            sessionData.setUserName(user.getUserName());
            sessionData.setId(user.getId());
        }
        return "redirect:/chatroom";
    }

    /**
     * Logout string.
     *
     * @param request the request
     * @return the string
     */
    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        getRepo().delete(getRepo().findById(sessionData.getId()));
        request.getSession().invalidate();
        System.out.println("session invalidated");
        return "redirect:/";
    }
}

