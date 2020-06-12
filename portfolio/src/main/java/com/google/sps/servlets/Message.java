package com.google.sps.servlets;
   
public class Message {
    private String comment;
    private String imageUrl;
    private String email;

    public Message(String comment, String imageUrl, String email) {
        this.comment = comment;
        this.imageUrl = imageUrl;
        this.email = email;
    }
}