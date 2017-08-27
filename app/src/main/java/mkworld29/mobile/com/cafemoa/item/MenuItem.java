package mkworld29.mobile.com.cafemoa.item;

/**
 * Created by chmod777 on 2017. 6. 24..
 */

public class MenuItem {
    int image;
    String title;
    String price;

    public int getImage() {
        return this.image;
    }

    public String getTitle() {
        return this.title;
    }

    public String getPrice(){return this.price;}

    public MenuItem(int image, String title,String price) {
        this.image = image;
        this.title = title;
        this.price = price;
    }
}
