package xyz.phyoekhant.padc.mealordering.data.agents;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import xyz.phyoekhant.padc.mealordering.data.models.MealModel;
import xyz.phyoekhant.padc.mealordering.responses.MealListResponse;
import xyz.phyoekhant.padc.mealordering.utils.CommonInstance;
import xyz.phyoekhant.padc.mealordering.utils.MealOrderingConstants;

/**
 * Created by aung on 7/9/16.
 */
public class RetrofitDataAgent implements MealDataAgent {

    private static RetrofitDataAgent objInstance;

    private final MealApi theApi;

    private RetrofitDataAgent() {
        final OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(15, TimeUnit.SECONDS)
                .writeTimeout(15, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(MealOrderingConstants.MEAL_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(CommonInstance.getGsonInstance()))
                .client(okHttpClient)
                .build();

        theApi = retrofit.create(MealApi.class);
    }

    public static RetrofitDataAgent getInstance() {
        if (objInstance == null) {
            objInstance = new RetrofitDataAgent();
        }
        return objInstance;
    }

    @Override
    public void loadMeals() {
        Call<MealListResponse> loadFoodCall = theApi.loadMeals(MealOrderingConstants.ACCESS_TOKEN);
        loadFoodCall.enqueue(new Callback<MealListResponse>() {
            @Override
            public void onResponse(Call<MealListResponse> call, Response<MealListResponse> response) {
                MealListResponse mealListResponse=response.body();
                if(mealListResponse==null)
                {
                    MealModel.getInstance().notifyErrorInLoadingMeals(response.message());
                }
                else
                {
                    MealModel.getInstance().notifyMealsLoaded(mealListResponse.getMealList());
                }
            }

            @Override
            public void onFailure(Call<MealListResponse> call, Throwable throwable) {

                MealModel.getInstance().notifyErrorInLoadingMeals(throwable.getMessage());
            }
        });
    }
}
