package mkworld29.mobile.com.cafemoa.item;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by parkjaemin on 2017. 10. 6..
 */

public class OrderListItem implements Parcelable{
    String content;
    String img;
    String price;
    boolean is_best;
    int pk;
    int type;

    public OrderListItem(String c, String i, String price, boolean is_best, int type, int pk)
    {
        this.content = c;
        this.img = i;
        this.is_best = is_best;
        this.pk=pk;
        this.type=type;
        this.price=price;
    }

    protected OrderListItem(Parcel in) {
        content = in.readString();
        img = in.readString();
        is_best = in.readByte() != 0;
        pk = in.readInt();
        type = in.readInt();
        price=in.readString();
    }

    public static final Creator<OrderListItem> CREATOR = new Creator<OrderListItem>() {
        @Override
        public OrderListItem createFromParcel(Parcel in) {
            return new OrderListItem(in);
        }

        @Override
        public OrderListItem[] newArray(int size) {
            return new OrderListItem[size];
        }
    };

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(content);
        dest.writeString(img);
        dest.writeByte((byte) (is_best ? 1 : 0));
        dest.writeInt(pk);
        dest.writeInt(type);
        dest.writeString(price);
    }
}
