package com.fdsa.infamous.myfoody.common.myinterface;

/**
 * Created by FDSA on 4/24/2017.
 */
//Interface dùng trong expanablelistview chọn Tỉnh, huyện, thị xã
public interface IChooseDistrict {
    void onExpand(int groupPosition);

    void onSelectDistrict(int groupPosition);

    void onSelectStreet(int groupPosion, int chlidPosion);
}
