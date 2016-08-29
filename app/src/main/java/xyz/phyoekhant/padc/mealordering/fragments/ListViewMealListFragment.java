package xyz.phyoekhant.padc.mealordering.fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.greenrobot.event.EventBus;
import xyz.phyoekhant.padc.mealordering.MealOrderingApp;
import xyz.phyoekhant.padc.mealordering.R;
import xyz.phyoekhant.padc.mealordering.adapters.MealAdapter;
import xyz.phyoekhant.padc.mealordering.data.models.MealModel;
import xyz.phyoekhant.padc.mealordering.data.vos.MealVO;
import xyz.phyoekhant.padc.mealordering.events.DataEvent;
import xyz.phyoekhant.padc.mealordering.views.holders.MealViewHolder;


/**
 * A simple {@link Fragment} subclass.
 */
public class ListViewMealListFragment extends Fragment {

    public static String CURRENT_SCREENSTYLE = "CURRENT_SCREENSTYLE";
    public static String SHOW_ONECOLUMN = "1";
    public static String SHOW_TWOCOLUMN = "2";
    private String showcol;
    private int gridColumnSpanCount;

    @BindView(R.id.rv_meals)
    RecyclerView rvMeals;

    private MealAdapter mMealAdapter;
   private MealViewHolder.ControllerMealItem controllerMealItem;

    public ListViewMealListFragment() {
        // Required empty public constructor
    }

    public static ListViewMealListFragment newInstance(String cols) {
        ListViewMealListFragment fragment = new ListViewMealListFragment();
        Bundle bundle=new Bundle();
        bundle.putString(fragment.CURRENT_SCREENSTYLE, cols);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        controllerMealItem = (MealViewHolder.ControllerMealItem) context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView= inflater.inflate(R.layout.fragment_list_view_meal_list, container, false);
        ButterKnife.bind(this, rootView);

        Bundle bundle=getArguments();
        if(bundle!=null)
        {
            showcol=bundle.getString(ListViewMealListFragment.CURRENT_SCREENSTYLE);
            Log.d(MealOrderingApp.TAG,"SHOW COLUMN" + showcol);
        }
        if(showcol==ListViewMealListFragment.SHOW_ONECOLUMN)
        {
            gridColumnSpanCount = getResources().getInteger(R.integer.meal_list_grid);
        }
        if(showcol==ListViewMealListFragment.SHOW_TWOCOLUMN)
        {
            gridColumnSpanCount = getResources().getInteger(R.integer.meal_list_grid2);
        }
        rvMeals.setLayoutManager(new GridLayoutManager(getContext(), gridColumnSpanCount));
        List<MealVO> mealList = MealModel.getInstance().getMealList();
        mMealAdapter = new MealAdapter(mealList, controllerMealItem);
        rvMeals.setAdapter(mMealAdapter);

        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus eventBus = EventBus.getDefault();
        if (!eventBus.isRegistered(this)) {
            eventBus.register(this);
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus eventBus = EventBus.getDefault();
        eventBus.unregister(this);
    }

    public void onEventMainThread(DataEvent.MealDataLoadedEvent event) {
        String extra = event.getExtraMessage();
        Toast.makeText(getContext(), "Extra : " + extra, Toast.LENGTH_SHORT).show();

        //List<AttractionVO> newAttractionList = AttractionModel.getInstance().getAttractionList();
        List<MealVO> newMealList = event.getMealList();
        mMealAdapter.setNewData(newMealList);
        mMealAdapter.notifyDataSetChanged();
    }
}
