package mkworld29.mobile.com.cafemoa.item;

/**
 * Created by ABCla on 2017-10-07.
 */

public class MainCafeItem {
    private String name;
    private String location;
    private String tag;
    private int cafe_image;
    private boolean is_event;

    public MainCafeItem(String name, String location,String tag, int cafe_image, boolean is_event){
        this.name=name;
        this.location=location;
        this.tag=tag;
        this.cafe_image=cafe_image;
        this.is_event=is_event;
    }

    public String getName(){ return name; }
    public String getLocation(){ return location; }
    public String getTag(){ return tag; }
    public int getImage(){ return cafe_image; }
    public boolean getEvent(){ return is_event; }
}
