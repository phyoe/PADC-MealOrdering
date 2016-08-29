package xyz.phyoekhant.padc.mealordering.utils;

import com.google.gson.Gson;

/**
 * Created by Phyoe Khant on 8/28/2016.
 */
public class CommonInstance {

    private static Gson gson = new Gson();

    public static Gson getGsonInstance(){
        return gson;
    }
}
