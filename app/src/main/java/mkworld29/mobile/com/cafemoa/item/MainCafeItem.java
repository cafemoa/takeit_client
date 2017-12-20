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

    public MainCafeItem(int pk,String name, String location,String tag, String cafe_image, boolean is_open, int min_time){
        this.name=name;
        this.location=location;
        this.tag=tag;
        this.cafe_image=cafe_image;
        this.is_open=is_open;
        this.pk=pk;
        this.min_time=min_time;
    }
    public int getPk(){ return pk; }
    public String getName(){ return name; }
    public String getLocation(){ return location; }
    public String getTag(){ return tag; }
    public String getImage(){ return cafe_image; }
    public boolean getOpen(){ return is_open; }
    public int getMin_time(){ return min_time; }
}
