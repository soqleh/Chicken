package com.chicken;

import com.chicken.type.ActionType;
import com.chicken.type.UserType;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Start {
    private static int[] guestActionOrder = {ActionType.ACTION_NONE, ActionType.ACTION_GET_STAR, ActionType.ACTION_ORDER_MENU, ActionType.ACTION_SET_STAR, ActionType.ACTION_QUIT};
    private static int[] ownerActionOrder = {ActionType.ACTION_NONE, ActionType.ACTION_ADD_STORE, ActionType.ACTION_GET_STAR, ActionType.ACTION_QUIT};
    static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        int usertype;
        while (true) {
            printWithArrow("[System] 고객님이시면 1번, 점주님이시면 2번을 눌러주세요\n" +
                    "(1) 고객님\n" + "(2) 점주님");
            if (!sc.hasNextInt()) {
                sc.next(); //입력한것 버리기
                System.out.println("잘못 입력 하셨습니다.");
                continue;
            }
            usertype = sc.nextInt();
            if(usertype == UserType.TYPE_GUEST) {
                sc.nextLine();
                printWithArrow("[System] 고객님의 성함을 입력해 주세요.");
                String username = sc.nextLine();
                OrderSystem.getInstance().setCurUser(new Guest(username));
            }else if(usertype == UserType.TYPE_OWNER){
                OrderSystem.getInstance().setCurUser(Owner.getInstance());
            }else{
                System.out.println("잘못 입력 하셨습니다.");
                continue;
            }
            printForUser(usertype);
            int action = sc.nextInt();
            if(doAction(usertype, action)){
                break;
            } else continue;

        }
    }
    public static boolean doAction(int userType, int action){
        int actionType;
        if(userType == UserType.TYPE_GUEST){
            actionType = guestActionOrder[action];
            if(actionType == ActionType.ACTION_QUIT) {
                System.out.println("[안내] 이용해주셔서 감사합니다.");
                sc.close();
            }
            switch (actionType){
                case ActionType.ACTION_GET_STAR:
                    OrderSystem.getInstance().getStars();
                    break;
                case ActionType.ACTION_ORDER_MENU:
                    OrderSystem.getInstance().orderMenu();
                    break;
                case ActionType.ACTION_SET_STAR:
                    OrderSystem.getInstance().setStar(5);
                    break;
                default:
                    System.out.println("틀린 번호입니다. 다시 입력해주세요.");
                    return false;
            }
        }else if(userType == UserType.TYPE_OWNER){
            actionType = ownerActionOrder[action];
            if(actionType == ActionType.ACTION_QUIT) {
                System.out.println("[안내] 이용해주셔서 감사합니다.");
                sc.close();
            }
            switch (actionType){
                case ActionType.ACTION_ADD_STORE:
                    OrderSystem.getInstance().addStore();
                    break;
                case ActionType.ACTION_GET_STAR:
                    OrderSystem.getInstance().getStars();
                    break;
                default:
                    System.out.println("틀린 번호입니다. 다시 입력해주세요.");
                    return false;
            }
        }
        return true;
    }
    public static void printForUser(int userType){
        int input;
        String strToPrint;
        if(userType == UserType.TYPE_GUEST){
            strToPrint = "[System] 코드스테이츠 고객님 어서오세요.\n" +
                    "[System] 해당 프로그램의 기능입니다.\n" +
                    "1. 음식점 별점 조회하기\n" +
                    "2. 음식 주문하기\n" +
                    "3. 별점 등록하기\n" +
                    "4. 프로그램 종료하기\n" +
                    "------------------------------\n" +
                    "[시스템] 무엇을 도와드릴까요?";
        } else {
            strToPrint = "[System] 코드스테이츠 점주님 어서오세요.\n" +
                    "[System] 해당 프로그램의 기능입니다.\n" +
                    "1. 음식점 등록하기\n" +
                    "2. 음식점 별점 조회하기\n" +
                    "3. 프로그램 종료하기\n" +
                    "------------------------------\n" +
                    "[시스템] 무엇을 도와드릴까요?";
        }
        printWithArrow(strToPrint);
    }
    public static void printWithArrow(String print){
        System.out.print(print + "/n>>> ");
    }
}
