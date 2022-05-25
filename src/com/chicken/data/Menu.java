package com.chicken.data;

//음식점 등록
public class Menu {
    String store; //메뉴가 포함된 가게 이름
    String menu;//메뉴이름
    float star = 0.0f;//별점
    int price;//가격
    int reviewCount = 0;//주문 수량

    public Menu(String menu, int price, String storeName) {
        this.menu = menu;
        this.price = price;
        this.store = storeName;
    }
    public Menu(String menu, String storeName){
        this.menu = menu;
        this.store = storeName;
    }

    public String getMenu() {
        return menu;
    }

    public float getStar() {
        if(reviewCount <=0) return 0.0f;
        return star/reviewCount;
    }

    public void setStar(int star) {
        this.star += star;
        reviewCount++;
    }

    public String getStore() {
        return store;
    }

    public int getPrice() {
        return price;
    }
}
