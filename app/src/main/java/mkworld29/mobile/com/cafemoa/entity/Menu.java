package mkworld29.mobile.com.cafemoa.entity;

/**
 * Created by chmod777 on 2017. 6. 24..
 */

public class Menu {

    private String name;        // 메뉴 이름
    private int price;          // 메뉴 가격
    private String image;          // 메뉴 이미지
    private int limit;          // 메뉴 수량

    public Menu()
    {
        this.name = null;
        this.image = null;
        this.price = this.limit = 0;
    }

    public Menu(String name,int price, String image, int limit)
    {
        this.name = name;
        this.price = price;
        this.image = image;
        this.limit = limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public String getImage() {
        return image;
    }

    public int getLimit() {
        return limit;
    }
}
