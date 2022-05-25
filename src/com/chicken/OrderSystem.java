package com.chicken;

import com.chicken.login.LoginManager;
import com.chicken.login.TransferableLoginInfo;
import com.chicken.type.ActionType;
import com.chicken.type.LoginType;
import com.chicken.type.UserType;

import java.util.Scanner;

public class OrderSystem {
    private static int[] guestActionOrder
            = {ActionType.ACTION_NONE, ActionType.ACTION_GET_STAR, ActionType.ACTION_ORDER_MENU, ActionType.ACTION_SET_STAR, ActionType.ACTION_LOGOUT, ActionType.ACTION_QUIT};
    private static int[] ownerActionOrder = {ActionType.ACTION_NONE, ActionType.ACTION_ADD_STORE, ActionType.ACTION_GET_STAR, ActionType.ACTION_LOGOUT, ActionType.ACTION_QUIT};

    private static int[] loginActionOrder = {LoginType.REGISTER, LoginType.LOGIN, LoginType.QUIT_LOGIN};
    public static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
            while (true) {
                if(!UserAccess.getInstance().isLoggedInNow() && !doLogin()){//로그아웃된 경우에 Login 시도를 다시 하고 Login시도 중 종료를 누르면 프로그램 종료
                    break;
                }

                int userType = UserAccess.getInstance().getCurUser().getUserType();
                int actionNum = inputForUserAction(userType);
                if(!doAction(userType, actionNum)) break;
            }
    }

    public static boolean doLogin() {
        boolean bGoToNextStep = true;
        while (true) {
            System.out.println("=".repeat(50));
            System.out.println("[안내] 환영합니다.\n서비스를 사용하시려면 로그인이 필요합니다.");
            printWithArrow("[안내] 다음 중 원하시는 메뉴를 선택해 주세요.\n(1) 회원가입\n(2) 로그인\n(3) 종료");
            String UserName = "고객";
            int actionType = loginActionOrder[sc.nextInt() - 1];
            sc.nextLine();
            if (actionType == LoginType.REGISTER) {
                LoginManager.getInstance().register();
                continue;
            } else if (actionType == LoginType.LOGIN) {
                TransferableLoginInfo info = LoginManager.getInstance().login();
                if (info == null) continue;
                if (info.getUserType() == UserType.TYPE_GUEST) {
                    UserAccess.getInstance().setCurUser(new Guest(info.getUserName()));
                } else if (info.getUserType() == UserType.TYPE_OWNER) {
                    UserAccess.getInstance().setCurUser(Owner.getInstance());
                }
                System.out.printf("\n" + "=".repeat(50) + "\n[System] 반갑습니다. %s님!\n" + "=".repeat(50) + "\n", info.getUserName());
                UserAccess.getInstance().login();
            } else if (actionType == LoginType.QUIT_LOGIN) {
                System.out.println("[안내] 서비스를 종료합니다.");
                bGoToNextStep = false;
            } else {
                System.out.println("[안내] 잘못 입력 하셨습니다.");
                continue;
            }

            break;
        }
        return bGoToNextStep;
    }

    public static boolean doAction(int userType, int action) {
        int actionType;
        if (userType == UserType.TYPE_GUEST) {
            actionType = guestActionOrder[action];
            if (actionType == ActionType.ACTION_QUIT) {
                System.out.println("[안내] 이용해주셔서 감사합니다.");
                sc.close();
                return false;
            }
            switch (actionType) {
                case ActionType.ACTION_GET_STAR:
                    UserAccess.getInstance().getStars();
                    break;
                case ActionType.ACTION_ORDER_MENU:
                    UserAccess.getInstance().orderMenu();
                    break;
                case ActionType.ACTION_SET_STAR:
                    UserAccess.getInstance().setStar();
                    break;
                case ActionType.ACTION_LOGOUT:
                    System.out.println("로그아웃 하였습니다.");
                    UserAccess.getInstance().logout();
                    break;
                default:
                    System.out.println("틀린 번호입니다. 다시 입력해주세요.");
                    break;
            }
        } else if (userType == UserType.TYPE_OWNER) {
            actionType = ownerActionOrder[action];
            if (actionType == ActionType.ACTION_QUIT) {
                System.out.println("[안내] 이용해주셔서 감사합니다.");
                sc.close();
            }
            switch (actionType) {
                case ActionType.ACTION_ADD_STORE:
                    UserAccess.getInstance().addStore();
                    break;
                case ActionType.ACTION_GET_STAR:
                    UserAccess.getInstance().getStars();
                    break;
                case ActionType.ACTION_LOGOUT:
                    System.out.println("로그아웃 하였습니다.");
                    UserAccess.getInstance().logout();
                    break;
                default:
                    System.out.println("틀린 번호입니다. 다시 입력해주세요.");
                    break;
            }
        }
        return true;
    }

    public static int inputForUserAction(int userType) {
        int input;
        String strToPrint;
        System.out.println("=".repeat(50));
        if (userType == UserType.TYPE_GUEST) {
            strToPrint = "[System] 프로그램 기능\n" +
                    "1. 음식점 별점 조회하기\n" +
                    "2. 음식 주문하기\n" +
                    "3. 별점 등록하기\n" +
                    "4. 로그아웃\n" +
                    "5. 프로그램 종료하기\n" +
                    "=".repeat(50) + "\n" +
                    "[시스템] 무엇을 도와드릴까요?";
        } else {
            strToPrint = "[System] 프로그램 기능\n" +
                    "1. 음식점 등록하기\n" +
                    "2. 음식점 별점 조회하기\n" +
                    "3. 로그아웃\n" +
                    "4. 프로그램 종료하기\n" +
                    "=".repeat(50) + "\n" +
                    "[시스템] 무엇을 도와드릴까요?";
        }
        printWithArrow(strToPrint);
        return sc.nextInt();
    }

    public static void printWithArrow(String print) {
        System.out.print(print + "\n>>> ");
    }
}


