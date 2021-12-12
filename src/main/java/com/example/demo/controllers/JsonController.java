package com.example.demo.controllers;

import com.example.demo.beans.SpringSession;
import com.example.demo.repo.Message;
import com.example.demo.repo.MessageRepository;
import com.example.demo.repo.User;
import com.example.demo.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;


import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

/**
 * The type Json controller.
 */
@Controller
public class JsonController {

    @Autowired
    private UserRepository userRepository;

    private UserRepository getRepo() {
        return userRepository;
    }

    @Autowired
    private MessageRepository messageRepository;

    private MessageRepository getMsgRepo() { return messageRepository; }


    /**
     * Gets users.
     *
     * @return the users
     */
    @GetMapping(value = "/getUsers")
    public @ResponseBody
    List<User> getUsers() {
        return getRepo().findAll();
    }

    /**
     * Find by author list.
     *
     * @param request the request
     * @return the list
     * @throws IOException the io exception
     */
    @PostMapping(value = "/findByAuthor")
    public @ResponseBody
    List<Message> findByAuthor(HttpServletRequest request) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));
        String author = br.readLine();
        return getMsgRepo().findByAuthorContainingIgnoreCase(author);
    }

    /**
     * Find by message list.
     *
     * @param request the request
     * @return the list
     * @throws IOException the io exception
     */
    @PostMapping(value = "/findByMessage")
    public @ResponseBody
    List<Message> findByMessage(HttpServletRequest request) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));
        String message = br.readLine();
        return getMsgRepo().findByMsgContainingIgnoreCase(message);
    }

    /**
     * Gets messages.
     *
     * @return the messages
     */
    @GetMapping(value = "/getMessages")
    public @ResponseBody
    List<Message> getMessages() {
        return getMsgRepo().findAll();
    }
}
