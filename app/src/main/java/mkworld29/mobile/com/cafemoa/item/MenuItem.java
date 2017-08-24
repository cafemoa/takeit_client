package mkworld29.mobile.com.cafemoa.item;

/**
 * Created by chmod777 on 2017. 6. 24..
 */

public class MenuItem {
    int image;
    String title;
    int price;

    public int getImage() {
        return this.image;
    }

    public String getTitle() {
        return this.title;
    }

    public int getPrice(){return this.price;}

    public MenuItem(int image, String title,int price) {
        this.image = image;
        this.title = title;
        this.price = price;
    }
}
