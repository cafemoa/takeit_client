package mkworld29.mobile.com.cafemoa.prefs;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import mkworld29.mobile.com.cafemoa.item.BasketItem;

/**
 * Created by ABCla on 2017-10-12.
 */

public class SignupPref {
    public static SignupPref mInstance;

    public static SignupPref getInstance(Context ctx)
    {
        if(mInstance != null)
            return mInstance;
        else
        {
            mInstance = new SignupPref(ctx);
            return mInstance;
        }
    }

    private static final String PREFS_NAME = "mkWorld29.mobile.com.cafemoa.SignupPrefs";
    private static SharedPreferences settings;
    private static SharedPreferences.Editor editor;

    public SignupPref(Context ctx){
        if(settings == null){
            settings = ctx.getSharedPreferences(PREFS_NAME,
                    Context.MODE_PRIVATE );
        }

        editor = settings.edit();
    }

    public void addString(String key, String add)
    {
        editor.putString(key,add);
        editor.commit();
    }

    public String getString(String target)
    {
        return settings.getString(target,"");
    }

    public void addInfo(String key, String value)
    {
        editor.putString(key, value);
        editor.commit();
    }

    public void deleteInfo(String id)
    {
        editor.remove(id);
        editor.commit();
    }

    public String getInfo(String id)
    {
        String info = settings.getString(id, "");
        return info;
    }

    public void removeAllInfo()
    {
        editor.clear();
        editor.commit();
    }

}
