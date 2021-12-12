package com.example.demo.listeners;

import com.example.demo.beans.SpringSession;
import com.example.demo.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.session.events.SessionDestroyedEvent;
import javax.annotation.Resource;
import javax.servlet.annotation.WebListener;


/**
 * The type Session destroyed listener.
 */
@WebListener
public class SessionDestroyedListener implements ApplicationListener<SessionDestroyedEvent> {

    @Resource(name="sessionBean")
    private SpringSession sessionData;

    @Autowired
    private UserRepository userRepository;
    private UserRepository getRepo() { return userRepository; }

    @Override
    public void onApplicationEvent(SessionDestroyedEvent sessionDestroyedEvent) {
        System.out.println("SessionInvalidated");
        getRepo().delete(getRepo().findById(sessionData.getId()));
    }
}