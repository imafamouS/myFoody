package com.fdsa.infamous.myfoody.ui.util.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.fdsa.infamous.myfoody.R;
import com.fdsa.infamous.myfoody.ui.util.bean.Comment;
import com.fdsa.infamous.myfoody.ui.util.bean.Restaurant;

import java.util.List;

/**
 * Created by FDSA on 4/8/2017.
 */

public class HomeWhereToGoAdapter extends BaseAdapter {

    static Context context;
    private List<Restaurant> restaurantList;

    //Hàm khởi tạo
    public HomeWhereToGoAdapter(Context context, List<Restaurant> restaurantList) {
        super();
        this.context = context;
        this.restaurantList = restaurantList;
    }

    //Hàm set lại danh sách nhà hàng (cập nhật adapter)
    public void setRestaurantList(List<Restaurant> restaurantList) {
        this.restaurantList = restaurantList;
    }

    /**
     * Hàm get số lượng phần tử của adapter
     *
     * @return
     */
    @Override
    public int getCount() {
        return restaurantList.size();
    }

    /**
     * Hàm trả về nhà hàng tại vị trí position
     * @param position
     * @return
     */
    @Override
    public Object getItem(int position) {
        return restaurantList.get(position);
    }

    /**
     * Hàm trả về id của nhà hàng tại vị trí position
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

        return getCount();
    }

    @Override
    public int getItemViewType(int position) {

        return position;
    }

    /**
     * Hàm hiện dữ liệu lên view
     * @param position
     * @param convertView
     * @param parent
     * @return
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        RestaurentViewHolder holder;
        Restaurant item = this.restaurantList.get(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(this.context).inflate(R.layout.restaurent_item_where_to_go, parent, false);
            holder = new RestaurentViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (RestaurentViewHolder) convertView.getTag();
        }
        holder.renderData(item);
        return convertView;
    }

    /**Class dùng trong việc lưu lại các view để được lấy ID để không cần phải thực hiện findViewById nhiều lần**/
    static class RestaurentViewHolder {
        View item;
        private RelativeLayout layout_parent_header_restaurant;
        private TextView text_view_rate_arg_restaurant;
        private LinearLayout linear_layout_header_restaurant;
        private TextView text_view_name_restaurant;
        private TextView text_view_address_restaurant;
        private LinearLayout linear_layout_main_img_restaurant;
        private ImageView image_view_main_img_restaurant;
        private LinearLayout linear_layout_sub_img_restaurant;
        private ImageView image_view_sub_img_res_1;
        private ImageView image_view_sub_img_res_2;
        private ImageView image_view_sub_img_res_3;
        private LinearLayout linear_layout_parent_comment_res;
        private LinearLayout linear_layout_sub_comment_res_1;
        private ImageView image_view_avatar_comment_1;
        private TextView text_view_name_user_1;
        private TextView text_view_user_rate_1;
        private TextView text_view_comment_1;
        private LinearLayout linear_layout_sub_comment_res_2;
        private ImageView image_view_avatar_comment_2;
        private TextView text_view_name_user_2;
        private TextView text_view_user_rate_2;
        private TextView text_view_comment_2;
        private LinearLayout linear_layout_num_of_review;
        private LinearLayout linear_layout_num_of_photo;
        private TextView text_view_num_of_review;
        private TextView text_view_num_of_photo;
        private String requestReview;

        //Hàm khởi tạo
        public RestaurentViewHolder(View item) {
            this.item = item;

            initView(item);

        }

