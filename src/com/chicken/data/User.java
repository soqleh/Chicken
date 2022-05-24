package com.chicken.data;

import com.chicken.interfaces.ICommonFunction;

abstract public class User implements ICommonFunction {
//    //UserType
//    protected Type userType;
    //이름
    protected String name;//가맹주 or 김철수

    protected User(String name) {//하위 클래스 생성자에서 super로 부를 용도
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
