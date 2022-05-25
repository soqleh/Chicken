package com.chicken.repository;

import com.chicken.data.Menu;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Set;

public class TotalStoreDBRepository {
    private LinkedHashMap<String, ArrayList<Menu>> allStore = new LinkedHashMap<>();
    private static TotalStoreDBRepository sInstance;

    private TotalStoreDBRepository() {
    }

    public static TotalStoreDBRepository getInstance() {
        if (sInstance == null) sInstance = new TotalStoreDBRepository();
        return sInstance;
    }

    public boolean addStoreInfo(String storename, String menuName, int price) {
        ArrayList<Menu> menus;
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


    public LinkedHashMap<String, ArrayList<Menu>> getAllStore() {
        return allStore;
    }

    public ArrayList<String> getAllStoreList(){
        if(allStore.isEmpty()) return null;
        Set<String> keySet = allStore.keySet();
        ArrayList<String> listOfStore = new ArrayList<String>(keySet);
        return listOfStore;
    }

    public ArrayList<Menu> getAllMenuList(String storeName){
        if(allStore.isEmpty()) return null;
        return allStore.get(storeName);
    }

}
