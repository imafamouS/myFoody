package com.fdsa.infamous.myfoody.ui.util.adapter;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.fdsa.infamous.myfoody.R;
import com.fdsa.infamous.myfoody.ui.util.bean.Province;

import java.util.List;

/**
 * Created by FDSA on 4/3/2017.
 */

public class ChooseProvinceAdapter extends BaseAdapter {

    static Context context;
    public int indexSelected = -1;
    IOnSetDefaultProvince onSetDefaultProvince;
    private List<Province> provinceList;
    private List<Province> provinceListDisplay;
    private Province currentProvince;

    public ChooseProvinceAdapter(Context context, List<Province> provinceList, Province currentProvince, IOnSetDefaultProvince onSetDefaultProvince) {
        this.context=context;
        this.provinceList=provinceList;
        this.provinceListDisplay = provinceList;
        this.currentProvince=currentProvince;
        this.onSetDefaultProvince=onSetDefaultProvince;
        this.indexSelected=getIndexCurrentProvince();


    }

    private int getIndexCurrentProvince() {
        int index = -1;
        for (int i = 0; i < provinceListDisplay.size(); i++) {
            if (provinceListDisplay.get(i).getIdProvince().equals(currentProvince.getIdProvince())) {
                index = i;
                break;
            }
        }
        return index;
    }

    @Override
    public int getCount() {
        return provinceListDisplay.size();
    }

    @Override
    public Province getItem(int position) {
        return provinceListDisplay.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getViewTypeCount() {

        return getCount();
    }

    @Override
    public int getItemViewType(int position) {

        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ChooseProvinceViewHolder holder;

        if (convertView == null) {
            convertView = LayoutInflater.from(this.context).inflate(R.layout.choose_province_item, parent, false);
            holder = new ChooseProvinceViewHolder(convertView,onSetDefaultProvince);
            convertView.setTag(holder);
        } else {
            holder = (ChooseProvinceViewHolder) convertView.getTag();
        }
        Province item = this.provinceListDisplay.get(position);
        int indexCurrentProvince = getIndexCurrentProvince();
        holder = (ChooseProvinceViewHolder) convertView.getTag();

        if(position==indexCurrentProvince){
            holder.image_view_check_status.setVisibility(View.VISIBLE);
        }
        if(position==indexSelected){
            if(position==indexCurrentProvince){
                holder.text_view_province_name.setTextColor(ContextCompat.getColor(context, R.color.color_text_default_choose_province));
                holder.text_view_set_default.setVisibility(View.GONE);
                holder.image_view_check_status.setVisibility(View.VISIBLE);
            }else{
                holder.image_view_check_status.setVisibility(View.VISIBLE);
                holder.text_view_set_default.setVisibility(View.VISIBLE);
            }

        }else{
            if(position==indexCurrentProvince){
                holder.text_view_province_name.setTextColor(ContextCompat.getColor(context, R.color.color_text_default_choose_province));
                holder.text_view_set_default.setVisibility(View.GONE);
                holder.image_view_check_status.setVisibility(View.GONE);
            }else{
                holder.image_view_check_status.setVisibility(View.GONE);
                holder.text_view_set_default.setVisibility(View.GONE);
            }
        }

        holder.text_view_province_name.setText(item.getTitleProvince());

        return convertView;
    }

    public interface IOnSetDefaultProvince {
        void onSetDefaultProvince();
    }

    public class ChooseProvinceViewHolder implements View.OnClickListener {
        public ImageView image_view_check_status;
        public TextView text_view_province_name;
        public TextView text_view_set_default;
        View item;
        IOnSetDefaultProvince onSetDefaultProvince;
        public ChooseProvinceViewHolder(View view, IOnSetDefaultProvince onSetDefaultProvince) {
            item = view;
            this.image_view_check_status = (ImageView) view.findViewById(R.id.image_view_check_status);
            this.text_view_province_name = (TextView) view.findViewById(R.id.text_view_province_name);
            this.text_view_set_default=(TextView) view.findViewById(R.id.text_view_set_default);
            this.onSetDefaultProvince=onSetDefaultProvince;

            text_view_set_default.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onSetDefaultProvince.onSetDefaultProvince();
        }
    }
}
