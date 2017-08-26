package mkworld29.mobile.com.cafemoa.item;

import com.google.gson.Gson;

import org.json.JSONObject;

import java.util.Random;

import mkworld29.mobile.com.cafemoa.entity.CoffeeOption;

/**
 * Created by chmod777 on 2017. 6. 24..
 */

public class BasketItem {
    String id;
    String image;
    String cafe_name;
    String content;
    String price;
    CoffeeOption option;

    public BasketItem(String image,
                      String cafe_name,
                      String content,
                      String price,
                      CoffeeOption option) {
        this.image = image;
        this.cafe_name = cafe_name;
        this.content = content;
        this.price = price;
        this.option = option;

    }


    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getCafe_name() {
        return cafe_name;
    }

    public void setCafe_name(String cafe_name) {
        this.cafe_name = cafe_name;
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


    public CoffeeOption getOption() {
        return option;
    }

    public void setOption(CoffeeOption option) {
        this.option = option;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}
