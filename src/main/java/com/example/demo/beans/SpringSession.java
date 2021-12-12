package com.example.demo.beans;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import java.io.Serializable;


/**
 * The type Spring session.
 */
@Component
public class SpringSession implements Serializable {

    private long id;
    private String userName;

    /**
     * Set user name.
     *
     * @param name the name
     */
    public void setUserName(String name){userName = name;}

    /**
     * Get user name string.
     *
     * @return the string
     */
    public String getUserName(){return userName;}

    /**
     * Set id.
     *
     * @param newId the new id
     */
    public void setId(long newId){id = newId;}

    /**
     * Get id long.
     *
     * @return the long
     */
    public long getId(){return id;}

    /**
     * Session bean com . example . demo . beans . spring session.
     *
     * @return the com . example . demo . beans . spring session
     */
    /* BEAN using ctor - session scope */
    @Bean
    @Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
    public com.example.demo.beans.SpringSession sessionBean () {
        com.example.demo.beans.SpringSession m = new com.example.demo.beans.SpringSession();
        return m;
    }
}