package com.chicken;

import com.chicken.data.Menu;
import com.chicken.data.Order;
import com.chicken.data.User;
import com.chicken.interfaces.IGuestFunction;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Guest extends User implements IGuestFunction {
    ArrayList<Order> myOrderList;
    //나의 별점 - 내가 준 별점 (가게로 조회한 별점은 사용자 통합별점)
    public Guest(String userName) {
        super(userName);
    }

    public ArrayList<Order> getMyOrderList() {
        return myOrderList;
    }


    @Override
    public void orderMenu() {

    }

    @Override
    public void setStars() {

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
