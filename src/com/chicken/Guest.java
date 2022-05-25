package com.chicken;

import com.chicken.data.Menu;
import com.chicken.data.User;
import com.chicken.repository.TotalOrderDBRepository;
import com.chicken.repository.TotalStoreDBRepository;
import com.chicken.type.UserType;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import static com.chicken.OrderSystem.printWithArrow;
import static com.chicken.OrderSystem.sc;

public class Guest extends User{
    public Guest(String userName) {
        super(userName, UserType.TYPE_GUEST);
    }

    @Override
    public void orderMenu() {
        ArrayList<String> storeList = TotalStoreDBRepository.getInstance().getAllStoreList();
        if(storeList == null) {
            System.out.println("[안내] 주문 가능한 매장이 없습니다.");
            return;
        }
        System.out.printf("[안내] %s고객님! 메뉴 주문을 진행하겠습니다!", name);
        System.out.println("\n" + "-".repeat(50));
        System.out.println("[주문 가능한 매장 리스트]");
        for (int i = 0; i < storeList.size(); i++) {
            System.out.printf("(%d) %s\n", i+1, storeList.get(i));
        }
        System.out.println("-".repeat(50));
        printWithArrow("[안내] 주문할 음식점 상호는 무엇인가요?");
        int storeNum = sc.nextInt();

        System.out.println("\n" + "-".repeat(50));
        System.out.printf("[%s의 주문 가능한 메뉴]\n", storeList.get(storeNum-1));
        ArrayList<Menu> menuList = TotalStoreDBRepository.getInstance().getAllMenuList(storeList.get(storeNum-1));
        for (int i = 0; i < menuList.size(); i++) {
            Menu menu = menuList.get(i);
            System.out.printf("(%d) %s - %d원 (별점: %.1f)\n", i+1, menu.getMenu(), menu.getPrice(), menu.getStar());
        }
        System.out.println("-".repeat(50));
        printWithArrow("[안내] 주문할 메뉴 이름을 알려주세요!");
        int menuNum = sc.nextInt();
        System.out.println("\n" + "-".repeat(50));
        System.out.printf("[안내] %s 고객님, %s 매장의 %s 메뉴를 주문하였습니다.\n", name, storeList.get(storeNum-1), menuList.get(menuNum-1).getMenu());
        System.out.println("-".repeat(50) + "\n");
        //ORDER DB에 넣기
        TotalOrderDBRepository.getInstance().orderMenu(name, storeList.get(storeNum-1), menuList.get(menuNum-1).getMenu());

    }
    @Override
    public void setStars(/*String storeName, String menuName, int star*/) {
        System.out.printf("[안내] %s고객님! 별점 등록을 진행합니다!", name);
        System.out.println("\n" + "-".repeat(50));
        System.out.println("[기존 주문 내역]");
        ArrayList<Menu> myOrders = TotalOrderDBRepository.getInstance().getAllOrders().get(name);
        if(myOrders == null || myOrders.isEmpty()) {
            System.out.println("[안내] 기존 주문 내역이 없어 별점 등록을 진행할 수 없습니다.");
            return;
        }
        for (int i = 0; i < myOrders.size(); i++) {
            Menu menu = myOrders.get(i);
            System.out.printf("(%d) [%s] %s\n", i+1, menu.getStore(), menu.getMenu());
        }
        System.out.println("-".repeat(50) + "\n");
        printWithArrow("[안내] 별점을 업데이트 할 항목을 고르세요!");
        int reviewNum = sc.nextInt();
        String storeName = myOrders.get(reviewNum-1).getStore();
        String menuName = myOrders.get(reviewNum-1).getMenu();
        printWithArrow("[안내] 음식맛은 어떠셨나요? (1점 ~ 5점)");
        int point = sc.nextInt();
        ArrayList<Menu> list = TotalStoreDBRepository.getInstance().getAllMenuList(storeName);
        //각각의 Guest가 별점을 달수 있다.
        //user가 별점을 달면 count도 더한다. 그리고 별점의 총 합을 count로 나눈다.
        for (Menu menu : list) {
            if(menuName == menu.getMenu()){
                menu.setStar(point);
            }
        }
    }

    @Override
    public void addStoreAndMenu() {
        //DO_NOT_IMPLEMENT
    }

    @Override
    public void getStars() {
        System.out.printf("\n" + "=".repeat(50) + "\n");
        System.out.println("조회자: " + name);
        super.getStars();
        System.out.printf("\n" + "=".repeat(50));
    }
}
