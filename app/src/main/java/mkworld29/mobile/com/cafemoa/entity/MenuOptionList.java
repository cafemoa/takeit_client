package mkworld29.mobile.com.cafemoa.entity;

import java.util.List;

import mkworld29.mobile.com.cafemoa.adapter.CoffeeOptionAdapter;

/**
 * Created by parkjaemin on 2018. 1. 10..
 */

public class MenuOptionList {
    private String content;
    private CoffeeOptionAdapter optionAdapter;
    public MenuOptionList(String content,CoffeeOptionAdapter optionAdapter) {
        this.content=content;
        this.optionAdapter=optionAdapter;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public CoffeeOptionAdapter getOptions(){ return optionAdapter; }
}
