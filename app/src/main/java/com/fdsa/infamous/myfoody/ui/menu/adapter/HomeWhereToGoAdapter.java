package com.fdsa.infamous.myfoody.ui.menu.adapter;

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
import com.fdsa.infamous.myfoody.common.bean_F2.CommentResBean;
import com.fdsa.infamous.myfoody.common.bean_F2.RestaurantBean;
import com.fdsa.infamous.myfoody.common.myinterface.IRestaurantItemClick;
import com.fdsa.infamous.myfoody.config.api.APIConfig;
import com.fdsa.infamous.myfoody.util.global.GlobalFunction;

import java.util.List;

/**
 * Created by FDSA on 4/8/2017.
 */

public class HomeWhereToGoAdapter extends BaseAdapter {

    static Context context;

    public List<RestaurantBean> getRestaurantList() {
        return restaurantList;
    }

    private List<RestaurantBean> restaurantList;

    public IRestaurantItemClick getRestaurantItemClick() {
        return restaurantItemClick;
    }

    public void setRestaurantItemClick(IRestaurantItemClick restaurantItemClick) {
        this.restaurantItemClick = restaurantItemClick;
    }

    public IRestaurantItemClick restaurantItemClick;

    //Hàm khởi tạo
    public HomeWhereToGoAdapter(Context context, List<RestaurantBean> restaurantList) {
        super();
        this.context = context;
        this.restaurantList = restaurantList;
    }

    //Hàm set lại danh sách nhà hàng (cập nhật adapter)
    public void setRestaurantList(List<RestaurantBean> restaurantList) {
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
        RestaurantBean item = this.restaurantList.get(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(this.context).inflate(R.layout.restaurent_item_where_to_go, parent, false);
            holder = new RestaurentViewHolder(convertView,restaurantItemClick);
            convertView.setTag(holder);
        } else {
            holder = (RestaurentViewHolder) convertView.getTag();
        }
        holder.renderData(item);
        return convertView;
    }

    /**Class dùng trong việc lưu lại các view để được lấy ID để không cần phải thực hiện findViewById nhiều lần**/
    static class RestaurentViewHolder implements View.OnClickListener{
        View item;
        private LinearLayout layout_parent_restaurant;
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
        private IRestaurantItemClick restaurantItemClick;
        //Hàm khởi tạo
        public RestaurentViewHolder(View item,IRestaurantItemClick restaurantItemClick) {
            this.item = item;
            this.restaurantItemClick=restaurantItemClick;
            initView(item);

        }

        //Hàm khởi tạo các view để hiện dũ liệu
        private void initView(View item) {
            layout_parent_restaurant=(LinearLayout)item.findViewById(R.id.layout_parent_restaurant);
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

            layout_parent_restaurant.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            restaurantItemClick.RestaurantItemCLick();
        }

        //Hàm hiện dữ liệu lên view
        public void renderData(RestaurantBean restaurant) {
            loadHeader(restaurant);

            loadMoreImage(restaurant);

            loadComment(restaurant);

            updateStatusRestaurent(restaurant);
        }

        //Hàm load tên, địa chỉ, rating, và hình chính của của nhà hàng
        private void loadHeader(RestaurantBean restaurant) {
            text_view_rate_arg_restaurant.setText(GlobalFunction.round(restaurant.getAvg_rating(),2) + "");
            text_view_name_restaurant.setText(restaurant.getTitle());
            text_view_address_restaurant.setText(restaurant.getAddress());

            if (restaurant.getPhoto() != null) {
                Glide.with(context).load(APIConfig.BASE_URL_IMAGE+restaurant.getPhoto()).override(600, 200).into(image_view_main_img_restaurant);
            }

        }

