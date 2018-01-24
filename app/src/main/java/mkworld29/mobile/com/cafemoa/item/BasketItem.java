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
    String image_url;
    String cafe_name;
    String content;
    String price;
    String time;
    int amount;
    CoffeeOption option;

    public BasketItem(String image,
                      String cafe_name,
                      String content,
                      String price,
                      String time,
                      int amount,
                      CoffeeOption option) {
        this.image_url = image;
        this.cafe_name = cafe_name;
        this.content = content;
        this.price = price;
        this.time = time;
        this.amount = amount;
        this.option = option;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
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

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
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

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

}
