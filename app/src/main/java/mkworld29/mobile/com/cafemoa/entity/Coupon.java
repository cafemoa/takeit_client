package mkworld29.mobile.com.cafemoa.entity;

/**
 * Created by chmod777 on 2017. 6. 25..
 */

public class Coupon{

    String cafe_name;
    String cafe_address;
    int cafe_logo;
    int sum;
    int pk;

    public int getCafe_logo() {
        return cafe_logo;
    }

    public void setCafe_logo(int cafe_logo) {
        this.cafe_logo = cafe_logo;
    }

    public Coupon(String cafe_name, String cafe_address, int cafe_logo, int sum,int pk)
    {
        this.cafe_name = cafe_name;
        this.cafe_address = cafe_address;
        this.cafe_logo = cafe_logo;
        this.sum = sum;
        this.pk = pk;
    }

    public int getPk() {
        return pk;
    }

    public void setPk(int pk) {
        this.pk = pk;
    }

    public void setCafe_name(String cafe_name) {
        this.cafe_name = cafe_name;
    }

    public void setCafe_address(String cafe_address) {
        this.cafe_address = cafe_address;
    }

    public void setSum(int sum) {
        this.sum = sum;
    }

    public String getCafe_name() {
        return cafe_name;
    }

    public String getCafe_address() {
        return cafe_address;
    }

    public int getSum() {
        return sum;
    }
}
