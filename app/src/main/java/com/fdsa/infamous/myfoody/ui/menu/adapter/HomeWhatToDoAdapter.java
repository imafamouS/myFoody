package com.fdsa.infamous.myfoody.ui.menu.adapter;

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
import com.fdsa.infamous.myfoody.common.bean_F2.FoodBean;
import com.fdsa.infamous.myfoody.common.myinterface.IRestaurantItemClick;
import com.fdsa.infamous.myfoody.config.api.APIConfig;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by FDSA on 4/8/2017.
 */

public class HomeWhatToDoAdapter extends BaseAdapter {


    static Context context;
    private List<FoodBean> foodList;
    IRestaurantItemClick restaurantItemClick;

    public void setRestaurantItemClick(IRestaurantItemClick restaurantItemClick) {
        this.restaurantItemClick = restaurantItemClick;
    }

    //Hàm khởi tạo
    public HomeWhatToDoAdapter(Context context, List<FoodBean> foodList) {
        this.context = context;
        if(foodList!=null){
            this.foodList = foodList;
        }else{
            this.foodList = new ArrayList<>();
        }

    }

    //Hàm set danh sách món ăn (update adapter)
    public void setFoodList(List<FoodBean> foodList) {
        this.foodList = foodList;
    }

    /**
     * Hàm trả về số lượng phần tử của adapter
     *
     * @return
     */
    @Override
    public int getCount() {
        return foodList.size();
    }

    /**
     * Hàm trả về món về tại vị trí postion
     * @param position
     * @return
     */
    @Override
    public FoodBean getItem(int position) {
        return foodList.get(position);
    }

    /**
     * Hàm trả về id của món ăn trong apdater tại vị trí posion
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

        return getCount()==0?1:getCount();
    }

    @Override
    public int getItemViewType(int position) {

        return position;
    }

    /**
     * Hàm hiện dữ liệu lên view
     *
     * @param position
     * @param convertView
     * @param parent
     * @return
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        HomeWhatToDoAdapter.FoodViewHolder holder;
        FoodBean item = this.foodList.get(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(this.context).inflate(R.layout.food_item_what_to_do, parent, false);
            holder = new HomeWhatToDoAdapter.FoodViewHolder(convertView,this.restaurantItemClick,position);
            convertView.setTag(holder);
        } else {
            holder = (HomeWhatToDoAdapter.FoodViewHolder) convertView.getTag();
        }
        holder.renderData(item);
        return convertView;


    }

    /**Class dùng trong việc lưu lại các view để được lấy ID để không cần phải thực hiện findViewById nhiều lần**/
    static class FoodViewHolder implements  View.OnClickListener{
        public View item;
        private LinearLayout linear_layout_food_item_1;
        private ImageView image_view_pic_food_1;
        private TextView text_view_name_food_1;
        private TextView text_view_name_res_1;
        private TextView text_view_add_res_1;
        private CircleImageView image_view_avatar_user_food_1;
        private TextView text_view_name_user_food_1;
        private TextView text_view_day_user_food_1;
        IRestaurantItemClick restaurantItemClick;
        int position;

        //Hàm khởi tạo
        public FoodViewHolder(View item,IRestaurantItemClick restaurantItemClick,int position) {
            this.item = item;
            this.restaurantItemClick=restaurantItemClick;
            this.position=position;

            linear_layout_food_item_1 = (LinearLayout) item.findViewById(R.id.linear_layout_food_item_1);
            image_view_pic_food_1 = (ImageView) item.findViewById(R.id.image_view_pic_food_1);
            text_view_name_food_1 = (TextView) item.findViewById(R.id.text_view_name_food_1);
            text_view_name_res_1 = (TextView) item.findViewById(R.id.text_view_name_res_1);
            text_view_add_res_1 = (TextView) item.findViewById(R.id.text_view_add_res_1);
            image_view_avatar_user_food_1 = (CircleImageView) item.findViewById(R.id.image_view_avatar_user_food_1);
            text_view_name_user_food_1 = (TextView) item.findViewById(R.id.text_view_name_user_food_1);
            text_view_day_user_food_1 = (TextView) item.findViewById(R.id.text_view_day_user_food_1);
            item.setOnClickListener(this);
        }

        //Hàm hiện dữ liệu từ đối tượng món ăn lên view
        public void renderData(FoodBean food) {
            loadItem1(food);
        }

        //Hàm hiện dữ liệu từ đối tượng món ăn lên view
        private void loadItem1(FoodBean food) {
            Glide.with(context).load(APIConfig.BASE_URL_IMAGE+food.getPhoto()).into(image_view_pic_food_1);
            text_view_name_food_1.setText(food.getTitle());
            text_view_name_res_1.setText(food.getName_res());
            text_view_add_res_1.setText(food.getAddress_res());
            //image_view_avatar_user_food_1
            if (food.getListComment() != null && food.getListComment().size()>0) {
                if(food.getListComment().get(0).getUser()!=null){
                    Glide.with(context).load(APIConfig.BASE_URL_IMAGE+food.getListComment().get(0).getUser().getAvatar()).into(image_view_avatar_user_food_1);
                    text_view_name_user_food_1.setVisibility(View.VISIBLE);
                    text_view_day_user_food_1.setVisibility(View.VISIBLE);

                    text_view_name_user_food_1.setText(food.getListComment().get(0).getUser().getName());
                    text_view_day_user_food_1.setText(food.getListComment().get(0).getComment());
                }
            } else {
                text_view_name_user_food_1.setVisibility(View.GONE);
                text_view_day_user_food_1.setVisibility(View.GONE);
            }
        }

        @Override
        public void onClick(View v) {
            restaurantItemClick.RestaurantItemCLick(this.position);
        }
    }
}