        //Hàm load thêm hình ảnh của của hàng
        private void loadMoreImage(RestaurantBean restaurant) {
            if (restaurant.getListImage() == null || restaurant.getListImage().size() <= 0) {
                linear_layout_sub_img_restaurant.setVisibility(View.GONE);
                return;
            }
            linear_layout_sub_img_restaurant.setVisibility(View.VISIBLE);
            switch (restaurant.getListImage().size()) {
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
        private void loadSubImage(int index, RestaurantBean restaurant) {
            if (index == 1) {
                image_view_sub_img_res_2.setVisibility(View.GONE);
                image_view_sub_img_res_3.setVisibility(View.GONE);

                image_view_sub_img_res_1.setVisibility(View.VISIBLE);

                Glide.with(context).load(APIConfig.BASE_URL_IMAGE+restaurant.getListImage().get(0).getPhoto()).into(image_view_sub_img_res_1);

                return;
            }
            if (index == 2) {
                image_view_sub_img_res_3.setVisibility(View.GONE);

                image_view_sub_img_res_1.setVisibility(View.VISIBLE);
                image_view_sub_img_res_2.setVisibility(View.VISIBLE);

                Glide.with(context).load(APIConfig.BASE_URL_IMAGE+restaurant.getListImage().get(0).getPhoto()).into(image_view_sub_img_res_1);
                Glide.with(context).load(APIConfig.BASE_URL_IMAGE+restaurant.getListImage().get(1).getPhoto()).into(image_view_sub_img_res_2);

                return;
            }
            if (index == 3) {
                image_view_sub_img_res_1.setVisibility(View.VISIBLE);
                image_view_sub_img_res_2.setVisibility(View.VISIBLE);
                image_view_sub_img_res_3.setVisibility(View.VISIBLE);

                Glide.with(context).load(APIConfig.BASE_URL_IMAGE+restaurant.getListImage().get(0).getPhoto()).into(image_view_sub_img_res_1);
                Glide.with(context).load(APIConfig.BASE_URL_IMAGE+restaurant.getListImage().get(1).getPhoto()).into(image_view_sub_img_res_2);
                Glide.with(context).load(APIConfig.BASE_URL_IMAGE+restaurant.getListImage().get(2).getPhoto()).into(image_view_sub_img_res_3);

                return;

            }
        }

        //Hàm load bình luận của của hàng
        private void loadComment(RestaurantBean restaurant) {
            if (restaurant.getListComment().size() == 0 || restaurant.getListComment() == null) {
                linear_layout_parent_comment_res.setVisibility(View.GONE);
            } else {
                linear_layout_parent_comment_res.setVisibility(View.VISIBLE);

                if (restaurant.getListComment().size() == 1) {
                    linear_layout_sub_comment_res_1.setVisibility(View.VISIBLE);
                    linear_layout_sub_comment_res_2.setVisibility(View.GONE);

                    CommentResBean comment1 = restaurant.getListComment().get(0);

                    loadComment1(comment1);


                } else {
                    linear_layout_sub_comment_res_1.setVisibility(View.VISIBLE);
                    linear_layout_sub_comment_res_2.setVisibility(View.VISIBLE);

                    CommentResBean comment1 = restaurant.getListComment().get(0);
                    CommentResBean comment2 = restaurant.getListComment().get(1);

                    loadComment1(comment1);
                    loadComment2(comment2);

                }
            }

        }

        //Hàm load bình luận của của hàng
        private void loadComment1(CommentResBean comment1) {
            if(comment1.getUser()!=null ){
                if(comment1.getUser().getAvatar()!=null){
                    Glide.with(context).load(APIConfig.BASE_URL_IMAGE+comment1.getUser().getAvatar()).into(image_view_avatar_comment_1);
                }

                text_view_name_user_1.setText(comment1.getUser().getName());
                text_view_user_rate_1.setText(comment1.getRating() + "");
                text_view_comment_1.setText(comment1.getComment());
            }



        }

        //Hàm load bình luận của của hàng
        private void loadComment2(CommentResBean comment2) {
            if(comment2.getUser()!=null){
                if(comment2.getUser().getAvatar()!=null){
                    Glide.with(context).load(APIConfig.BASE_URL_IMAGE+comment2.getUser().getAvatar()).into(image_view_avatar_comment_2);
                }
                text_view_name_user_2.setText(comment2.getUser().getName());
                text_view_user_rate_2.setText(comment2.getRating() + "");
                text_view_comment_2.setText(comment2.getComment());
            }
        }

        //Hàm load trạng thái của của hàng
        private void updateStatusRestaurent(RestaurantBean restaurant) {
            String numOfReview = restaurant.getListComment().size() + "";
            String numOfPhoto = restaurant.getListImage().size() + "";

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
