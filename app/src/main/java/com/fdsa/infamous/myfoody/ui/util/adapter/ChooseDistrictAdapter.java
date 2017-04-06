package com.fdsa.infamous.myfoody.ui.util.adapter;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.fdsa.infamous.myfoody.R;
import com.fdsa.infamous.myfoody.ui.util.bean.District;

import java.util.List;

/**
 * Created by FDSA on 4/3/2017.
 */

public class ChooseDistrictAdapter extends BaseAdapter {

    static Context context;
    List<District> districtList;

    public ChooseDistrictAdapter(Context context, List<District> districtList) {
        this.context = context;
        this.districtList = districtList;
    }

    @Override
    public int getCount() {
        return districtList.size();
    }

    @Override
    public District getItem(int position) {
        return districtList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ChooseDistrictViewHolder holder;
        District item = this.districtList.get(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(this.context).inflate(R.layout.choose_district_group_item, parent, false);
            holder = new ChooseDistrictViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ChooseDistrictViewHolder) convertView.getTag();
        }
        holder.renderData(item);
        return convertView;
    }
    @Override
    public int getViewTypeCount() {

        return getCount();
    }

    @Override
    public int getItemViewType(int position) {

        return position;
    }

    static class ChooseDistrictViewHolder {
        View item;
         TextView text_view_district_name;
         TextView text_view_num_of_street;

        public ChooseDistrictViewHolder(View view) {
            item = view;
            this.text_view_district_name = (TextView) view.findViewById(R.id.text_view_district_name);
            this.text_view_num_of_street = (TextView) view.findViewById(R.id.text_view_num_of_street);
        }
        public void renderData(District District){
            if(District.isSelected()){
                this.text_view_district_name.setTextColor(ContextCompat.getColor(context,R.color.colorPrimary));
            }
            this.text_view_district_name.setText(District.getTitleDistrict());
            this.text_view_num_of_street.setText(District.getNumofStreet()+ " Đường");
        }
    }

}
