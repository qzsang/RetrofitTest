/**
 * Copyright 2016 aTool.org
 */
package com.qzsang.retrofittest.net.bean;

/**
 * Auto-generated: 2016-10-29 12:34:23
 *
 * @author aTool.org (i@aTool.org)
 * @website http://www.atool.org/json2javabean.php
 */
public class BookBean {

    private int book;
    private int id;
    private String message;
    private int seq;
    private String title;

    public void setBook(int book) {
        this.book = book;
    }

    public int getBook() {
        return book;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setSeq(int seq) {
        this.seq = seq;
    }

    public int getSeq() {
        return seq;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public String toString() {
        return "BookBean{" +
                "book=" + book +
                ", id=" + id +
                ", message='" + message + '\'' +
                ", seq=" + seq +
                ", title='" + title + '\'' +
                '}';
    }
}