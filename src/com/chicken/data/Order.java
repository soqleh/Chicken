package com.chicken.data;

public class Order {
    //상호
    String storeName;
    Menu menuName;
    String guestName;

    public Order(String storeName, Menu menuName, String guestName) {
        this.storeName = storeName;
        this.menuName = menuName;
        this.guestName = guestName;
    }
}