        //Hàm khởi tạo các view để hiện dũ liệu
        private void initView(View item) {
            layout_parent_header_restaurant = (RelativeLayout) item.findViewById(R.id.layout_parent_header_restaurant);
            text_view_rate_arg_restaurant = (TextView) item.findViewById(R.id.text_view_rate_arg_restaurant);
            linear_layout_header_restaurant = (LinearLayout) item.findViewById(R.id.linear_layout_header_restaurant);
            text_view_name_restaurant = (TextView) item.findViewById(R.id.text_view_name_restaurant);
            text_view_address_restaurant = (TextView) item.findViewById(R.id.text_view_address_restaurant);


            linear_layout_main_img_restaurant = (LinearLayout) item.findViewById(R.id.linear_layout_main_img_restaurant);
            image_view_main_img_restaurant = (ImageView) item.findViewById(R.id.image_view_main_img_restaurant);

            linear_layout_sub_img_restaurant = (LinearLayout) item.findViewById(R.id.linear_layout_sub_img_restaurant);
            image_view_sub_img_res_1 = (ImageView) item.findViewById(R.id.image_view_sub_img_res_1);
            image_view_sub_img_res_2 = (ImageView) item.findViewById(R.id.image_view_sub_img_res_2);
            image_view_sub_img_res_3 = (ImageView) item.findViewById(R.id.image_view_sub_img_res_3);

            linear_layout_parent_comment_res = (LinearLayout) item.findViewById(R.id.linear_layout_parent_comment_res);

            linear_layout_sub_comment_res_1 = (LinearLayout) item.findViewById(R.id.linear_layout_sub_comment_res_1);
            image_view_avatar_comment_1 = (ImageView) item.findViewById(R.id.image_view_avatar_comment_1);
            text_view_name_user_1 = (TextView) item.findViewById(R.id.text_view_name_user_1);
            text_view_user_rate_1 = (TextView) item.findViewById(R.id.text_view_user_rate_1);
            text_view_comment_1 = (TextView) item.findViewById(R.id.text_view_comment_1);

            linear_layout_sub_comment_res_2 = (LinearLayout) item.findViewById(R.id.linear_layout_sub_comment_res_2);
            image_view_avatar_comment_2 = (ImageView) item.findViewById(R.id.image_view_avatar_comment_2);
            text_view_name_user_2 = (TextView) item.findViewById(R.id.text_view_name_user_2);
            text_view_user_rate_2 = (TextView) item.findViewById(R.id.text_view_user_rate_2);
            text_view_comment_2 = (TextView) item.findViewById(R.id.text_view_comment_2);

            linear_layout_num_of_review = (LinearLayout) item.findViewById(R.id.linear_layout_num_of_review);
            linear_layout_num_of_photo = (LinearLayout) item.findViewById(R.id.linear_layout_num_of_photo);
            text_view_num_of_review = (TextView) item.findViewById(R.id.text_view_num_of_review);
            text_view_num_of_photo = (TextView) item.findViewById(R.id.text_view_num_of_photo);

            requestReview = item.getResources().getString(R.string.REQUEST_REVIEW);
        }

        //Hàm hiện dữ liệu lên view
        public void renderData(Restaurant restaurant) {
            loadHeader(restaurant);

            loadMoreImage(restaurant);

            loadComment(restaurant);

            updateStatusRestaurent(restaurant);
        }

        //Hàm load tên, địa chỉ, rating, và hình chính của của nhà hàng
        private void loadHeader(Restaurant restaurant) {
            text_view_rate_arg_restaurant.setText(restaurant.getRating() + "");
            text_view_name_restaurant.setText(restaurant.getNameRes());
            text_view_address_restaurant.setText(restaurant.getAddressRes());

            if (restaurant.getImg() != null) {
                Glide.with(context).load(restaurant.getImg()).override(600, 200).into(image_view_main_img_restaurant);
            }

        }

        //Hàm load thêm hình ảnh của của hàng
        private void loadMoreImage(Restaurant restaurant) {
            if (restaurant.getSubImg() == null || restaurant.getSubImg().size() <= 0) {
                linear_layout_sub_img_restaurant.setVisibility(View.GONE);
                return;
            }
            linear_layout_sub_img_restaurant.setVisibility(View.VISIBLE);
            switch (restaurant.getSubImg().size()) {
                case 1:
                    loadSubImage(1, restaurant);
                    break;
                case 2:
                    loadSubImage(2, restaurant);
                    break;
                case 3:
                    loadSubImage(3, restaurant);
                    break;
                default:
                    break;
            }
        }

