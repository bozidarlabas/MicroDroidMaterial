package com.bozidar.microdroid.searchview;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.bozidar.microdroid.R;

import java.util.ArrayList;

/**
 * Created by macbook on 06.10.2015..
 */
public class SearchAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<String> data;
    private LayoutInflater layoutInflater;
    private boolean isFilterList;


    public SearchAdapter(Context context, ArrayList<String> data, boolean filterList){
        this.context = context;
        this.data = data;
        this.isFilterList = filterList;
    }

    /**
     * Method for updating list
     * @param filterList
     * @param isFilterList
     */
    public void updateList(ArrayList<String> filterList, boolean isFilterList){
        this.data = filterList;
        this.isFilterList = isFilterList;
        notifyDataSetChanged();
    }


    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        ViewHolder holder;

        if(v == null){
            holder = new ViewHolder();
            layoutInflater = (LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            v = layoutInflater.inflate(R.layout.item_search, parent, false);
            holder.txtData = (TextView)v.findViewById(R.id.txt_data);
            v.setTag(holder);
        }else{
            holder = (ViewHolder) v.getTag();
        }

        holder.txtData.setText(data.get(position));


        Drawable searchDrawable, recentDrawable;

        if(Build.VERSION.SDK_INT > 21){
            searchDrawable = context.getResources().getDrawable(R.drawable.ic_magnify_grey600_24dp, null);
            recentDrawable = context.getResources().getDrawable(R.drawable.ic_backup_restore_grey600_24dp, null);
        }else{
            searchDrawable = context.getResources().getDrawable(R.drawable.ic_magnify_grey600_24dp);
            recentDrawable = context.getResources().getDrawable(R.drawable.ic_backup_restore_grey600_24dp);
        }

        if(isFilterList){
            holder.txtData.setCompoundDrawablesWithIntrinsicBounds(searchDrawable, null, null, null);
        }else{
            holder.txtData.setCompoundDrawablesWithIntrinsicBounds(recentDrawable, null, null, null);
        }


        return v;
    }

    private class ViewHolder{
        TextView txtData;
    }




}
