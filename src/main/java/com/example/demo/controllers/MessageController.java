package com.example.demo.controllers;

import com.example.demo.beans.SpringSession;
import com.example.demo.repo.Message;
import com.example.demo.repo.MessageRepository;
import com.example.demo.repo.User;
import com.example.demo.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

/**
 * The type Message controller.
 */
@Controller
public class MessageController {

    @Resource(name = "sessionBean")
    private SpringSession sessionData;

    @Autowired
    private UserRepository userRepository;

    private UserRepository getRepo() { return userRepository; }

    @Autowired
    private MessageRepository messageRepository;

    private MessageRepository getMsgRepo() { return messageRepository; }

    /**
     * Chatroom string.
     *
     * @param model the model
     * @param user  the user
     * @return the string
     */
    @GetMapping("/chatroom")
    public String chatroom(Model model, User user) {

        if (sessionData.getUserName() == null) {
            return "redirect:/";
        }

        model.addAttribute("User", user);
        model.addAttribute("users", getRepo().findAll());
        model.addAttribute("userName", sessionData.getUserName());
        model.addAttribute("topFiveMessages", getMsgRepo().findTop5ByOrderByIdDesc());

        return "chatroom";
    }

    /**
     * Message list.
     *
     * @param msg     the msg
     * @param request the request
     * @return the list
     * @throws IOException the io exception
     */
    @PostMapping(value = "/message")
    public @ResponseBody
    List<Message> message(@Valid Message msg, HttpServletRequest request) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));
        String temp = br.readLine();
        synchronized(messageRepository) {
            msg.setAuthor(sessionData.getUserName());
            msg.setMsg(temp);
            getMsgRepo().save(msg);
        }
        return getMsgRepo().findAll();
    }
}
