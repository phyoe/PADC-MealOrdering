package xyz.phyoekhant.padc.mealordering.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;
import xyz.phyoekhant.padc.mealordering.MealOrderingApp;
import xyz.phyoekhant.padc.mealordering.R;
import xyz.phyoekhant.padc.mealordering.data.models.MealModel;
import xyz.phyoekhant.padc.mealordering.data.vos.MealVO;

public class MealDetailActivity extends AppCompatActivity {

    private static final String IE_MEAL_NAME = "IE_MEAL_NAME";
    private static final String IE_MEAL = "IE_MEAL";

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.fab)
    FloatingActionButton fab;

    @BindView(R.id.tv_meal_desc)
    TextView tvMealDesc;

    @BindView(R.id.tv_price)
    TextView tvMealPrice;

    @BindView(R.id.iv_meal)
    ImageView ivMeal;

    @BindView(R.id.collapsing_toolbar)
    CollapsingToolbarLayout collapsingToolbar;

    @BindView(R.id.tv_meal_ingrident)
    TextView tvMealIngrident;

    private String mMealTitle;
    private MealVO mMeal;
    public int mMealid;
    public  String ingident="Ingridents : " +  "\n\n" ;

    public static Intent newIntent(int mealid) {

        Intent intent = new Intent(MealOrderingApp.getContext(), MealDetailActivity.class);
        intent.putExtra(IE_MEAL_NAME, mealid);
        //Bundle bundle=new Bundle();

        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_meal_detail);
        ButterKnife.bind(this, this);

        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String imageUrl =  mMeal.getimgUrl();

            }
        });

        int mMealId=getIntent().getIntExtra(IE_MEAL_NAME,0);
        mMeal= MealModel.getInstance().getMealById(mMealId);
        // Log.d(mMealid);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Context context = MealOrderingApp.getContext();
            String transitionName = context.getResources().getString(R.string.meal_list_detail_transition_name);
            ivMeal.setTransitionName(transitionName);
        }

        mMealTitle = mMeal.getName();

        bindData(mMeal);
    }
    private void bindData(MealVO meal) {
        String[] ingredients = meal.getIngredients();

        for(int i=0;i<ingredients.length;i++){
            ingident = ingident + ingredients[i] + "\n";
        }

        tvMealDesc.setText(meal.getDescription() );
        tvMealPrice.setText("PRICE : " + meal.getPrice() + " Ks");
        tvMealIngrident.setText(ingident);

        String imageUrl = meal.getimgUrl();
        Glide.with(ivMeal.getContext())
                .load(imageUrl)
                .centerCrop()
                .placeholder(R.drawable.stock_photo_placeholder)
                .error(R.drawable.stock_photo_placeholder)
                .into(ivMeal);



        collapsingToolbar.setTitle(mMealTitle);
    }
}
