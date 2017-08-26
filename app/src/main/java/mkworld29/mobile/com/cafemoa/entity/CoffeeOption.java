package mkworld29.mobile.com.cafemoa.entity;

/**
 * Created by parkjaemin on 2017. 8. 18..
 */

public class CoffeeOption {

    private String  shots;
    private String  size;
    private boolean is_cold;
    private boolean is_whipping;

    public CoffeeOption(String shots, String size, boolean is_cold, boolean is_whipping)
    {
        this.shots = shots;
        this.size = size;
        this.is_cold = is_cold;
        this.is_whipping = is_whipping;
    }

    public String getShots() {
        return shots;
    }

    public void setShots(String shots) {
        this.shots = shots;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
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
}
