package xyz.phyoekhant.padc.mealordering.responses;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

import xyz.phyoekhant.padc.mealordering.data.vos.MealVO;

/**
 * Created by Phyoe Khant on 8/28/2016.
 */
public class MealListResponse {

    @SerializedName("code")
    private int code;

    @SerializedName("message")
    private String message;

    @SerializedName("meal_list")
    private ArrayList<MealVO> mealList;

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public ArrayList<MealVO> getMealList() {
        return mealList;
    }
}
