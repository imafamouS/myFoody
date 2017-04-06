package com.fdsa.infamous.myfoody.ui.util.adapter;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.fdsa.infamous.myfoody.AppConfig;
import com.fdsa.infamous.myfoody.R;
import com.fdsa.infamous.myfoody.ui.util.Type;
import com.fdsa.infamous.myfoody.ui.util.bean.MenuBarItem;

import java.util.List;

/**
 * Created by FDSA on 3/22/2017.
 */

public class MenuBarAdapter extends BaseAdapter {
    private static Context context;
    List<MenuBarItem> menuBarItems;
    Type type;
    private LayoutInflater layoutInflater;

    public Type getType() {
        return type;
    }

    public List<MenuBarItem> getMenuBarItems() {
        return menuBarItems;
    }

    public MenuBarAdapter(Context aContext, List<MenuBarItem> listData, Type type) {
        this.menuBarItems = listData;
        this.context = aContext;
        this.type = type;
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
    public boolean isEmpty() {
        return false;
    }

    @Override
    public int getCount() {
        return menuBarItems.size();
    }

    @Override
    public MenuBarItem getItem(int position) {
        return menuBarItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

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

    static class ViewHolder {
        ImageView imageView;
        TextView textView;
        ImageView isSelected;

        public ViewHolder(View view) {
            imageView = (ImageView) view.findViewById(R.id.list_view_item_menu_tab_image);
            textView = (TextView) view.findViewById(R.id.list_view_item_menu_tab_text);
            isSelected = (ImageView) view.findViewById(R.id.list_view_item_menu_tab_is_selected);

        }

        public void renderData(MenuBarItem item, Type type) {
            if (type == Type.LATEST) {
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
                textView.setText(item.getTittle());
                if(!item.getId().equals("l0")){
                    int img_id = context.getResources().getIdentifier(item.getImage(), "drawable", context.getPackageName()) == 0 ?
                            context.getResources().getIdentifier(AppConfig.IMAGE_NULL, "drawable", context.getPackageName()) :
                            context.getResources().getIdentifier(item.getImage(), "drawable", context.getPackageName());

                    Log.d("CATEGORY", img_id + "");
                    Glide.with(context).load(img_id).into(imageView);
                   // imageView.setImageDrawable(ContextCompat.getDrawable(context, img_id));

                    imageView.setVisibility(View.VISIBLE);

                    if (item.isSelected()) {
                        textView.setTextColor(ContextCompat.getColor(context, R.color.colorPrimary));
                        this.isSelected.setVisibility(View.VISIBLE);
                    }
                }else{
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
