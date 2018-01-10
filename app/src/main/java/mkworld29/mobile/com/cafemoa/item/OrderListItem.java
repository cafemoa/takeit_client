package mkworld29.mobile.com.cafemoa.item;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

import mkworld29.mobile.com.cafemoa.retrofit.RetrofitConnection;

/**
 * Created by parkjaemin on 2017. 10. 6..
 */

public class OrderListItem{
    private String name;
    private String image;
    private String price;
    private boolean is_best;
    private int pk;
    private int type;

    public OrderListItem(String c, String i, String price, boolean is_best, int type, int pk)
    {
        this.name = c;
        this.image = i;
        this.is_best = is_best;
        this.pk=pk;
        this.type=type;
        this.price=price;
    }

    public String getContent() {
        return name;
    }

    public void setContent(String content) {
        this.name = content;
    }

    public String getImg() {
        return image;
    }

    public void setImg(String img) {
        this.image = img;
    }

    public boolean is_best() {
        return is_best;
    }

    public void setIs_best(boolean is_best) {
        this.is_best = is_best;
    }
    public void setPk(int pk){ this.pk=pk; }
    public int getPk(){ return pk; }
    public int getType(){return type;}
    public String getPrice(){ return price; }

}
