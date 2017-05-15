package com.fdsa.infamous.myfoody.ui.menu.activity;

import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.fdsa.infamous.myfoody.R;
import com.fdsa.infamous.myfoody.common.bean_F2.DistrictBean;
import com.fdsa.infamous.myfoody.common.bean_F2.ImageGalleryBean;
import com.fdsa.infamous.myfoody.common.bean_F2.MenuBarItemBean;
import com.fdsa.infamous.myfoody.common.bean_F2.ProvinceBean;
import com.fdsa.infamous.myfoody.common.myenum.Type;
import com.fdsa.infamous.myfoody.common.myinterface.IOnClickImage;
import com.fdsa.infamous.myfoody.config.AppConfig;
import com.fdsa.infamous.myfoody.ui.menu.activity.gallery.GalleryFolderActivity;
import com.fdsa.infamous.myfoody.ui.menu.activity.gallery.GallerySelectedFileAdapter;
import com.fdsa.infamous.myfoody.ui.menu.views.ListItemDialog;
import com.fdsa.infamous.myfoody.util.controller_F2.DistrictController;
import com.fdsa.infamous.myfoody.util.controller_F2.ProvinceController;
import com.fdsa.infamous.myfoody.util.global.GlobalStaticData;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by apple on 5/8/17.
 */

public class AddNewPlaceActivity extends AppCompatActivity implements View.OnClickListener, IOnClickImage {
    public AddNewPlaceActivity() {

    }

    TextView text_view_choose_province;
    TextView text_view_choose_district;

    EditText edit_text_name_res;
    LinearLayout linear_layout_choose_type_res;
    EditText edit_text_address_res;

    LinearLayout linear_layout_map_location;
    LinearLayout linear_layout_phone_number;
    TextView text_view_open_time;
    TextView text_view_close_time;
    EditText edit_text_max_cash;
    EditText edit_text_min_cash;
    EditText edit_text_short_descr;

    FrameLayout frame_layout_add_image;

    TimePickerDialog openTimePiker;
    TimePickerDialog closeTimePiker;
    ArrayList<ImageGalleryBean> selectedImages = new ArrayList<>();

    GallerySelectedFileAdapter selectedFileAdapter;
    RecyclerView grid_view_file;
    TextView text_view_photo_count;

    public static final int POPUP_CHOOSE_PROVINCE=0;
    public static final int POPUP_CHOOSE_DISTRICT=1;
    String curProvinceID=GlobalStaticData.getCurrentProvinceBean().getId();
    int positionSelectedProvince=0;

    String curDistrictID;
    int positionSelectedDistrict=0;

    ListItemDialog dialogChooseProvince;
    DialogAdapter choosePronvinceDialogAdapter;
    ProvinceController provinceController;

