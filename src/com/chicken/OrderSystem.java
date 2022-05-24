package com.chicken;

import com.chicken.data.Menu;

import java.util.ArrayList;
import com.chicken.data.User;

import static com.chicken.Start.printWithArrow;
import static com.chicken.Start.sc;

public class OrderSystem {
    User curUser;
    private ArrayList<Guest> guestList;
    //private ArrayList<Owner> ownerList; //owner가 가맹주라 필요 없음
    private static OrderSystem sInstance;
    private OrderSystem() {
    }

    public static OrderSystem getInstance() {
        if(sInstance == null) sInstance = new OrderSystem();
        return sInstance;
    }

//    private LinkedHashMap<String, Store> hashMap = new LinkedHashMap<>();

    public void setCurUser(User user){
        curUser = user;
    }

    //1) [사장님용] 음식점 등록하기
    public void addStore(){
        printWithArrow("[안내] 반갑습니다. 가맹주님!\n" +
                "[안내] 음식점 상호는 무엇인가요?");
        String storeName = sc.nextLine();
        printWithArrow("[안내] 대표 메뉴 이름은 무엇인가요?");
        String menuName = sc.nextLine();
        printWithArrow("[안내] 해당 메뉴 가격은 얼마인가요?");
        int price = sc.nextInt();
        Owner.getInstance().addStoreAndMenu(storeName, menuName, price);
        System.out.println("[시스템] 가게 등록이 정상 처리되었습니다.");
    }
    //2) [고객님과 사장님용] 음식점 별점 조회하기
    public void getStars(){
        curUser.getStars();
    }

    //3) [고객님용] 음식 주문하기
    public void orderMenu(){

    }
    //4) [고객님용] 별점 등록하기
    public void setStar(int rate) {

    }

}