        //Hàm load thêm hình ảnh của của hàng
        private void loadSubImage(int index, Restaurant restaurant) {
            if (index == 1) {
                image_view_sub_img_res_2.setVisibility(View.GONE);
                image_view_sub_img_res_3.setVisibility(View.GONE);

                image_view_sub_img_res_1.setVisibility(View.VISIBLE);

                Glide.with(context).load(restaurant.getSubImg().get(0)).into(image_view_sub_img_res_1);

                return;
            }
            if (index == 2) {
                image_view_sub_img_res_3.setVisibility(View.GONE);

                image_view_sub_img_res_1.setVisibility(View.VISIBLE);
                image_view_sub_img_res_2.setVisibility(View.VISIBLE);

                Glide.with(context).load(restaurant.getSubImg().get(0)).into(image_view_sub_img_res_1);
                Glide.with(context).load(restaurant.getSubImg().get(1)).into(image_view_sub_img_res_2);

                return;
            }
            if (index == 3) {
                image_view_sub_img_res_1.setVisibility(View.VISIBLE);
                image_view_sub_img_res_2.setVisibility(View.VISIBLE);
                image_view_sub_img_res_3.setVisibility(View.VISIBLE);

                Glide.with(context).load(restaurant.getSubImg().get(0)).into(image_view_sub_img_res_1);
                Glide.with(context).load(restaurant.getSubImg().get(1)).into(image_view_sub_img_res_2);
                Glide.with(context).load(restaurant.getSubImg().get(2)).into(image_view_sub_img_res_3);

                return;

            }
        }

        //Hàm load bình luận của của hàng
        private void loadComment(Restaurant restaurant) {
            if (restaurant.getComments().size() == 0 || restaurant.getComments() == null) {
                linear_layout_parent_comment_res.setVisibility(View.GONE);
            } else {
                linear_layout_parent_comment_res.setVisibility(View.VISIBLE);

                if (restaurant.getComments().size() == 1) {
                    linear_layout_sub_comment_res_1.setVisibility(View.VISIBLE);
                    linear_layout_sub_comment_res_2.setVisibility(View.GONE);

                    Comment comment1 = restaurant.getComments().get(0);

                    loadComment1(comment1);


                } else {
                    linear_layout_sub_comment_res_1.setVisibility(View.VISIBLE);
                    linear_layout_sub_comment_res_2.setVisibility(View.VISIBLE);

                    Comment comment1 = restaurant.getComments().get(0);
                    Comment comment2 = restaurant.getComments().get(1);

                    loadComment1(comment1);
                    loadComment2(comment2);

                }
            }

        }

        //Hàm load bình luận của của hàng
        private void loadComment1(Comment comment1) {
            Glide.with(context).load(comment1.getUid().getAvatar()).into(image_view_avatar_comment_1);

            text_view_name_user_1.setText(comment1.getUid().getName());
            text_view_user_rate_1.setText(comment1.getRate() + "");
            text_view_comment_1.setText(comment1.getText());
        }

        //Hàm load bình luận của của hàng
        private void loadComment2(Comment comment2) {
            Glide.with(context).load(comment2.getUid().getAvatar()).into(image_view_avatar_comment_2);

            text_view_name_user_2.setText(comment2.getUid().getName());
            text_view_user_rate_2.setText(comment2.getRate() + "");
            text_view_comment_2.setText(comment2.getText());
        }

        //Hàm load trạng thái của của hàng
        private void updateStatusRestaurent(Restaurant restaurant) {
            String numOfReview = restaurant.getComments().size() + "";
            String numOfPhoto = restaurant.getSubImg().size() + "";

            if (numOfPhoto.equals("0") && numOfReview.equals("0")) {
                linear_layout_num_of_photo.setVisibility(View.GONE);
                text_view_num_of_review.setText(requestReview);
            } else {
                linear_layout_num_of_photo.setVisibility(View.VISIBLE);
                text_view_num_of_review.setText(numOfReview);
                text_view_num_of_photo.setText(numOfPhoto);
            }


        }
    }


}
