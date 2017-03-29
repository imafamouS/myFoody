package com.fdsa.infamous.myfoody.ui.util;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.fdsa.infamous.myfoody.R;

import java.util.List;

/**
 * Created by FDSA on 3/22/2017.
 */

public class MenuBarAdapter extends BaseAdapter {
    private static Context context;
    List<MenuBarItem> menuBarItems;
    Type type;
    private LayoutInflater layoutInflater;

    public MenuBarAdapter(Context aContext, List<MenuBarItem> listData, Type type) {
        this.menuBarItems = listData;
        this.context = aContext;
        this.type = type;
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
            if (type == Type.LASTEST) {
                textView.setText(item.getTittle());
                imageView.setImageDrawable(ContextCompat.getDrawable(context, item.getImage()));
                imageView.setVisibility(View.VISIBLE);
                if (item.isSelected()) {
                    if (item.getId() == 0) {
                        imageView.setImageDrawable(imageView.getResources().getDrawable(R.drawable.icon_tab_1_new_selected));
                    }
                    this.isSelected.setVisibility(View.VISIBLE);
                }
            } else if (type == Type.CATEGORY) {
                textView.setText(item.getTittle());
                if (item.getImage() != -1) {
                    imageView.setImageDrawable(ContextCompat.getDrawable(context, item.getImage()));
                    imageView.setVisibility(View.VISIBLE);
                }
                if (item.isSelected()) {
                    this.isSelected.setVisibility(View.VISIBLE);
                }

            }
        }
    }
}
