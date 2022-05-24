package com.chicken;

import com.chicken.data.Menu;
import com.chicken.data.User;
import com.chicken.interfaces.IOwnerFunction;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Owner extends User implements IOwnerFunction {
    private static Owner sInstance;

    private Owner() {
        super("가맹주");
    }

    public static Owner getInstance() {
        if (sInstance == null) sInstance = new Owner();
        return sInstance;
    }


    @Override
    public boolean addStoreAndMenu(String storeName, String menu, int price) {
        return TotalStoreDBRepository.getInstance().addStoreInfo(storeName, menu, price);

    }

    @Override
    public void getStars() {
        HashMap<String, ArrayList<Menu>> allStore = TotalStoreDBRepository.getInstance().getAllStore();
        System.out.print("가맹주님/n" + "------------------/n");
        for (Map.Entry<String, ArrayList<Menu>> info : allStore.entrySet()) {
            ArrayList<Menu> menus = info.getValue();
            for (Menu menu : menus) {
                System.out.print(
                        "주문 매장: " + info.getKey() + "/n"
                                + "주문 메뉴: " + menu + "/n");
                int starRate = menu.getStar();
                if (starRate > 0) {
                    System.out.println("별점: ");
                    for (int i = 1; i <= starRate; i++) {
                        System.out.println("*");
                    }
                    System.out.println("/n");
                }
            }
        }
    }
}
