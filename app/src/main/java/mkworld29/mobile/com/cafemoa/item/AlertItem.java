package mkworld29.mobile.com.cafemoa.item;

/**
 * Created by ABCla on 2017-10-07.
 */

public class AlertItem {
    String cafe_name;
    String content;
    public AlertItem(String cafe_name, String content){
        this.cafe_name=cafe_name;
        this.content=content;
    }
    public String getCafe_name(){return cafe_name;}
    public String getContent(){return content;}
}
