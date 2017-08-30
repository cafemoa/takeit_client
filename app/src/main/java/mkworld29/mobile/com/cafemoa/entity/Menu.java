package mkworld29.mobile.com.cafemoa.entity;

import android.util.Log;

/**
 * Created by chmod777 on 2017. 6. 24..
 */

public class Menu {

    private String name;        // 메뉴 이름
    private String price;          // 메뉴 가격
    private String image;          // 메뉴 이미지
    private int limit;          // 메뉴 수량
    private int pk;
    private int cafe_pk;

    public Menu(String name,String price, String image, int limit, int pk,int cafe_pk)
    {
        this.name = name;
        this.image = image;
        this.price  =price;
        this.limit = limit;
        this.pk = pk;
        this.cafe_pk=cafe_pk;
    }


    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public int getPk() {
        return pk;
    }

    public void setPk(int pk) {
        this.pk = pk;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public String getImage() {
        return image;
    }

    public int getLimit() {
        return limit;
    }

    public int getCafe_pk(){ return cafe_pk; }

    public void setCafe_pk(){ this.cafe_pk=cafe_pk; }
}

