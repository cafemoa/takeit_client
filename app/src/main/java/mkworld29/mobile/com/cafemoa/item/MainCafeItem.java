package mkworld29.mobile.com.cafemoa.item;

/**
 * Created by ABCla on 2017-10-07.
 */

public class MainCafeItem {
    private int pk;
    private String name;
    private String location;
    private String tag;
    private String cafe_image;
    private boolean is_open;
    private int min_time;
    private int coupon_num;
    private int coupon_price;

    public MainCafeItem(int pk,String name, String location,String tag, String cafe_image, boolean is_open, int min_time,int coupon_num,int coupon_price){
        this.name=name;
        this.location=location;
        this.tag=tag;
        this.cafe_image=cafe_image;
        this.is_open=is_open;
        this.pk=pk;
        this.min_time=min_time;
        this.coupon_num=coupon_num;
        this.coupon_price=coupon_price;
    }
    public int getPk(){ return pk; }
    public String getName(){ return name; }
    public String getLocation(){ return location; }
    public String getTag(){ return tag; }
    public String getImage(){ return cafe_image; }
    public boolean getOpen(){ return is_open; }
    public int getMin_time(){ return min_time; }
    public int getCoupon_num(){ return coupon_num; }
    public int getCoupon_price() { return coupon_price; }
}
