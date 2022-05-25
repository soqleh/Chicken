package com.chicken.login;

public class TransferableLoginInfo {
    int userType;
    String userName = "가맹주";

    public int getUserType() {
        return userType;
    }

    public String getUserName() {
        return userName;
    }

    public TransferableLoginInfo(int userType, String userName) {
        this.userType = userType;
        this.userName = userName;
    }
}
