package mkworld29.mobile.com.cafemoa.entity;

/**
 * Created by parkjaemin on 2018. 1. 10..
 */

public class MenuOption {

    private String title;
    private int price;
    private boolean is_check;

    public MenuOption(String title, int price, boolean is_check) {
        this.title = title;
        this.price = price;
        this.is_check = is_check;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public boolean isIs_check() {
        return is_check;
    }

    public void setIs_check(boolean is_check) {
        this.is_check = is_check;
    }
}
