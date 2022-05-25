package com.chicken.interfaces;

import com.chicken.data.Menu;
import com.chicken.repository.TotalStoreDBRepository;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

public interface IActionInterface {

    abstract void orderMenu();

    abstract void setStars();

    abstract void addStoreAndMenu();

    default void getStars() {
        LinkedHashMap<String, ArrayList<Menu>> allStore = TotalStoreDBRepository.getInstance().getAllStore();
        if (allStore == null || allStore.isEmpty()) {
            System.out.println("*** 조회 결과가 없습니다.***");
            return;
        }
        for (Map.Entry<String, ArrayList<Menu>> info : allStore.entrySet()) {
            System.out.println("[매장: " + info.getKey() + "]");
            ArrayList<Menu> menus = info.getValue();
            for (Menu menu : menus) {
                System.out.printf("* 메뉴: %s (별점: %.1f)\n", menu.getMenu(), menu.getStar());
            }
        }

    }

    ;
}
