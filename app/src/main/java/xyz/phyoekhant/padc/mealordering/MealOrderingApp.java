package xyz.phyoekhant.padc.mealordering;

import android.app.Application;
import android.content.Context;

import xyz.phyoekhant.padc.mealordering.data.agents.RetrofitDataAgent;

public class MealOrderingApp extends Application {

    public static final String TAG = "MealOrderingApp";

    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        RetrofitDataAgent.getInstance().loadMeals();
    }

    public static Context getContext() {
        return context;
    }


}
