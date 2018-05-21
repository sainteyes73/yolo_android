package com.example.gim_useong.myapplication;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class productAdapter  extends BaseAdapter{
    private ArrayList<product_list_data> productItems = new ArrayList<>();

    @Override
    public int getCount(){
        return productItems.size();
    }

    @Override
    public product_list_data getItem(int position){
        return productItems.get(position);
    }

    @Override
    public long getItemId(int position){
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){

        Context context = parent.getContext();

        // row_listview 레이아웃을 inflate하여 convertView 참조
        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.row_listview, parent, false);
        }

        // row_listview에 정의한 위젯에 대한 참조
        ImageView p_img = (ImageView)convertView.findViewById(R.id.product_image);
        TextView p_name = (TextView)convertView.findViewById(R.id.product_name);
        TextView p_sell = (TextView)convertView.findViewById(R.id.product_sell_by_date);
        TextView p_buy = (TextView)convertView.findViewById(R.id.product_buy_date);

        product_list_data productData = getItem(position);

        p_img.setImageDrawable(productData.getIcon());
        p_name.setText(productData.getName());
        p_sell.setText(productData.getSell_by_date());
        p_buy.setText(productData.getBuy_date());

        return convertView;
    }
    // 물품 데이터 추가를 위한 함수
    public void addItem(Drawable img, String name, String s_date, String b_date){
        product_list_data productListData = new product_list_data();

        productListData.setIcon(img);
        productListData.setName(name);
        productListData.setSell_by_date(s_date);
        productListData.setBuy_date(b_date);

        productItems.add(productListData);
    }
}
