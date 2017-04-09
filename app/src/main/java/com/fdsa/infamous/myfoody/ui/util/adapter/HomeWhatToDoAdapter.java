package com.fdsa.infamous.myfoody.ui.util.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.fdsa.infamous.myfoody.R;
import com.fdsa.infamous.myfoody.ui.util.bean.Food;

import java.util.List;

/**
 * Created by FDSA on 4/8/2017.
 */

public class HomeWhatToDoAdapter extends BaseAdapter {


    static Context context;
    List<Food> foodList;

    public HomeWhatToDoAdapter(Context context, List<Food> foodList) {
        this.context = context;
        this.foodList = foodList;
    }

    public void setFoodList(List<Food> foodList) {
        this.foodList = foodList;
    }


    @Override
    public int getCount() {
        return foodList.size();
    }

    @Override
    public Food getItem(int position) {
        return foodList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        HomeWhatToDoAdapter.FoodViewHolder holder;
        Food item = this.foodList.get(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(this.context).inflate(R.layout.food_item_what_to_do, parent, false);
            holder = new HomeWhatToDoAdapter.FoodViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (HomeWhatToDoAdapter.FoodViewHolder) convertView.getTag();
        }
        holder.renderData(item);
        return convertView;


    }

    static class FoodViewHolder {
        public View item;
        private LinearLayout linear_layout_food_item_1;
        private ImageView image_view_pic_food_1;
        private TextView text_view_name_food_1;
        private TextView text_view_name_res_1;
        private TextView text_view_add_res_1;
        private ImageView image_view_avatar_user_food_1;
        private TextView text_view_name_user_food_1;
        private TextView text_view_day_user_food_1;

        public FoodViewHolder(View item) {
            this.item = item;

            linear_layout_food_item_1 = (LinearLayout) item.findViewById(R.id.linear_layout_food_item_1);
            image_view_pic_food_1 = (ImageView) item.findViewById(R.id.image_view_pic_food_1);
            text_view_name_food_1 = (TextView) item.findViewById(R.id.text_view_name_food_1);
            text_view_name_res_1 = (TextView) item.findViewById(R.id.text_view_name_res_1);
            text_view_add_res_1 = (TextView) item.findViewById(R.id.text_view_add_res_1);
            image_view_avatar_user_food_1 = (ImageView) item.findViewById(R.id.image_view_avatar_user_food_1);
            text_view_name_user_food_1 = (TextView) item.findViewById(R.id.text_view_name_user_food_1);
            text_view_day_user_food_1 = (TextView) item.findViewById(R.id.text_view_day_user_food_1);
        }

        public void renderData(Food food) {
            loadItem1(food);
        }

        private void loadItem1(Food food) {
            Glide.with(context).load(food.getPhoto()).into(image_view_pic_food_1);
            text_view_name_food_1.setText(food.getFoodName());
            text_view_name_res_1.setText(food.getNameRes());
            text_view_add_res_1.setText(food.getAddressRes());
            //image_view_avatar_user_food_1
            if (food.getComment() != null && food.getComment().getUid().getAvatar() != null) {
                Glide.with(context).load(food.getComment().getUid().getAvatar()).into(image_view_avatar_user_food_1);

                text_view_name_user_food_1.setVisibility(View.VISIBLE);
                text_view_day_user_food_1.setVisibility(View.VISIBLE);

                text_view_name_user_food_1.setText(food.getComment().getUid().getName());
                text_view_day_user_food_1.setText(food.getComment().getText());
            } else {
                text_view_name_user_food_1.setVisibility(View.GONE);
                text_view_day_user_food_1.setVisibility(View.GONE);
            }
        }
    }
}
