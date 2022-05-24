package com.chicken;

import com.chicken.data.Menu;

import java.util.ArrayList;
import java.util.HashMap;

public class TotalStoreDBRepository {
    private HashMap<String, ArrayList<Menu>> allStore;
    private static TotalStoreDBRepository sInstance;

    private TotalStoreDBRepository() {
    }

    public static TotalStoreDBRepository getInstance() {
        if (sInstance == null) sInstance = new TotalStoreDBRepository();
        return sInstance;
    }

    public boolean addStoreInfo(String storename, String menuName, int price) {
        ArrayList<Menu> menus;
        Menu additionalMenu;
        if (allStore.containsKey(storename)) {
            menus = allStore.get(storename);
            for (Menu menu : menus) {
                if (menu.getMenu() == menuName) {
                    return false;
                }
            }
        } else {
            menus = new ArrayList<>();
        }
        menus.add(new Menu(menuName, price, storename));
        allStore.put(storename, menus);
        return true;
    }

    public HashMap<String, ArrayList<Menu>> getAllStore() {
        return allStore;
    }

}
