package mkworld29.mobile.com.cafemoa.retrofit;

/**
 * Created by 이은서 on 2017-04-02.
 */
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by eunseo on 2016-12-09.
 */
public class SharedPreference {
    private static SharedPreference instance=null;
    private SharedPreferences pre;
    private SharedPreferences.Editor editor;
    public static SharedPreference getInstance(Context c){
        if(instance==null) instance=new SharedPreference(c);
        return instance;
    }

    public static SharedPreference getInstance(){
        if(instance==null) return null;
        return instance;
    }

    SharedPreference(Context c){
        pre=c.getSharedPreferences(c.getPackageName(), Activity.MODE_PRIVATE);
        editor=pre.edit();
    }

    public void put(String key, String value){
        editor.putString(key,value);
        editor.commit();
    }
    public String get(String key){
        return pre.getString(key,"");
    }
    public void remove(String key){
        editor.remove(key);
        editor.commit();
    }
}