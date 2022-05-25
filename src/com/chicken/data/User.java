package com.chicken.data;

import com.chicken.interfaces.IActionInterface;
import com.chicken.type.UserType;

abstract public class User implements IActionInterface {
    protected String name;//가맹주 or 김철수
    protected boolean bLogin = false;
    protected int userType = UserType.TYPE_NONE;

    protected User(String name, int userType) {//하위 클래스 생성자에서 super로 부를 용도
        this.name = name;
        this.userType = userType;
    }

    public void login() {
        bLogin = true;
    }

    public void logout() {
        bLogin = false;
    }

    public boolean isLoggedInNow(){
        return bLogin;
    }

    public String getName() {
        return name;
    }

    protected void setName(String name) {
        this.name = name;
    }

    public int getUserType() {
        return userType;
    }
}
