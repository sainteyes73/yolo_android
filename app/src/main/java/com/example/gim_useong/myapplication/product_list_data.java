package com.example.gim_useong.myapplication;

import android.graphics.drawable.Drawable;

public class product_list_data {
    public String product_name;
    public String buy_date;
    public String sell_by_date;
    public Drawable Drawbleicon;

    public Drawable getIcon(){
        return Drawbleicon;
    }
    public void setIcon(Drawable icon){
        this.Drawbleicon = icon;
    }
    public String getName(){return product_name;    }
    public void setName(String name){
        this.product_name = name;
    }
    public String getBuy_date(){
        return buy_date;
    }
    public void setBuy_date(String b_date){
        this.buy_date = b_date;
    }
    public String getSell_by_date(){
        return sell_by_date;
    }
    public void setSell_by_date(String sell_date){
        this.sell_by_date = sell_date;
    }
}