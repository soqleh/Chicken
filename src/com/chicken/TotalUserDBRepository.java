package com.chicken;

import com.chicken.data.Menu;

import java.util.ArrayList;
import java.util.HashMap;

public class TotalUserDBRepository {
    HashMap<String, ArrayList<Menu>> allOrderByUser;
    private static TotalUserDBRepository sInstance;
    private TotalUserDBRepository() {
    }

    public static TotalUserDBRepository getInstance() {
        if(sInstance == null) sInstance = new TotalUserDBRepository();
        return sInstance;
    }
    public HashMap<String, ArrayList<Menu>> getAllOrders(){
        return allOrderByUser;
    }
}
