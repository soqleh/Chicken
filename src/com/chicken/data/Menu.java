package com.chicken.data;

//음식점 등록
//로그인 - UserListj //2번 시 음식점 별점을 로그인 User별로, 가맹점 별로
public class Menu {
    String store; //메뉴가 포함된 가게 이름
    String menu;//메뉴이름
    int star;//별점
    int price;//가격

    public Menu(String menu, int price, String storeName) {
        this.menu = menu;
        this.price = price;
        this.store = storeName;
    }

    public String getMenu() {
        return menu;
    }

    public int getStar() {
        return star;
    }

    public void setStar(int star) {
        this.star = star;
    }

    public int getPrice() {
        return price;
    }
}