    ListItemDialog dialogChooseDistrict;
    DialogAdapter chooseDistrictDialogAdapter;
    DistrictController districtController;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_place_layout);
        provinceController=new ProvinceController(this.getApplicationContext(),"vietnam",false);
        districtController=new DistrictController(this.getApplicationContext());

        buildPopup(POPUP_CHOOSE_PROVINCE);
        buildPopup(POPUP_CHOOSE_DISTRICT);
        initView();
        initEvent();
        setDefaultDisplay();
    }

    private void initView() {
        text_view_choose_province = (TextView) findViewById(R.id.text_view_choose_province);
        text_view_choose_district = (TextView) findViewById(R.id.text_view_choose_district);

        edit_text_name_res = (EditText) findViewById(R.id.edit_text_name_res);
        linear_layout_choose_type_res = (LinearLayout) findViewById(R.id.linear_layout_choose_type_res);
        edit_text_address_res = (EditText) findViewById(R.id.edit_text_address_res);

        linear_layout_map_location = (LinearLayout) findViewById(R.id.linear_layout_map_location);
        linear_layout_phone_number = (LinearLayout) findViewById(R.id.linear_layout_phone_number);
        text_view_open_time = (TextView) findViewById(R.id.text_view_open_time);
        text_view_close_time = (TextView) findViewById(R.id.text_view_close_time);
        edit_text_max_cash = (EditText) findViewById(R.id.edit_text_max_cash);
        edit_text_min_cash = (EditText) findViewById(R.id.edit_text_min_cash);
        edit_text_short_descr = (EditText) findViewById(R.id.edit_text_short_descr);

        frame_layout_add_image = (FrameLayout) findViewById(R.id.frame_layout_add_image);

        text_view_photo_count = (TextView) findViewById(R.id.text_view_photo_count);


        final Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);

        openTimePiker = new TimePickerDialog(this, onOpenTimeSet, hour, minute, true);
        closeTimePiker = new TimePickerDialog(this, onCloseTimeSet, hour, minute, true);


        grid_view_file = (RecyclerView) findViewById(R.id.recycle_view);
        selectedFileAdapter = new GallerySelectedFileAdapter(getApplicationContext(), selectedImages, Type.IMAGESELECTED_INADDPLACE);
        selectedFileAdapter.setiOnClickImage(this);


        grid_view_file.setLayoutManager(new GridLayoutManager(this, 3));
        grid_view_file.setAdapter(selectedFileAdapter);

    }

    TimePickerDialog.OnTimeSetListener onOpenTimeSet = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            AddNewPlaceActivity.this.text_view_open_time.setText(hourOfDay + ":" + minute);
        }
    };
    TimePickerDialog.OnTimeSetListener onCloseTimeSet = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            AddNewPlaceActivity.this.text_view_close_time.setText(hourOfDay + ":" + minute);
        }
    };

    private void initEvent() {
        text_view_choose_province.setOnClickListener(this);
        text_view_choose_district.setOnClickListener(this);
        linear_layout_choose_type_res.setOnClickListener(this);
        linear_layout_map_location.setOnClickListener(this);
        text_view_open_time.setOnClickListener(this);
        text_view_close_time.setOnClickListener(this);

        frame_layout_add_image.setOnClickListener(this);
    }

    private void setDefaultDisplay() {
        addLayoutNewPhone();
        text_view_choose_province.setText(GlobalStaticData.getCurrentProvinceBean().gettitle());
    }


    private void addLayoutNewPhone() {
        View v = View.inflate(this, R.layout.item_phone_layout, null);
        ImageView image_view_add_phone_number = (ImageView) v.findViewById(R.id.image_view_add_phone_number);
        image_view_add_phone_number.setImageDrawable(ContextCompat.getDrawable(this.getApplicationContext(), R.drawable.icon_plus_blue));
        image_view_add_phone_number.setTag(10);
        image_view_add_phone_number.setOnClickListener(new OnClickAddNumber(this.getApplicationContext(), image_view_add_phone_number, v));
        this.linear_layout_phone_number.addView(v);
    }

    class OnClickAddNumber implements View.OnClickListener {
        Context context;
        ImageView image_view_add_phone_number;
        View layout;

        public OnClickAddNumber(Context context, ImageView image_view_add_phone_number, View layout) {
            this.context = context;
            this.image_view_add_phone_number = image_view_add_phone_number;
            this.layout = layout;
        }

        @Override
        public void onClick(View v) {
            if ((Integer) image_view_add_phone_number.getTag() == 10) {
                addLayoutNewPhone();
                image_view_add_phone_number.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.icon_minus_gray));
                image_view_add_phone_number.setTag(100);
                return;
            }
            AddNewPlaceActivity.this.linear_layout_phone_number.removeView(layout);
            ImageView image_view_add_phone_number_last = (ImageView) AddNewPlaceActivity.this.linear_layout_phone_number
                    .getChildAt(AddNewPlaceActivity.this.linear_layout_phone_number.getChildCount() - 1)
                    .findViewById(R.id.image_view_add_phone_number);
            image_view_add_phone_number_last.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.icon_plus_blue));
            image_view_add_phone_number_last.setTag(10);

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == AppConfig.RESULT_CODE_FROM_GALLERY_FOLDER) {
            selectedImages = data.getParcelableArrayListExtra("images");
            this.selectedFileAdapter.removeAllSelectedSingleClick();
            this.selectedFileAdapter.addAll(selectedImages);

            updateNumofPhoto();
        }
    }

    private void updateNumofPhoto() {
        if (this.selectedFileAdapter.imageSelected.size() > 0) {
            text_view_photo_count.setText("" + this.selectedFileAdapter.imageSelected.size());
            text_view_photo_count.setVisibility(View.VISIBLE);
        } else {
            text_view_photo_count.setText("");
            text_view_photo_count.setVisibility(View.GONE);
        }
    }

    @Override
    public void onClickImage(View v, final int index) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        //Cài đặt các thuộc tính
        builder.setTitle("Xóa");
        builder.setMessage("Bạn có muốn xóa ảnh?");
        // Cài đặt button yes- logout
        builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                selectedFileAdapter.removeSelectedImage(index);
                updateNumofPhoto();
            }
        });

        // Cài đặt button hủy Dismiss ẩn Dialog
        builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }

    @Override
    public void onClickReviewImage(View v, int index) {

    }

    private void buildPopup(int type) {
        if(type==POPUP_CHOOSE_PROVINCE){
            dialogChooseProvince = new ListItemDialog(this);
            choosePronvinceDialogAdapter=new DialogAdapter(this.getApplicationContext(),getDataDialog(type));
            dialogChooseProvince
                    .setMode(ListItemDialog.NO_SEARCH)
                    .setTitle(getString(R.string.TEXT_CHOOSE_PROVINCE))
                    .setLeftButton(getString(R.string.TEXT_ACTION_CANCEL), onCancelChooseProvinceClick)
                    .setAdapter(choosePronvinceDialogAdapter)
                    .setOnItemClickListener(new OnItemInDialogClick(POPUP_CHOOSE_PROVINCE,choosePronvinceDialogAdapter));
        }else if(type==POPUP_CHOOSE_DISTRICT){
            dialogChooseDistrict=new ListItemDialog(this);
            chooseDistrictDialogAdapter=new DialogAdapter(this.getApplicationContext(),getDataDialog(type));
            dialogChooseDistrict
                    .setMode(ListItemDialog.SEARCH)
                    .setTitle(getString(R.string.TEXT_CHOOSE_DISTRICT))
                    .setLeftButton(getString(R.string.TEXT_ACTION_CANCEL), onCancelChooseDistrictClick)
                    .setAdapter(chooseDistrictDialogAdapter)
                    .setOnItemClickListener(new OnItemInDialogClick(POPUP_CHOOSE_DISTRICT,chooseDistrictDialogAdapter));
        }

    }
    private List<MenuBarItemBean> getDataDialog(int type){
        List<MenuBarItemBean> list=new ArrayList<>();
        try {
            if(type==POPUP_CHOOSE_PROVINCE){
                List<ProvinceBean> provinceBeanList= provinceController.getListProvince();
                for (ProvinceBean i: provinceBeanList) {
                    list.add(new MenuBarItemBean(i.getId(),i.gettitle(),"",false));
                }
                list.get(positionSelectedProvince).setSelected(true);
            }else if(type==POPUP_CHOOSE_DISTRICT){
                List<DistrictBean> distrctBeanList=districtController.getListDistrict(this.curProvinceID);
                for (DistrictBean i: distrctBeanList) {
                    list.add(new MenuBarItemBean(i.getId(),i.gettitle(),"",false));
                }
            }
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return list;
    }

    public View.OnClickListener onCancelChooseProvinceClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            dialogChooseProvince.dismiss();
        }
    };
    public View.OnClickListener onCancelChooseDistrictClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            dialogChooseDistrict.dismiss();
        }
    };

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.text_view_choose_province:
                dialogChooseProvince.show();
                break;
            case R.id.text_view_choose_district:
                dialogChooseDistrict.show();
                break;
            case R.id.linear_layout_choose_type_res:

                break;
            case R.id.linear_layout_map_location:
                Toast.makeText(this, "Choose location", Toast.LENGTH_SHORT).show();
                break;
            case R.id.text_view_open_time:
                this.openTimePiker.show();
                Toast.makeText(this, "Choose open time", Toast.LENGTH_SHORT).show();
                break;
            case R.id.text_view_close_time:
                this.closeTimePiker.show();
                Toast.makeText(this, "Choose close time", Toast.LENGTH_SHORT).show();
                break;
            case R.id.frame_layout_add_image:
                Intent intent = new Intent(this, GalleryFolderActivity.class);
                intent.putExtra("mode", GalleryFolderActivity.MULTI_SELECT);
                intent.putParcelableArrayListExtra("images", this.selectedFileAdapter.imageSelected);
                startActivityForResult(intent, 1);
                break;
        }
    }
    class OnItemInDialogClick implements AdapterView.OnItemClickListener{
        int type;
        DialogAdapter adapter;
        public OnItemInDialogClick(int type,DialogAdapter adapter){
            this.type=type;
            this.adapter=adapter;
        }

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            if(adapter!=null && adapter.data.size()>0){
                for(int i=0;i<adapter.data.size();i++){
                    adapter.data.get(i).setSelected(false);
                }
            }
            ((MenuBarItemBean)adapter.getItem(position)).setSelected(true);
            MenuBarItemBean item=((MenuBarItemBean)adapter.getItem(position));

            if(type==POPUP_CHOOSE_PROVINCE){
                positionSelectedProvince=position;
                dialogChooseProvince.refreshData();
                dialogChooseProvince.dismiss();
                curProvinceID=item.getId();
                text_view_choose_province.setText(item.getTittle());
                chooseDistrictDialogAdapter.data=getDataDialog(POPUP_CHOOSE_DISTRICT);
                text_view_choose_district.setText(getString(R.string.TEXT_CHOOSE_DISTRICT));
                curDistrictID="";
                dialogChooseDistrict.refreshData();
            }else if(type==POPUP_CHOOSE_DISTRICT){
                positionSelectedDistrict=position;
                dialogChooseDistrict.refreshData();
                dialogChooseDistrict.dismiss();
                curDistrictID=item.getId();
                text_view_choose_district.setText(item.getTittle());
            }
        }
    }

    class DialogAdapter extends BaseAdapter {
        Context context;
        List<MenuBarItemBean> data;

        public DialogAdapter(Context context, List<MenuBarItemBean> data) {
            super();
            this.context = context;
            this.data = data;
        }

        @Override
        public int getCount() {
            return data.size();
        }

        @Override
        public Object getItem(int position) {
            return data.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            ViewHolder holder;
            MenuBarItemBean item = this.data.get(position);
            if (convertView == null) {
                convertView = LayoutInflater.from(this.context).inflate(R.layout.menu_item_dialog, parent, false);
                holder = new ViewHolder(convertView);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            holder.list_view_item_menu_tab_text.setText(item.getTittle());

            if(item.isSelected()){
                holder.list_view_item_menu_tab_is_selected.setVisibility(View.VISIBLE);
            }else{
                holder.list_view_item_menu_tab_is_selected.setVisibility(View.GONE);
            }

            return convertView;
        }

        @Override
        public int getViewTypeCount() {
            return getCount()==0?1:getCount();
        }
        @Override
        public int getItemViewType(int position) {
            return position;
        }

        class ViewHolder {
            View v;
            TextView list_view_item_menu_tab_text;
            ImageView list_view_item_menu_tab_is_selected;
            public ViewHolder(View v){
                this.v=v;
                this.list_view_item_menu_tab_text=(TextView)v.findViewById(R.id.list_view_item_menu_tab_text);
                this.list_view_item_menu_tab_is_selected=(ImageView)v.findViewById(R.id.list_view_item_menu_tab_is_selected);
            }
        }
    }
}