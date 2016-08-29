package xyz.phyoekhant.padc.mealordering.views.holders;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;
import xyz.phyoekhant.padc.mealordering.MealOrderingApp;
import xyz.phyoekhant.padc.mealordering.R;
import xyz.phyoekhant.padc.mealordering.data.vos.MealVO;

/**
 * Created by aung on 7/6/16.
 */
public class MealViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    @BindView(R.id.tv_meal_title)
    TextView tvMealName;

    @BindView(R.id.iv_meal)
    ImageView ivMeal;



    private ControllerMealItem mController;
    private MealVO mMeal;

    public MealViewHolder(View itemView, ControllerMealItem controller) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        itemView.setOnClickListener(this);
        mController = controller;
    }

    public void bindData(MealVO meal) {
        mMeal = meal;
        tvMealName.setText(meal.getName());

        String imageUrl = meal.getimgUrl();
        Log.d(MealOrderingApp.TAG, "imageurl:" + imageUrl);
        Glide.with(ivMeal.getContext())
                .load(imageUrl)
                .centerCrop()
                .placeholder(R.drawable.stock_photo_placeholder)
                .error(R.drawable.stock_photo_placeholder)
                .into(ivMeal);
    }

    @Override
    public void onClick(View view) {
        mController.onTapMealItem(mMeal, ivMeal);
    }

    public interface ControllerMealItem {
        void onTapMealItem(MealVO meal, ImageView ivMeal);
    }
}
