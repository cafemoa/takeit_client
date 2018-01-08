package mkworld29.mobile.com.cafemoa.item;

import java.util.ArrayList;

/**
 * Created by ABCla on 2018-01-04.
 */

public class OptionItem{
    private String content;
    private int pk;
    ArrayList<Selection> selections;
    public class Selection{
        private String content;
        private int pk;
        private int add_price;
    }
}
