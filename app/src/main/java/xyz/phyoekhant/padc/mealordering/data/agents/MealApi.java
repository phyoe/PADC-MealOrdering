package xyz.phyoekhant.padc.mealordering.data.agents;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import xyz.phyoekhant.padc.mealordering.responses.MealListResponse;
import xyz.phyoekhant.padc.mealordering.utils.MealOrderingConstants;

/**
 * Created by aung on 7/9/16.
 */
public interface MealApi {

    @FormUrlEncoded
    @POST(MealOrderingConstants.API_GET_MEAL_LIST)
    Call<MealListResponse> loadMeals(
            @Field(MealOrderingConstants.PARAM_ACCESS_TOKEN) String param);
}
