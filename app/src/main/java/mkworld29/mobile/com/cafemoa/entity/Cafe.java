package mkworld29.mobile.com.cafemoa.entity;

import android.util.Log;

import java.util.List;

/**
 * Created by chmod777 on 2017. 6. 24..
 */

public class Cafe {

    private String cafe_name;
    private int menu_size;
    private List<Menu> menus;
    private int pk;
    public Cafe()
    {
        this(null,0,null,0);
    }

    public Cafe(String cafe_name, int menu_size, List<Menu> menus, int pk)
    {
        this.cafe_name = cafe_name;
        this.menu_size = menu_size;
        this.menus = menus;
        this.pk=pk;
    }

    public void setCafe_name(String cafe_name) {
        this.cafe_name = cafe_name;
    }

    public void setMenu_size(int menu_size) {
        this.menu_size = menu_size;
    }

    public void setMenus(List<Menu> menus) {
        this.menus = menus;
    }

    public String getCafe_name() {
        return cafe_name;
    }

    public int getMenu_size() {
        return menu_size;
    }

    public List<Menu> getMenus() {
        return menus;
    }

    public void setPk(int pk) { this.pk = pk; }

    public int getPk() {return pk;}


}
