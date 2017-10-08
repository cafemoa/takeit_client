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
    private boolean is_event;

    public MainCafeItem(int pk,String name, String location,String tag, String cafe_image, boolean is_event){
        this.name=name;
        this.location=location;
        this.tag=tag;
        this.cafe_image=cafe_image;
        this.is_event=is_event;
        this.pk=pk;
    }
    public int getPk(){ return pk; }
    public String getName(){ return name; }
    public String getLocation(){ return location; }
    public String getTag(){ return tag; }
    public String getImage(){ return cafe_image; }
    public boolean getEvent(){ return is_event; }
}
