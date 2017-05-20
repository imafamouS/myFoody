package com.fdsa.infamous.myfoody.ui.menu.adapter;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.fdsa.infamous.myfoody.R;
import com.fdsa.infamous.myfoody.common.bean_F2.ProvinceBean;
import com.fdsa.infamous.myfoody.common.myinterface.IOnSetDefaultProvince;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by FDSA on 4/3/2017.
 */

public class ChooseProvinceAdapter extends BaseAdapter {

    static Context context;
    public int indexSelected = -1;
    IOnSetDefaultProvince onSetDefaultProvince;
    private List<ProvinceBean> provinceBeanList;
    private List<ProvinceBean> provinceBeanListDisplay;
    private ProvinceBean currentProvinceBean;

    //Hàm khởi tạo
    public ChooseProvinceAdapter(Context context, List<ProvinceBean> provinceBeanList, ProvinceBean currentProvinceBean, IOnSetDefaultProvince onSetDefaultProvince) {
        this.context = context;
        if (provinceBeanList != null) {
            this.provinceBeanList = provinceBeanList;
            this.provinceBeanListDisplay = provinceBeanList;
        } else {
            this.provinceBeanList = new ArrayList<>();
            this.provinceBeanListDisplay = this.provinceBeanList;
        }

        this.currentProvinceBean = currentProvinceBean;
        this.onSetDefaultProvince = onSetDefaultProvince;
        this.indexSelected = getIndexCurrentProvince();


    }

    //Hàm lấy vị trí hiện tại của tỉnh hiện tại
    private int getIndexCurrentProvince() {
        int index = -1;
        for (int i = 0; i < provinceBeanListDisplay.size(); i++) {
            if (provinceBeanListDisplay.get(i).getId().equals(currentProvinceBean.getId())) {
                index = i;
                break;
            }
        }
        return index;
    }

    /**
     * Hàm trả về số lượng phần tử của adapter
     *
     * @return
     */
    @Override
    public int getCount() {
        return provinceBeanListDisplay.size();
    }

    /**
     * Hàm trả về tỉnh tại vị trí position
     *
     * @param position
     * @return
     */
    @Override
    public ProvinceBean getItem(int position) {
        return provinceBeanListDisplay.get(position);
    }

    /**
     * Hàm trả về id của món ăn trong apdater tại vị trí posion
     *
     * @param position
     * @return
     */
    @Override
    public long getItemId(int position) {
        return position;
    }

    /***
     * Hàm xác định số loại View trên 1 item của Adapter để xác định chính xác vị trí được chọn
     *
     * @return
     */
    @Override
    public int getViewTypeCount() {

        return getCount() == 0 ? 1 : getCount();
    }

    @Override
    public int getItemViewType(int position) {

        return position;
    }

    //Hàm hiện dữ liệu lên view
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ChooseProvinceViewHolder holder;

        if (convertView == null) {
            convertView = LayoutInflater.from(this.context).inflate(R.layout.choose_province_item, parent, false);
            holder = new ChooseProvinceViewHolder(convertView, onSetDefaultProvince);
            convertView.setTag(holder);
        } else {
            holder = (ChooseProvinceViewHolder) convertView.getTag();
        }
        ProvinceBean item = this.provinceBeanListDisplay.get(position);
        int indexCurrentProvince = getIndexCurrentProvince();
        holder = (ChooseProvinceViewHolder) convertView.getTag();

        if (position == indexCurrentProvince) {
            holder.image_view_check_status.setVisibility(View.VISIBLE);
        }
        if (position == indexSelected) {
            if (position == indexCurrentProvince) {
                holder.text_view_province_name.setTextColor(ContextCompat.getColor(context, R.color.color_text_default_choose_province));
                holder.text_view_set_default.setVisibility(View.GONE);
                holder.image_view_check_status.setVisibility(View.VISIBLE);
            } else {
                holder.image_view_check_status.setVisibility(View.VISIBLE);
                holder.text_view_set_default.setVisibility(View.VISIBLE);
            }

        } else {
            if (position == indexCurrentProvince) {
                holder.text_view_province_name.setTextColor(ContextCompat.getColor(context, R.color.color_text_default_choose_province));
                holder.text_view_set_default.setVisibility(View.GONE);
                holder.image_view_check_status.setVisibility(View.GONE);
            } else {
                holder.image_view_check_status.setVisibility(View.GONE);
                holder.text_view_set_default.setVisibility(View.GONE);
            }
        }

        holder.text_view_province_name.setText(item.gettitle());

        return convertView;
    }


    /**
     * Class dùng trong việc lưu lại các view để được lấy ID để không cần phải thực hiện findViewById nhiều lần
     **/
    public class ChooseProvinceViewHolder implements View.OnClickListener {
        public ImageView image_view_check_status;
        public TextView text_view_province_name;
        public TextView text_view_set_default;
        View item;
        IOnSetDefaultProvince onSetDefaultProvince;

        //Hàm khởi tạo
        public ChooseProvinceViewHolder(View view, IOnSetDefaultProvince onSetDefaultProvince) {
            item = view;
            this.image_view_check_status = (ImageView) view.findViewById(R.id.image_view_check_status);
            this.text_view_province_name = (TextView) view.findViewById(R.id.text_view_province_name);
            this.text_view_set_default = (TextView) view.findViewById(R.id.text_view_set_default);
            this.onSetDefaultProvince = onSetDefaultProvince;

            text_view_set_default.setOnClickListener(this);
        }

        //Sự kiện click vào các item của adapter thì interface IOnSetDefaultProvince được thực hiện
        @Override
        public void onClick(View v) {
            onSetDefaultProvince.onSetDefaultProvince();
        }
    }
}
