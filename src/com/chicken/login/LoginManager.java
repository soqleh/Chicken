package com.chicken.login;

import com.chicken.type.UserType;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.chicken.OrderSystem.printWithArrow;
import static com.chicken.OrderSystem.sc;

public class LoginManager {
    HashMap<String, LoginInfo> members = new HashMap<>();
    private final String regexForId = "^[a-z0-9]{5,12}$";
    private final String regexForPw = "^[a-z0-9_-]{6,10}$";

    private static LoginManager sInstance;

    private LoginManager() {
        members.put("aaaaa", new LoginInfo("aaaaa", "pppppp", "손님1", 1));
        members.put("bbbbb", new LoginInfo("bbbbb", "pppppp", "손님2", 1));
        members.put("ccccc", new LoginInfo("ccccc", "pppppp", "손님3", 1));
        members.put("ddddd", new LoginInfo("ddddd", "pppppp", "점장", 2));
    }

    public static LoginManager getInstance() {
        if (sInstance == null) sInstance = new LoginManager();
        return sInstance;
    }

    public static void logout() {
        sInstance = null;
    }

    // 회원가입
    public void register() {
        System.out.println("\n" + "=".repeat(50) + "\n***회원가입***");
        int userType = checkUsertype();
        String id = inputId();
        String pw = inputPw();
        //String name = "가맹주";
//        if(userType == UserType.TYPE_GUEST) {
        printWithArrow("[System] 고객님의 성함을 입력하세요.");
        String name = sc.nextLine();
//        }
        members.put(id, new LoginInfo(id, pw, name, userType));
        System.out.println("[안내] 회원 가입이 완료 되었습니다. \n 다시 로그인 해주세요.");
    }

    private int checkUsertype() {
        int userType = UserType.TYPE_NONE;
        while (true) {
            printWithArrow("[System] 고객님이시면 1번, 점주님이시면 2번을 눌러주세요\n"
                    + "(1) 고객님\n" + "(2) 점주님");
            if (!sc.hasNextInt()) {
                sc.next(); //입력한것 버리기
                System.out.println("잘못 입력 하셨습니다.");
                continue;
            }
            userType = sc.nextInt();
            break;
        }
        sc.nextLine();//버퍼버리기
        return userType;
    }

    private String inputId() {
        String id = "";
        while (true) {
            printWithArrow("[System] 사용할 아이디를 입력하세요");
            id = sc.nextLine();
            if (!isValidId(id)) {
                System.out.println("잘못된 id 포맷입니다.\n소문자, 숫자 포함 5자리 이상 12자리 이하로 입력해 주세요,.");
                continue;
            }
            break;
        }
        return id;
    }

    private String inputPw() {
        String pw = "";
        while (true) {
            printWithArrow("[System] 사용할 비밀번호를 입력하세요");
            pw = sc.nextLine();
            if (!isValidPw(pw)) {
                System.out.println("잘못된 Password 포맷입니다.\n소문자, 숫자, _, - 포함 6자리 이상 10자리 이하로 입력해 주세요,.");
                continue;
            }
            break;
        }
        return pw;
    }

    private boolean isValidId(String id) {
        Pattern pattern = Pattern.compile(regexForId);
        Matcher matcher = pattern.matcher(id);
        return matcher.matches();
    }

    private boolean isValidPw(String id) {
        Pattern pattern = Pattern.compile(regexForPw);
        Matcher matcher = pattern.matcher(id);
        return matcher.matches();
    }

    // 로그인
    public TransferableLoginInfo login() {
        System.out.println("\n" + "=".repeat(50) + "\n***로그인***\n");
        TransferableLoginInfo result = null;
        String id = inputId();
        String pw = inputPw();
        // 아이디와 패스워드 유무 확인
        if (members.containsKey(id) && members.get(id).getPw().equals(pw)) {
            System.out.println("로그인에 성공했습니다.");
            result = new TransferableLoginInfo(members.get(id).getUserType(), members.get(id).getUserName());
        } else
            System.out.println("아이디 또는 패스워드가 알맞지 않습니다.");
        return result;
    }
}
