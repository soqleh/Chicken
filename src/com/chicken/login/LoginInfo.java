package com.chicken.login;

class LoginInfo {
    private String id;
    private String pw;
    private String userName;
    private int userType;

    public LoginInfo(String id, String pw, String userName, int userType) {
        this.id = id;
        this.pw = pw;
        this.userName = userName;
        this.userType = userType;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPw() {
        return pw;
    }

    public void setPw(String pw) {
        this.pw = pw;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getUserType() {
        return userType;
    }

    public void setUserType(int userType) {
        this.userType = userType;
    }
}
