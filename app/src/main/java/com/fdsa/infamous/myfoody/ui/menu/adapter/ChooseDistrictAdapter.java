package com.fdsa.infamous.myfoody.ui.menu.adapter;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.fdsa.infamous.myfoody.R;
import com.fdsa.infamous.myfoody.common.bean_F2.DistrictBean;
import com.fdsa.infamous.myfoody.common.bean_F2.StreetBean;
import com.fdsa.infamous.myfoody.common.myinterface.IChooseDistrict;

import java.util.List;

/**
 * Created by FDSA on 4/24/2017.
 */

public class ChooseDistrictAdapter extends BaseExpandableListAdapter {

    Context context;
    List<DistrictBean> districtBeanList;
    IChooseDistrict iChooseDistrict;
    int groupPosition;
    int childPosition;
    //Hàm khởi tạo
    public ChooseDistrictAdapter(Context context, List<DistrictBean> districtBeanList) {
        this.context = context;

        this.districtBeanList = districtBeanList;
    }
    //Hàm gán interface Chọn huyện
    public void setiChooseDistrict(IChooseDistrict iChooseDistrict) {
        this.iChooseDistrict = iChooseDistrict;
    }
    //Hàm lấy số loại view trên group của expanablelist
    @Override
    public int getGroupTypeCount() {
        return getGroupCount() == 0 ? 1 : getGroupCount();
    }
    @Override
    public int getGroupType(int groupPosition) {
        return groupPosition;
    }

    //Hàm lấy số loại view trên child của expanablelist
    @Override
    public int getChildType(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public int getChildTypeCount() {
        return getChildrenCount(this.groupPosition) == 0 ? 1 : getChildrenCount(this.groupPosition);
    }
    //Hàm lấy  số lượng item group
    @Override
    public int getGroupCount() {
        return districtBeanList == null ? 0 : districtBeanList.size();
    }
    //Hàm lấy  số lượng item child trong 1 group
    @Override
    public int getChildrenCount(int groupPosition) {
        return districtBeanList != null ? districtBeanList.get(groupPosition).getListStreet().size() : 0;
    }
    //hàm trả về item group tại vị trí groupposition
    @Override
    public DistrictBean getGroup(int groupPosition) {
        return this.districtBeanList.get(groupPosition);
    }
    //hàm trả về item child tại vị trí groupposition
    @Override
    public StreetBean getChild(int groupPosition, int childPosition) {
        return this.districtBeanList.get(groupPosition).getListStreet().get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    //Hiện  dữ liệu len view group của list
    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        GroupViewHolder groupViewHolder;
        DistrictBean district = this.districtBeanList.get(groupPosition);
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.choose_district_group_item, null);
            groupViewHolder = new GroupViewHolder(convertView);
            convertView.setTag(groupViewHolder);
        } else {
            groupViewHolder = (GroupViewHolder) convertView.getTag();
        }
        groupViewHolder.itemGroup.setOnClickListener(new SelectDistrict(groupPosition));
        groupViewHolder.linear_layout_child_expand.setOnClickListener(new ExpandStreet(groupPosition));

        if (district.isSelected()) {
            groupViewHolder.text_view_district_name.setTextColor(ContextCompat.getColor(context, R.color.colorPrimary));
        }
        groupViewHolder.text_view_district_name.setText(district.getTitle());
        groupViewHolder.text_view_num_of_street.setText(district.getNumofStreet() + " Đường");

        return convertView;
    }
    //Hiện  dữ liệu len view child của list
    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {

        ChildViewHolder childViewHolder;

        this.groupPosition = groupPosition;
        this.childPosition = childPosition;

        StreetBean streetBean = this.districtBeanList.get(groupPosition).getListStreet().get(childPosition);
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.choose_district_child_item, null);
            childViewHolder = new ChildViewHolder(convertView);
            convertView.setTag(childViewHolder);
        } else {
            childViewHolder = (ChildViewHolder) convertView.getTag();
        }
        childViewHolder.itemChild.setOnClickListener(new SelectStreet(groupPosition, childPosition));
        if (streetBean.isSelected()) {
            childViewHolder.text_view_street_name.setTextColor(ContextCompat.getColor(context, R.color.colorPrimary));
        }
        childViewHolder.text_view_street_name.setText(streetBean.getTitle());

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }

    //Class thực hiện việc khi click vào quận, huyện
    class SelectDistrict implements View.OnClickListener {
        int groupPosion;

        public SelectDistrict(int groupPosition) {
            this.groupPosion = groupPosition;
        }

        @Override
        public void onClick(View v) {
            ChooseDistrictAdapter.this.iChooseDistrict.onSelectDistrict(this.groupPosion);
        }
    }
    //Class thực hiện việc hiện thông tin các đường trong 1 quận, huyện
    class ExpandStreet implements View.OnClickListener {
        int groupPosion;

        public ExpandStreet(int groupPosition) {
            this.groupPosion = groupPosition;
        }

        @Override
        public void onClick(View v) {
            ChooseDistrictAdapter.this.iChooseDistrict.onExpand(this.groupPosion);
        }
    }
    //Class thực hiện việc click vào đường
    class SelectStreet implements View.OnClickListener {
        int groupPosition;
        int childPosition;

        public SelectStreet(int groupPosition, int childPosition) {
            this.groupPosition = groupPosition;
            this.childPosition = childPosition;
        }

        @Override
        public void onClick(View v) {
            ChooseDistrictAdapter.this.iChooseDistrict.onSelectStreet(groupPosition, childPosition);
        }
    }

    //ViewHodler của Group View
    class GroupViewHolder {
        View itemGroup;
        LinearLayout linear_layout_child_expand;
        TextView text_view_district_name;
        TextView text_view_num_of_street;

        public GroupViewHolder(View item) {
            this.itemGroup = item;
            linear_layout_child_expand = (LinearLayout) itemGroup.findViewById(R.id.linear_layout_child_expand);

            text_view_district_name = (TextView) itemGroup.findViewById(R.id.text_view_district_name);
            text_view_num_of_street = (TextView) itemGroup.findViewById(R.id.text_view_num_of_street);
        }
    }
    //ViewHodler của ChildView
    class ChildViewHolder {
        View itemChild;
        TextView text_view_street_name;

        public ChildViewHolder(View item) {
            this.itemChild = item;
            text_view_street_name = (TextView) itemChild.findViewById(R.id.text_view_street_name);
        }
    }
}
