package com.chicken;

import java.util.*;

import com.chicken.data.User;

public class UserAccess {
    private User curUser;
    private ArrayList<Guest> guestList;
    //private ArrayList<Owner> ownerList; //owner가 가맹주라 필요 없음
    private static UserAccess sInstance;

    private UserAccess() {
    }

    public static UserAccess getInstance() {
        if (sInstance == null) sInstance = new UserAccess();
        return sInstance;
    }


    public void setCurUser(User user) {
        curUser = user;
    }

    public User getCurUser() {
        return curUser;
    }

    public void logout() {
        curUser.logout();
        curUser = null;
    }

    public void login() {
        curUser.login();
    }

    public boolean isLoggedInNow() {
        return curUser != null && curUser.isLoggedInNow();
    }

    //1) [사장님용] 음식점 등록하기
    public void addStore() {
        curUser.addStoreAndMenu();
    }

    //2) [고객님과 사장님용] 음식점 별점 조회하기
    public void getStars() {
        curUser.getStars();
    }

    //3) [고객님용] 음식 주문하기
    public void orderMenu() {
        curUser.orderMenu();
    }

    //4) [고객님용] 별점 등록하기
    public void setStar() {
        curUser.setStars();
    }
}
