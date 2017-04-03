package com.fdsa.infamous.myfoody.ui.util.adapter;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.fdsa.infamous.myfoody.Global.GlobalStaticData;
import com.fdsa.infamous.myfoody.R;
import com.fdsa.infamous.myfoody.ui.util.bean.District;
import com.fdsa.infamous.myfoody.ui.util.bean.MenuBarItem;
import com.fdsa.infamous.myfoody.ui.util.bean.Province;

import java.util.List;

/**
 * Created by FDSA on 4/3/2017.
 */

public class ChooseProvinceAdapter extends BaseAdapter {

    static Context context;
    List<Province> provinceList;
    Province currentProvince;

    public ChooseProvinceAdapter(Context context,List<Province> provinceList,Province currentProvince) {
        this.context=context;
        this.provinceList=provinceList;
        this.currentProvince=currentProvince;

    }

    @Override
    public int getCount() {
        return provinceList.size();
    }

    @Override
    public Province getItem(int position) {
        return provinceList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    ImageView image_view_check_status;
    TextView text_view_province_name;
    TextView text_view_set_default;
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ChooseProvinceViewHolder holder;
        Province item = this.provinceList.get(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(this.context).inflate(R.layout.choose_province_item, parent, false);
            holder = new ChooseProvinceViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ChooseProvinceViewHolder) convertView.getTag();
        }
        holder = (ChooseProvinceViewHolder) convertView.getTag();
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

    static class ChooseProvinceViewHolder{
        View item;
        ImageView image_view_check_status;
        TextView text_view_province_name;
        TextView text_view_set_default;

        public ChooseProvinceViewHolder(View view) {
            item = view;
            this.image_view_check_status = (ImageView) view.findViewById(R.id.image_view_check_status);
            this.text_view_province_name = (TextView) view.findViewById(R.id.text_view_province_name);
            this.text_view_set_default=(TextView) view.findViewById(R.id.text_view_set_default);
        }
        public void renderData(Province province){
            if(province.getIdProvince().equals("0")){
                this.image_view_check_status.setVisibility(View.VISIBLE);
                this.text_view_province_name.setTextColor(ContextCompat.getColor(context,R.color.color_text_default_choose_province));
            }
            this.text_view_province_name.setText(province.getIdProvince());
        }
    }
}
