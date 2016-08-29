package xyz.phyoekhant.padc.mealordering.data.vos;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Phyoe Khant on 8/28/2016.
 */
public class MealVO {

    @SerializedName("id")
    private int id;

    @SerializedName("name")
    private String name;

    @SerializedName("img_url")
    private String imgUrl;

    @SerializedName("description")
    private String description;

    @SerializedName("price")
    private int price;

    @SerializedName("ingredients")
    private String[] ingredients;

    public MealVO(int id, String name, String imgUrl, String description, int price, String[] ingredients) {
        this.id = id;
        this.name = name;
        this.imgUrl = imgUrl;
        this.description = description;
        this.price = price;
        this.ingredients = ingredients;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getimgUrl() {
        return imgUrl;
    }

    public String getDescription() {
        return description;
    }

    public int getPrice() {
        return price;
    }

    public String[] getIngredients() {
        return ingredients;
    }
}
