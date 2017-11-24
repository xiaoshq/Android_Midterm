package com.xiaoshq.sqltest;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;


/**
 * Created by ivan on 2017/11/22.
 */

public class DataListAdapter extends BaseAdapter {
    private Context context;
    private List<Data> dataList;

    public DataListAdapter(Context context, List<Data> dataList) {
        this.context = context;
        this.dataList = dataList;
    }

    @Override
    public int getCount() {
        return dataList.size();
    }

    @Override
    public Object getItem(int i) {
        return dataList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        Data data = dataList.get(i);
        if (data == null) return null;
        ViewHolder holder = null;
        if (view != null) {
            holder = (ViewHolder) view.getTag();
        } else {
//            view = LayoutInflater.from(context).inflate(R.layout.activity_main, null);
//
//            holder = new ViewHolder();
//            holder.dataNameTV = (TextView) view.findViewById(R.id.dataNameTV);
//            holder.dataSexTV = (TextView) view.findViewById(R.id.dataSexTV);
//            holder.dataImgIV = (ImageView) view.findViewById(R.id.dataImageIv);
//            holder.dataRegionTV = (TextView) view.findViewById(R.id.dataRegionTV);
//            holder.dataBornTV = (TextView) view.findViewById(R.id.dataBornTV);
//            holder.dataDeadTV = (TextView) view.findViewById(R.id.dataDeadTV);
//            holder.dataMasterTV = (TextView) view.findViewById(R.id.dataMasterTV);

            view.setTag(holder);
        }
        holder.dataIdTV.setText(data.id);
        holder.dataNameTV.setText(data.name);
        holder.dataImgIV.setImageResource(data.img);
        holder.dataSexTV.setText(data.sex);
        holder.dataRegionTV.setText(data.region);
        holder.dataBornTV.setText(data.born);
        holder.dataDeadTV.setText(data.dead);
        holder.dataMasterTV.setText(data.master);

        return view;
    }

    @Override
    public boolean isEnabled(int position) {
        return false;
    }

    public static class ViewHolder {
        public TextView dataIdTV;
        public TextView dataNameTV;
        public TextView dataSexTV;
        public ImageView dataImgIV;
        public TextView dataRegionTV;
        public TextView dataBornTV;
        public TextView dataDeadTV;
        public TextView dataMasterTV;
    }
}
