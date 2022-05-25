package com.chicken.repository;

import com.chicken.data.Menu;

import java.util.ArrayList;
import java.util.LinkedHashMap;

public class TotalOrderDBRepository {
    LinkedHashMap<String, ArrayList<Menu>> allOrdersByUser = new LinkedHashMap<>();
    private static TotalOrderDBRepository sInstance;
    private TotalOrderDBRepository() {
    }

    public static TotalOrderDBRepository getInstance() {
        if(sInstance == null) sInstance = new TotalOrderDBRepository();
        return sInstance;
    }
    public LinkedHashMap<String, ArrayList<Menu>> getAllOrders(){
        return allOrdersByUser;
    }

    public void orderMenu(String userName, String storeName, String menuName){
        ArrayList<Menu> menus;
        int count = 0;
        if (allOrdersByUser.containsKey(userName)) {
            menus = allOrdersByUser.get(userName);
            for (Menu menu : menus) {
                if (menu.getMenu() == menuName) {
                    return; //제약조건: User가 동일한 제품을 주문했을 때 count처리 하지 않는다.
                }
            }
        } else {
            menus = new ArrayList<>();
        }
        menus.add(new Menu(menuName, storeName));
        allOrdersByUser.put(userName, menus);
    }
}
