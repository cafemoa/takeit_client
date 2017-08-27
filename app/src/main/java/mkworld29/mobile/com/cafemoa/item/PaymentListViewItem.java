package mkworld29.mobile.com.cafemoa.item;

/**
 * Created by chmod777 on 2017. 6. 25..
 */

public class PaymentListViewItem {
    private String content;
    private String price;
    private String time;

    public PaymentListViewItem(String content, String price, String time)
    {

        this.content = content;
        this.price = price;
        this.time = time;
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
