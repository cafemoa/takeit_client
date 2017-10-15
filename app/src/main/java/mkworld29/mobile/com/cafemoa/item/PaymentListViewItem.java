package mkworld29.mobile.com.cafemoa.item;

/**
 * Created by chmod777 on 2017. 6. 25..
 */

public class PaymentListViewItem {
    private String content;
    private String cafe_name;
    private String cafe_address;
    private String price;
    private String time;

    public PaymentListViewItem(String content, String cafe_name, String cafe_address,String price, String time)
    {
        this.cafe_name = cafe_name;
        this.cafe_address = cafe_address;
        this.content = content;
        this.price = price;
        this.time = time;
    }

    public String getCafe_name() {
        return cafe_name;
    }

    public void setCafe_name(String cafe_name) {
        this.cafe_name = cafe_name;
    }

    public String getCafe_address() {
        return cafe_address;
    }

    public void setCafe_address(String cafe_address) {
        this.cafe_address = cafe_address;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
