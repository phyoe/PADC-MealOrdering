package xyz.phyoekhant.padc.mealordering.events;

import java.util.List;

import xyz.phyoekhant.padc.mealordering.data.vos.MealVO;

/**
 * Created by aung on 7/9/16.
 */
public class DataEvent {
    public static class MealDataLoadedEvent {

        private String extraMessage;
        private List<MealVO> mealList;

        public MealDataLoadedEvent(String extraMessage, List<MealVO> mealList) {
            this.extraMessage = extraMessage;
            this.mealList = mealList;
        }

        public String getExtraMessage() {
            return extraMessage;
        }

        public List<MealVO> getMealList() {
            return mealList;
        }
    }

}
