package mkworld29.mobile.com.cafemoa.item;

import java.util.ArrayList;

/**
 * Created by ABCla on 2018-01-04.
 */

public class OptionItem{
    private String content;
    private int pk;
    private ArrayList<Selection> selections;
    private boolean one_selector;
    public class Selection{
        private String content;
        private int pk;
        private int add_price;
        public String getContent() { return content; }
        public int getPk(){ return pk; }
        public int getAdd_price() {return add_price; }
    }

    public boolean getOne_selector(){ return one_selector; }
    public int getPk() {return pk;}
    public String getContent(){ return content; }
    public ArrayList<Selection> getSelections() {return selections; }

}
