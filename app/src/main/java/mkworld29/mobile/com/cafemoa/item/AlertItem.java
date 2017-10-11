package mkworld29.mobile.com.cafemoa.item;

/**
 * Created by ABCla on 2017-10-07.
 */

public class AlertItem {
    private String cafe_name;
    private String content;
    private boolean is_event;
    public AlertItem(String cafe_name, String content,boolean is_event){
        this.cafe_name=cafe_name;
        this.content=content;
        this.is_event=is_event;
    }
    public String getCafe_name(){return cafe_name;}
    public String getContent(){return content;}
    public boolean getIs_event(){return is_event;}
}
