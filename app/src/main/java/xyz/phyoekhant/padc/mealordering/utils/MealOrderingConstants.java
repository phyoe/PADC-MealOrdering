package xyz.phyoekhant.padc.mealordering.utils;

public class MealOrderingConstants {

    public static final String MEAL_BASE_URL = "http://www.aungpyaephyo.xyz/meal_ordering/";
    public static final String API_GET_MEAL_LIST = "getMealsList.php";

    public static final String PARAM_ACCESS_TOKEN = "access_token";

    public static final String ACCESS_TOKEN = "b002c7e1a528b7cb460933fc2875e916";

    //Loader ID
    public static final int MEAL_LIST_LOADER = 1;
    public static final int MEAL_DETAIL_LOADER = 2;
    public static final int MEAL_LIST_LOADER_LISTVIEW = 3;
    public static final int MEAL_LIST_LOADER_GRIDVIEW = 4;


    //Regular Expression for checking email.
    public static final String EMAIL_PATTERN = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";

    public static final String ENCRYPT_MD5 = "MD5";
}
