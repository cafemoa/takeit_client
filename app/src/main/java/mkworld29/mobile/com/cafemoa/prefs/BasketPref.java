package mkworld29.mobile.com.cafemoa.prefs;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import mkworld29.mobile.com.cafemoa.item.BasketItem;
import mkworld29.mobile.com.cafemoa.retrofit.SharedPreference;

/**
 * Created by parkjaemin on 2017. 8. 24..
 */

public class BasketPref {
    public static BasketPref mInstance;

    /** @args Context
     *
     * */
    public static BasketPref getInstance(Context ctx)
    {
        if(mInstance != null)
            return mInstance;
        else
        {
            mInstance = new BasketPref(ctx);
            return mInstance;
        }
    }

    /** This application's preferences label */
    private static final String PREFS_NAME = "mkWorld29.mobile.com.cafemoa.BasketPrefs";

    /** This is basket item's current progress id number */
    private static final String PREFS_PROGRESS = "mkWorld29.mobile.com.cafemoa.Progress";

    /** This is basket item's current storage id array */
    private static final String PREFS_CURRENT_STORAGE= "mkWorld29.mobile.com.cafemoa.CurrentStorage";

    /** This application's preferences */
    private static SharedPreferences settings;

    /** This application's settings editor*/
    private static SharedPreferences.Editor editor;

    /** Constructor takes an android.content.Context argument*/
    public BasketPref(Context ctx){
        if(settings == null){
            settings = ctx.getSharedPreferences(PREFS_NAME,
                    Context.MODE_PRIVATE );
        }

       /**
        * Get a SharedPreferences editor instance.
        * SharedPreferences ensures that updates are atomic
        * and non-concurrent
        */
        editor = settings.edit();

        editor.remove("0");

        if(settings.getString(PREFS_PROGRESS,"").equals(""))
            editor.putString(PREFS_PROGRESS,"");

        if(settings.getString(PREFS_CURRENT_STORAGE,"").equals(""))
            editor.putString(PREFS_CURRENT_STORAGE,"");
    }

    public void addBasket(BasketItem item)
    {
        if(item == null)
            return ;


        String id = String.valueOf(Integer.valueOf(settings.getString(PREFS_PROGRESS,"0"))+1);

        item.setId(id);

        Gson gson = new Gson();
        String basket_json = gson.toJson(item);


        editor.putString(id, basket_json);
        editor.putString(PREFS_PROGRESS, id);
        editor.putString(PREFS_CURRENT_STORAGE, settings.getString(PREFS_CURRENT_STORAGE,"")+id+"_");
        editor.commit();

    }

    public void deleteBasket(String id)
    {
        if(id == null) return;

        editor.remove(id);

        String current_storage = settings.getString(PREFS_CURRENT_STORAGE,"");

        String[] split_storage = getSplitPrefsCurrentStorage();

        String new_current_storage = "";

        for(int i=0;i<split_storage.length;i++)
        {
            if(!split_storage[i].equals(id))
                new_current_storage += split_storage[i]+"_";

        }

        editor.putString(PREFS_CURRENT_STORAGE,new_current_storage);
        editor.commit();
    }

    public BasketItem getBasket(String id)
    {
        Gson gson = new Gson();

        String basket_json = settings.getString(id, "");

        BasketItem basket = gson.fromJson(basket_json, BasketItem.class);

        return basket;
    }

    public int getSize()
    {
        String current_storage = settings.getString(PREFS_CURRENT_STORAGE,"");

        String[] split_storage = current_storage.split("_");

        return split_storage.length;
    }

    public String[] getSplitPrefsCurrentStorage()
    {
        String[] a =  settings.getString(PREFS_CURRENT_STORAGE,"").split("_");

        List<String> list = new ArrayList<String>();

        for(int i=0;i<a.length;i++)
        {
            if(a[i] != null )
                list.add(a[i]);
        }

        Object[] temp = list.toArray();

        String[] new_string = new String[temp.length];

        int i= 0;

        for(Object o : temp)
            new_string[i++] = (String)o;

        return new_string;
    }
}
