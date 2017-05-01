package com.fdsa.infamous.myfoody.ui.menu.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.fdsa.infamous.myfoody.R;

/**
 * Created by FDSA on 4/8/2017.
 */

/**
 * Adapter dùng khi các listview không có dữ liệu
 **/
public class NodataAdapter extends BaseAdapter {

    private Context context;

    //Hàm khởi tạo
    public NodataAdapter(Context context) {
        super();
        this.context = context;
    }

    /***
     * Hàm trả về đối tượng thứ position
     *
     * @param position: vị trí
     * @return
     */
    @Override
    public Object getItem(int position) {
        return null;
    }

    /***
     * Hàm đếm số lượng phần tử của adapter
     *
     * @return
     */
    @Override
    public int getCount() {
        return 1;
    }

    /***
     * Hàm trả về ID của phần tử thứ position
     *
     * @param position
     * @return
     */
    @Override
    public long getItemId(int position) {
        return 0;
    }

    /***
     * Hàm kiểm tra xem adapter có phải null không
     * Note: Luôn set là false để khi không có dữ liệu adapter vẫn hiển thị bình thường
     *
     * @return
     */
    @Override
    public boolean isEmpty() {
        return false;
    }

    /***
     * Hàm hiện các giá trị của các phần từ lên các view
     *
     * @param position
     * @param convertView
     * @param parent
     * @return
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            convertView = LayoutInflater.from(this.context).inflate(R.layout.no_data_layout, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.image_view_nodata.setVisibility(View.VISIBLE);
        holder.text_view_no_data.setVisibility(View.VISIBLE);
        return convertView;
    }

    /**
     * Class dùng trong việc lưu lại các view để được lấy ID để không cần phải thực hiện findViewById nhiều lần
     **/
    class ViewHolder {
        public ImageView image_view_nodata;
        public TextView text_view_no_data;

        //Hàm khởi tạo
        public ViewHolder(View item) {
            image_view_nodata = (ImageView) item.findViewById(R.id.image_view_no_data);
            text_view_no_data = (TextView) item.findViewById(R.id.text_view_no_data);
        }
    }
}
