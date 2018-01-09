package mkworld29.mobile.com.cafemoa.entity;

import java.util.ArrayList;

import mkworld29.mobile.com.cafemoa.item.OptionItem;

/**
 * Created by parkjaemin on 2017. 8. 18..
 */

public class CoffeeOption {

    private int  shots;
    private int  size;
    private int amounts;
    private int pk;
    private ArrayList<Integer> selections;

    public CoffeeOption(int shots, int size, int amounts, int pk,ArrayList<Integer> selections)
    {

        this.shots = shots;
        this.size = size;
        this.amounts = amounts;
        this.pk=pk;
        this.selections=selections;
    }

    public int getAmounts() {
        return amounts;
    }

    public void setAmounts(int amounts) {
        this.amounts = amounts;
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

    public int getPk() {
        return pk;
    }

    public void setPk(int pk) {
        this.pk = pk;
    }

    public ArrayList<Integer> getSelections(){ return selections; }
}
