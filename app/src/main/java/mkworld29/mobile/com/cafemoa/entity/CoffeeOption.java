package mkworld29.mobile.com.cafemoa.entity;

/**
 * Created by parkjaemin on 2017. 8. 18..
 */

public class CoffeeOption {

    private int  shots;
    private int  size;
    private boolean is_cold;
    private boolean is_whipping;
    private int pk;
    private String image_url;

    public CoffeeOption(int shots, int size, boolean is_cold, boolean is_whipping, int pk)
    {

        this.shots = shots;
        this.size = size;
        this.is_cold = is_cold;
        this.is_whipping = is_whipping;
        this.pk=pk;

    }

    public int getShots() {
        return shots;
    }

    public void setShots(int shots) {
        this.shots = shots;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public boolean is_cold() {
        return is_cold;
    }

    public void setIs_cold(boolean is_cold) {
        this.is_cold = is_cold;
    }

    public boolean is_whipping() {
        return is_whipping;
    }

    public void setIs_whipping(boolean is_whipping) {
        this.is_whipping = is_whipping;
    }

    public int getPk() {
        return pk;
    }

    public void setPk(int pk) {
        this.pk = pk;
    }
}
