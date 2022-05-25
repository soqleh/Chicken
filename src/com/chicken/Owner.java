package com.chicken;

import com.chicken.data.User;
import com.chicken.repository.TotalStoreDBRepository;
import com.chicken.type.UserType;

import static com.chicken.OrderSystem.printWithArrow;
import static com.chicken.OrderSystem.sc;

public class Owner extends User{
    private static Owner sInstance;

    private Owner() {
        super("가맹주", UserType.TYPE_OWNER);
    }

    public static Owner getInstance() {
        if (sInstance == null) sInstance = new Owner();
        return sInstance;
    }


    @Override
    public void orderMenu() {
        //DO_NOT_IMPLEMENT
    }

    @Override
    public void setStars() {
        //DO_NOT_IMPLEMENT
    }

    @Override
    public void addStoreAndMenu() {
        printWithArrow("[안내] 음식점 상호는 무엇인가요?");
        sc.nextLine();
        String storeName = sc.nextLine();
        printWithArrow("[안내] 대표 메뉴 이름은 무엇인가요?");
        String menuName = sc.nextLine();
        printWithArrow("[안내] 해당 메뉴 가격은 얼마인가요? (단위:원)");
        int price = sc.nextInt();
        if(TotalStoreDBRepository.getInstance().addStoreInfo(storeName, menuName, price))
            System.out.println("[시스템] 가게 등록이 정상 처리되었습니다.");
        else
            System.out.println("[시스템] 이미 존재하는 정보입니다.");
    }

    //매장의 메뉴에 대한 평점 조회
    @Override
    public void getStars() {
        System.out.printf("\n" + "=".repeat(50) + "\n");
        System.out.println("조회자: " + name);
        super.getStars();
        System.out.printf("\n" + "=".repeat(50));
    }
}
