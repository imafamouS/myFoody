package com.fdsa.infamous.myfoody.ui.util.adapter;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.fdsa.infamous.myfoody.AppConfig;
import com.fdsa.infamous.myfoody.R;
import com.fdsa.infamous.myfoody.ui.util.bean.MenuBarItem;
import com.fdsa.infamous.myfoody.ui.util.myenum.Type;

import java.util.List;

/**
 * Created by FDSA on 3/22/2017.
 */

/**
 * Adapter dùng trong các listview của các tab con trong Ở đâu, Ăn gì
 **/
public class MenuBarAdapter extends BaseAdapter {
    private static Context context;
    List<MenuBarItem> menuBarItems;
    Type type;
    private LayoutInflater layoutInflater;

    //Hàm khởi tạo
    public MenuBarAdapter(Context aContext, List<MenuBarItem> listData, Type type) {
        this.menuBarItems = listData;
        this.context = aContext;
        this.type = type;
    }

    //Hàm trả về loại của adapter
    public Type getType() {
        return type;
    }

    //Hàm get dữ liệu của adapter
    public List<MenuBarItem> getMenuBarItems() {
        return menuBarItems;
    }

    /***
     * Hàm xác định số loại View trên 1 item của Adapter để xác định chính xác vị trí được chọn
     *
     * @return
     */
    @Override
    public int getViewTypeCount() {
        return getCount();
    }
    @Override
    public int getItemViewType(int position) {
        return position;
    }

    /**
     * Hàm kiểm tra adapter có rỗng hay không
     *
     * @return
     */
    @Override
    public boolean isEmpty() {
        return false;
    }

    /**
     * Hàm đếm số lượng phần tử
     *
     * @return
     */
    @Override
    public int getCount() {
        return menuBarItems.size();
    }

    /**
     * Hàm trả về MenuBarItem tại position
     *
     * @param position
     * @return
     */
    @Override
    public MenuBarItem getItem(int position) {
        return menuBarItems.get(position);
    }

    /**
     * Hàm trả về id của item tại vị trí position
     *
     * @param position
     * @return
     */
    @Override
    public long getItemId(int position) {
        return position;
    }

    /**
     * Hàm hiện view cùng dữ liệu
     *
     * @param position
     * @param convertView
     * @param parent
     * @return
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        MenuBarItem item = this.menuBarItems.get(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(this.context).inflate(R.layout.home_tab_menu_item, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.renderData(item, type);
        return convertView;
    }

    /**Class dùng trong việc lưu lại các view để được lấy ID để không cần phải thực hiện findViewById nhiều lần**/
    static class ViewHolder {
        private ImageView imageView;
        private TextView textView;
        private ImageView isSelected;

        //Hàm khởi tạo
        public ViewHolder(View view) {
            imageView = (ImageView) view.findViewById(R.id.list_view_item_menu_tab_image);
            textView = (TextView) view.findViewById(R.id.list_view_item_menu_tab_text);
            isSelected = (ImageView) view.findViewById(R.id.list_view_item_menu_tab_is_selected);

        }

        //Hàm hiện dữ liệu lên view
        public void renderData(MenuBarItem item, Type type) {
            if (type == Type.LATEST) {
                //Tab 1
                textView.setText(item.getTittle());
                int img_id = context.getResources().getIdentifier(item.getImage(), "drawable", context.getPackageName()) == 0 ?
                        context.getResources().getIdentifier(AppConfig.IMAGE_NULL, "drawable", context.getPackageName()) :
                        context.getResources().getIdentifier(item.getImage(), "drawable", context.getPackageName());

                //imageView.setImageDrawable(ContextCompat.getDrawable(context, img_id));
                Glide.with(context).load(img_id).into(imageView);
                imageView.setVisibility(View.VISIBLE);
                if (item.isSelected()) {
                    textView.setTextColor(ContextCompat.getColor(context, R.color.colorPrimary));
                    switch (item.getId()) {
                        case "moinhat":
                            Glide.with(context).load(R.drawable.icon_tab_1_new_selected).into(imageView);
                            //imageView.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.icon_tab_1_new_selected));
                            break;
                        case "gantoi":
                            Glide.with(context).load( R.drawable.icon_tab_1_near_selected).into(imageView);
                            //imageView.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.icon_tab_1_near_selected));
                            break;
                        case "phobien":
                            Glide.with(context).load( R.drawable.icon_tab_1_popular_selected).into(imageView);
                            //imageView.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.icon_tab_1_popular_selected));
                            break;
                        case "dukhach":
                            Glide.with(context).load( R.drawable.icon_tab_1_tourist_selected).into(imageView);
                            //imageView.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.icon_tab_1_tourist_selected));
                            break;
                        case "ecard":
                            Glide.with(context).load( R.drawable.icon_tab_1_ecard_selected).into(imageView);
                           // imageView.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.icon_tab_1_ecard_selected));
                            break;
                        case "datcho":
                            Glide.with(context).load( R.drawable.icon_tab_1_promote_selected).into(imageView);
                           // imageView.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.icon_tab_1_book_selected));
                            break;
                        case "uudaithe":
                            Glide.with(context).load( R.drawable.icon_tab_1_promote_selected).into(imageView);
                          //  imageView.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.icon_tab_1_promote_selected));
                            break;
                        case "giaohang":
                            Glide.with(context).load( R.drawable.icon_tab_1_delivery_selected).into(imageView);
                           // imageView.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.icon_tab_1_delivery_selected));
                            break;
                        default:
                            break;
                    }

                    this.isSelected.setVisibility(View.VISIBLE);
                }
            } else if (type == Type.CATEGORY) {
                //Tab 2
                textView.setText(item.getTittle());
                if(!item.getId().equals("l0")){
                    int img_id = context.getResources().getIdentifier(item.getImage(), "drawable", context.getPackageName()) == 0 ?
                            context.getResources().getIdentifier(AppConfig.IMAGE_NULL, "drawable", context.getPackageName()) :
                            context.getResources().getIdentifier(item.getImage(), "drawable", context.getPackageName());

                    Glide.with(context).load(img_id).into(imageView);
                   // imageView.setImageDrawable(ContextCompat.getDrawable(context, img_id));

                    imageView.setVisibility(View.VISIBLE);

                    if (item.isSelected()) {
                        textView.setTextColor(ContextCompat.getColor(context, R.color.colorPrimary));
                        this.isSelected.setVisibility(View.VISIBLE);
                    }
                } else {
                    //Tab 3
                    imageView.setVisibility(View.GONE);
                    if(item.isSelected()) {
                        textView.setTextColor(ContextCompat.getColor(context, R.color.colorPrimary));
                        this.isSelected.setVisibility(View.VISIBLE);
                    }
                }

            }
        }
    }
}
