package mkworld29.mobile.com.cafemoa.item;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by parkjaemin on 2017. 10. 6..
 */

public class OrderListItem2 implements Parcelable{
    String content;
    String img;
    boolean is_best;
    int pk;
    int type;

    public OrderListItem2(String c, String i, boolean is_best, int type, int pk)
    {
        this.content = c;
        this.img = i;
        this.is_best = is_best;
        this.pk=pk;
        this.type=type;
    }

    protected OrderListItem2(Parcel in) {
        content = in.readString();
        img = in.readString();
        is_best = in.readByte() != 0;
        pk = in.readInt();
        type = in.readInt();
    }

    public static final Creator<OrderListItem2> CREATOR = new Creator<OrderListItem2>() {
        @Override
        public OrderListItem2 createFromParcel(Parcel in) {
            return new OrderListItem2(in);
        }

        @Override
        public OrderListItem2[] newArray(int size) {
            return new OrderListItem2[size];
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
    }
}
