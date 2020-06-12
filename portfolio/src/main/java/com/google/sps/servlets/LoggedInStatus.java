package com.google.sps.servlets;

public class LoggedInStatus {
    private boolean loggedIn;
    private String email;
    private String logoutUrl;
    private String loginUrl;

    public LoggedInStatus(boolean loggedIn) {
        this.loggedIn = loggedIn;
        this.email = email;
        this.loginUrl = loginUrl;
        this.logoutUrl = logoutUrl;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public void setLogoutUrl(String logoutUrl){
        this.logoutUrl = logoutUrl;
    }

    public void setLoginUrl(String loginUrl){
        this.loginUrl = loginUrl;
    }
}