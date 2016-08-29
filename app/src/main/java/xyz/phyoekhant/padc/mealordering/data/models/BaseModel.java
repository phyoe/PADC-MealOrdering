package xyz.phyoekhant.padc.mealordering.data.models;

import de.greenrobot.event.EventBus;
import xyz.phyoekhant.padc.mealordering.data.agents.MealDataAgent;
import xyz.phyoekhant.padc.mealordering.data.agents.RetrofitDataAgent;

/**
 * Created by aung on 7/15/16.
 */
public abstract class BaseModel {

    private static final int INIT_DATA_AGENT_RETROFIT = 4;

    protected MealDataAgent dataAgent;

    public BaseModel() {
        initDataAgent(INIT_DATA_AGENT_RETROFIT);

        EventBus eventBus = EventBus.getDefault();
        if (!eventBus.isRegistered(this)) {
            eventBus.register(this);
        }
    }

    private void initDataAgent(int initType) {
        switch (initType) {
            case INIT_DATA_AGENT_RETROFIT:
                dataAgent = RetrofitDataAgent.getInstance();
                break;
        }
    }

    public void onEventMainThread(Object obj) {

    }

}
