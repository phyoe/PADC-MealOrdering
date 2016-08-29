package xyz.phyoekhant.padc.mealordering.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import xyz.phyoekhant.padc.mealordering.MealOrderingApp;
import xyz.phyoekhant.padc.mealordering.R;
import xyz.phyoekhant.padc.mealordering.data.vos.MealVO;
import xyz.phyoekhant.padc.mealordering.views.holders.MealViewHolder;

/**
 * Created by Phyoe Khant on 8/28/2016.
 */
public class MealAdapter extends RecyclerView.Adapter<MealViewHolder> {
    private List<MealVO> mMealList;
    private LayoutInflater inflater;

    private MealViewHolder.ControllerMealItem mcontrollerMealItem;

    public MealAdapter(List<MealVO> mealList,MealViewHolder.ControllerMealItem controllerMealItem){
        inflater = LayoutInflater.from(MealOrderingApp.getContext());
        mMealList = mealList;
        mcontrollerMealItem = controllerMealItem;
    }

    @Override
    public MealViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = inflater.inflate(R.layout.view_item_meal, parent, false);
        return new MealViewHolder(itemView, mcontrollerMealItem);
    }

    @Override
    public void onBindViewHolder(MealViewHolder holder, int position) {
        holder.bindData(mMealList.get(position));
    }

    @Override
    public int getItemCount() {
        return  mMealList.size();
    }

    public void setNewData(List<MealVO> newMealList) {
        mMealList = newMealList;
        notifyDataSetChanged();//framework method
    }
}
