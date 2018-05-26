package com.example.gim_useong.myapplication;
import android.content.Context;
import android.graphics.Color;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gim_useong.myapplication.models.ListItem;
import com.example.gim_useong.myapplication.viewholder.ViewHolder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by user on 2017-10-23.
 */

public class ListAdapter extends BaseAdapter {

    public  ArrayList<ListItem> listViewItemList = new ArrayList<ListItem>();
    private ArrayList<ListItem> filteredItemList = listViewItemList;

    public static String name,count,price;

    @Override
    public int getCount() {
        return filteredItemList.size();
    }

    @Override
    public Object getItem(int position) {
        return filteredItemList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        final Context context = parent.getContext();
        final ViewHolder holder;

        // "listview_item" Layout을 inflate하여 convertView 참조 획득.
        if (convertView == null) {
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.listview_list, parent, false);
            holder.editText1 = (EditText)convertView.findViewById(R.id.editText1);

            convertView.setTag(holder);
        }else{
            holder = (ViewHolder)convertView.getTag();
        }

        holder.ref = position;

        // 화면에 표시될 View(Layout이 inflate된)으로부터 위젯에 대한 참조 획득
        final EditText editText1 = (EditText)convertView.findViewById(R.id.editText1);

        // Data Set(filteredItemList)에서 position에 위치한 데이터 참조 획득
        final ListItem listViewItem = filteredItemList.get(position);

        holder.editText1.setText(listViewItem.getName());


        name+=holder.editText1.getText()+"#";

        holder.editText1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                filteredItemList.get(holder.ref).setName(s.toString());
            }
        });


        return convertView;
    }

    public void addItem(String name) {
        ListItem item = new ListItem();
        item.setName(name);

        listViewItemList.add(item);
    }

    public void delItem() {
        if (listViewItemList.size() < 1) {
        } else {
            listViewItemList.remove(listViewItemList.size() - 1);
        }
    }
}

