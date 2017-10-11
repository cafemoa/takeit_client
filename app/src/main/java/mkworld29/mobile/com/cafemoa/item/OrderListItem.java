package mkworld29.mobile.com.cafemoa.item;

/**
 * Created by parkjaemin on 2017. 10. 6..
 */

public class OrderListItem {
    String content;
    String img;
    boolean is_best;

    public OrderListItem(String c, String i, boolean is_best)
    {
        this.content = c;
        this.img = i;
        this.is_best = is_best;
    }

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
}
