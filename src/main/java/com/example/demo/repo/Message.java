package com.example.demo.repo;


import javax.persistence.*;


/**
 * The type Message.
 */
@Entity
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String author;

    private String msg;

    /**
     * Instantiates a new Message.
     */
    public Message() {}

    /**
     * Instantiates a new Message.
     *
     * @param author the author
     * @param msg    the msg
     */
    public Message(String author, String msg) {
        this.author = author;
        this.msg = msg;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public long getId() {
        return id;
    }

    /**
     * Sets author.
     *
     * @param author the author
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     * Gets author.
     *
     * @return the author
     */
    public String getAuthor() {
        return author;
    }

    /**
     * Sets msg.
     *
     * @param msg the msg
     */
    public void setMsg(String msg) {
        this.msg = msg;
    }

    /**
     * Gets msg.
     *
     * @return the msg
     */
    public String getMsg() {
        return this.msg;
    }


    @Override
    public String toString() {
        return "MSG{" + "user=" + author + ", msg=" + msg + '}';
    }
}

