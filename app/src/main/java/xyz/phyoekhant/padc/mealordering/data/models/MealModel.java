package xyz.phyoekhant.padc.mealordering.data.models;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import de.greenrobot.event.EventBus;
import xyz.phyoekhant.padc.mealordering.data.vos.MealVO;
import xyz.phyoekhant.padc.mealordering.events.DataEvent;

/**
 * Created by Phyoe Khant on 8/28/2016.
 */
public class MealModel extends BaseModel {

    public static final String BROADCAST_DATA_LOADED = "BROADCAST_DATA_LOADED";

    private static MealModel objInstance;

    private List<MealVO> mMealList;

    private MealModel() {
        Log.d("Data", "data");
        mMealList = new ArrayList<>();
        dataAgent.loadMeals();
    }

    public static MealModel getInstance() {
        if (objInstance == null) {
            objInstance = new MealModel();
        }
        return objInstance;
    }
    public  void loadMeals()
    {
        dataAgent.loadMeals();
    }

    public List<MealVO> getMealList() {
        return mMealList;
    }

    public MealVO getMealById(int id) {
        for (MealVO meal : mMealList) {
            if (meal.getId()== id)
                return meal;
        }
        return null;
    }

    public void notifyMealsLoaded(List<MealVO> mealList) {
        //Notify that the data is ready - using LocalBroadcast
        mMealList = mealList;

        broadcastMealLoadedWithEventBus();
    }

    public void notifyErrorInLoadingMeals(String message) {

    }

    private void broadcastMealLoadedWithEventBus() {
        EventBus.getDefault().post(new DataEvent.MealDataLoadedEvent("extra-in-broadcast", mMealList));
    }
}
