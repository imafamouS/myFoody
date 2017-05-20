package com.fdsa.infamous.myfoody.ui.menu.activity;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
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

import com.fdsa.infamous.myfoody.MapsActivity;
import com.fdsa.infamous.myfoody.R;
import com.fdsa.infamous.myfoody.common.bean_F2.DistrictBean;
import com.fdsa.infamous.myfoody.common.bean_F2.ImageGalleryBean;
import com.fdsa.infamous.myfoody.common.bean_F2.MenuBarItemBean;
import com.fdsa.infamous.myfoody.common.bean_F2.MoreImageRestaurantBean;
import com.fdsa.infamous.myfoody.common.bean_F2.PositionBean;
import com.fdsa.infamous.myfoody.common.bean_F2.ProvinceBean;
import com.fdsa.infamous.myfoody.common.bean_F2.RestaurantBean;
import com.fdsa.infamous.myfoody.common.myenum.Type;
import com.fdsa.infamous.myfoody.common.myinterface.ICallBackAsynsTask;
import com.fdsa.infamous.myfoody.common.myinterface.IOnClickImage;
import com.fdsa.infamous.myfoody.config.AppConfig;
import com.fdsa.infamous.myfoody.config.api.APIAction;
import com.fdsa.infamous.myfoody.ui.menu.activity.gallery.GalleryFolderActivity;
import com.fdsa.infamous.myfoody.ui.menu.activity.gallery.GallerySelectedFileAdapter;
import com.fdsa.infamous.myfoody.ui.menu.views.ListItemDialog;
import com.fdsa.infamous.myfoody.ui.menu.views.ListItemDialogTypeRes;
import com.fdsa.infamous.myfoody.util.asynctask.MyFoodyPostMethod;
import com.fdsa.infamous.myfoody.util.controller_F2.DistrictController;
import com.fdsa.infamous.myfoody.util.controller_F2.MenuBarItemController;
import com.fdsa.infamous.myfoody.util.controller_F2.ProvinceController;
import com.fdsa.infamous.myfoody.util.global.GlobalFunction;
import com.fdsa.infamous.myfoody.util.global.GlobalStaticData;
import com.fdsa.infamous.myfoody.util.permission.PermissionUtil;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

/**
 * Created by apple on 5/8/17.
 */

public class AddNewPlaceActivity extends BaseSlideActivity implements View.OnClickListener, IOnClickImage {
    public static final int POPUP_CHOOSE_PROVINCE = 0;
    public static final int POPUP_CHOOSE_DISTRICT = 1;
    public static final int POPUP_CHOOSE_RESTYPE = 2;
    LinearLayout back_button_add_place;
    TextView text_view_choose_province;
    TextView text_view_choose_district;
    TextView text_view_done;
    EditText edit_text_name_res;
    LinearLayout linear_layout_choose_type_res;
    EditText edit_text_address_res;
    TextView text_view_res_type;
    LinearLayout linear_layout_map_location;
    TextView text_view_lat_long;
    LinearLayout linear_layout_phone_number;
    TextView text_view_open_time;
    TextView text_view_close_time;
    EditText edit_text_max_cash;
    EditText edit_text_min_cash;
    EditText edit_text_short_descr;
    FrameLayout frame_layout_add_image;
    TimePickerDialog openTimePiker;
    TimePickerDialog closeTimePiker;
    GallerySelectedFileAdapter selectedFileAdapter;
    RecyclerView grid_view_file;
    TextView text_view_photo_count;
    int positionSelectedProvince = 0;
    int positionSelectedDistrict = 0;
    ListItemDialog dialogChooseProvince;
    DialogAdapter choosePronvinceDialogAdapter;
    ProvinceController provinceController;
    ListItemDialog dialogChooseDistrict;
    DialogAdapter chooseDistrictDialogAdapter;
    DistrictController districtController;
    ListItemDialogTypeRes dialogChooseResType;
    DialogAdapter chooseResTypeAdapter;
    MenuBarItemController menuBarItemController;
    String curDistrictID;
    String curProvinceID = GlobalStaticData.getCurrentProvinceBean().getId();
    List<MenuBarItemBean> selectedResType = new ArrayList<>();
    double lat = -1;
    double lng = -1;
    ArrayList<ImageGalleryBean> selectedImages = new ArrayList<>();
    //Sự kiện khi chọn thời giangian mở cửa trên picker chọn thời
    TimePickerDialog.OnTimeSetListener onOpenTimeSet = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            AddNewPlaceActivity.this.text_view_open_time.setText(hourOfDay + ":" + minute);
        }
    };
    //Sự kiện khi chọn thời giangian đóng cửa trên picker chọn thời
    TimePickerDialog.OnTimeSetListener onCloseTimeSet = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            AddNewPlaceActivity.this.text_view_close_time.setText(hourOfDay + ":" + minute);
        }
    };

    public AddNewPlaceActivity() {

    }
    //hàm xứ lí sự kiện khi Activity được khởi tạo (khởi tạo view)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_place_layout);

        initView();
        initEvent();
        initController();
        initPopup();
        setDefaultDisplay();
    }
    //Hàm khởi tạo các Controller để thực hiện việc lấy dữ liệu
    private void initController() {
        provinceController = new ProvinceController(this.getApplicationContext(), "vietnam", false);
        districtController = new DistrictController(this.getApplicationContext());
        menuBarItemController = new MenuBarItemController(this.getApplicationContext(), APIAction.GET_CATEGORY_WHAT2DO);
    }
    //Hàm khởi tạo các Popup
    private void initPopup() {
        buildPopup(POPUP_CHOOSE_PROVINCE);
        buildPopup(POPUP_CHOOSE_DISTRICT);
        buildPopup(POPUP_CHOOSE_RESTYPE);
    }
    //hàm khởi tạo các view chính của layout
    private void initView() {
        back_button_add_place = (LinearLayout) findViewById(R.id.back_button_add_place);
        text_view_done = (TextView) findViewById(R.id.text_view_done);
        text_view_choose_province = (TextView) findViewById(R.id.text_view_choose_province);
        text_view_choose_district = (TextView) findViewById(R.id.text_view_choose_district);

        edit_text_name_res = (EditText) findViewById(R.id.edit_text_name_res);
        linear_layout_choose_type_res = (LinearLayout) findViewById(R.id.linear_layout_choose_type_res);
        edit_text_address_res = (EditText) findViewById(R.id.edit_text_address_res);

        linear_layout_map_location = (LinearLayout) findViewById(R.id.linear_layout_map_location);
        text_view_lat_long = (TextView) findViewById(R.id.text_view_lat_long);
        linear_layout_phone_number = (LinearLayout) findViewById(R.id.linear_layout_phone_number);
        text_view_open_time = (TextView) findViewById(R.id.text_view_open_time);
        text_view_close_time = (TextView) findViewById(R.id.text_view_close_time);
        edit_text_max_cash = (EditText) findViewById(R.id.edit_text_max_cash);
        edit_text_min_cash = (EditText) findViewById(R.id.edit_text_min_cash);
        edit_text_short_descr = (EditText) findViewById(R.id.edit_text_short_descr);
        text_view_res_type = (TextView) findViewById(R.id.text_view_res_type);

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
    //hàm khởi tạo các sự kiện của các view
    private void initEvent() {
        back_button_add_place.setOnClickListener(this);
        text_view_choose_province.setOnClickListener(this);
        text_view_choose_district.setOnClickListener(this);
        linear_layout_choose_type_res.setOnClickListener(this);
        linear_layout_map_location.setOnClickListener(this);
        text_view_open_time.setOnClickListener(this);
        text_view_close_time.setOnClickListener(this);
        text_view_done.setOnClickListener(this);
        frame_layout_add_image.setOnClickListener(this);
    }
    //Hàm hiện thông tin ban đầu lên Layout
    private void setDefaultDisplay() {
        addLayoutNewPhone();
        text_view_choose_province.setText("Tp. HCM");
    }

    //Hàm thêm 1 view (add phonenumber)
    private void addLayoutNewPhone() {
        View v = View.inflate(this, R.layout.item_phone_layout, null);
        ImageView image_view_add_phone_number = (ImageView) v.findViewById(R.id.image_view_add_phone_number);
        image_view_add_phone_number.setImageDrawable(ContextCompat.getDrawable(this.getApplicationContext(), R.drawable.icon_plus_blue));
        image_view_add_phone_number.setTag(10);
        image_view_add_phone_number.setOnClickListener(new OnClickAddNumber(this.getApplicationContext(), image_view_add_phone_number, v));
        this.linear_layout_phone_number.addView(v);
    }
    //Hàm xự lí sự kiện nhận các giá trị trả về từ các Activity khác (Nhận hình ảnh, nhận vị trí lat_long từ google)
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == AppConfig.RESULT_CODE_FROM_GALLERY_FOLDER) {
            selectedImages = data.getParcelableArrayListExtra("images");
            this.selectedFileAdapter.removeAllSelectedSingleClick();
            this.selectedFileAdapter.addAll(selectedImages);
            updateNumofPhoto();
        } else if (resultCode == AppConfig.RESULT_CODE_CHOOSE_LOCATION) {
            lat = data.getDoubleExtra("lat", -1);
            lng = data.getDoubleExtra("long", -1);
            updateLatLong();
        }
    }
    //Hàm cập nhật số ảnh được chọn
    private void updateNumofPhoto() {
        if (this.selectedFileAdapter.imageSelected.size() > 0) {
            text_view_photo_count.setText("" + this.selectedFileAdapter.imageSelected.size());
            text_view_photo_count.setVisibility(View.VISIBLE);
        } else {
            text_view_photo_count.setText("");
            text_view_photo_count.setVisibility(View.GONE);
        }
    }
    //hàm cập nhật text_view lat long
    private void updateLatLong() {
        if (lat == -1 || lng == -1) {
            text_view_lat_long.setText("Lat " + "0" + " - " + "Long " + "0");
            return;
        }
        text_view_lat_long.setText("Lat " + this.lat + " - " + "Long " + this.lng);
    }
    //Hàm thực hiện việc gửi thông tin lên server
    private synchronized  void sendData() {
        if (curDistrictID == null || curDistrictID.equals("")) {
            GlobalFunction.shakeView(this.getApplicationContext(), text_view_choose_district);
            return;
        } else if (edit_text_name_res.getText().toString().trim().length() == 0) {
            GlobalFunction.shakeView(this.getApplicationContext(), edit_text_name_res);
            return;
        } else if (selectedResType.size() == 0) {
            GlobalFunction.shakeView(this.getApplicationContext(), linear_layout_choose_type_res);
            return;
        } else if (edit_text_address_res.getText().toString().trim().length() == 0) {
            GlobalFunction.shakeView(this.getApplicationContext(), edit_text_address_res);
            return;
        } else if (edit_text_min_cash.getText().toString().length() == 0 || edit_text_max_cash.getText().toString().length() == 0) {
            GlobalFunction.shakeView(this.getApplicationContext(), edit_text_min_cash);
            GlobalFunction.shakeView(this.getApplicationContext(), edit_text_max_cash);
            return;
        } else {
            for (int i = 0; i < this.linear_layout_phone_number.getChildCount(); i++) {
                View v = this.linear_layout_phone_number.getChildAt(i);
                String s = ((EditText) v.findViewById(R.id.edit_text_phone_number)).getText().toString();
                if (s.length() <= 0) {
                    GlobalFunction.shakeView(this.getApplicationContext(), v);
                    return;
                }
            }
        }
        JsonObject input = createJsonInput();
        new MyFoodyPostMethod(input, this.getApplicationContext(), new CallBackUploadRes()).execute("api/restaurant/post");
    }
    //hàm tạo dữ liệu để gửi lên server (Nhà hàng)
    private JsonObject createJsonInput() {
        RestaurantBean res = new RestaurantBean();
        res.setAddress(edit_text_address_res.getText().toString().trim());
        res.setTitle(edit_text_name_res.getText().toString().trim());
        res.setPhone(getAllPhoneNumber().trim());
        res.setId_province(curProvinceID);
        res.setId_district(curDistrictID);
        res.setWhere_type(selectedResType.get(0).getId());

        if (selectedImages.size() > 0) {
            res.setPhoto(GlobalFunction.createImageInputObject(selectedImages.get(0).getPath()).get("image").toString());
        }
        res.setPosition(new PositionBean(this.lat, this.lng));
        res.setOpenTime(text_view_open_time.getText().toString());
        res.setCloseTime(text_view_close_time.getText().toString());
        res.setMinCash(Double.parseDouble(edit_text_min_cash.getText().toString().trim()));
        res.setMaxCash(Double.parseDouble(edit_text_max_cash.getText().toString().trim()));
        if (selectedImages.size() > 1) {
            res.setListImage(getListMoreItem(res.getId()));
        }
        Gson gson = new Gson();

        return gson.toJsonTree(res).getAsJsonObject();
    }
    //Hàm xây dựng danh sách ảnh cho nhà hàng
    private List<MoreImageRestaurantBean> getListMoreItem(final String resid) {
        List<MoreImageRestaurantBean> list = new ArrayList<>();
        for (int i = 1; i < selectedImages.size(); i++) {
            MoreImageRestaurantBean item = new MoreImageRestaurantBean(UUID.randomUUID().toString(), resid,
                    GlobalFunction.decodeImage2Base64(selectedImages.get(i).getPath()));

            list.add(item);
        }
        return list;
    }
    //Hàm lấy số điện thoại từ tất cả các view AddPhoneNumber
    private String getAllPhoneNumber() {
        String result = "";
        for (int i = 0; i < this.linear_layout_phone_number.getChildCount(); i++) {
            View v = this.linear_layout_phone_number.getChildAt(i);
            String s = ((EditText) v.findViewById(R.id.edit_text_phone_number)).getText().toString();
            result = result + s + "-";
        }
        return result.substring(0, result.length() - 1);
    }
    //Hàm cập naht65 text_view khi chọn loại nhà hàng
    private void updateTextResType() {
        this.text_view_res_type.setText("");
        String resType = "";
        if (selectedResType.size() <= 0) {
            this.text_view_res_type.setText(getString(R.string.TEXT_BUSSINESS_TYPE));
            return;
        }
        for (MenuBarItemBean i : selectedResType) {
            resType += i.getTittle() + ", ";
        }

        resType = resType.substring(0, resType.length() - 2);
        this.text_view_res_type.setText(resType);
    }
    //hàm hiện thông báo
    private void showAlert(String mess,final boolean isSuccess) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);


        alertDialogBuilder
                .setMessage(mess)
                .setCancelable(false)
                .setPositiveButton(getString(R.string.TEXT_ACTION_CLOSE), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                       if(isSuccess) {
                           finish();
                       }
                    }
                });

        alertDialogBuilder.show();
    }

    /**
     *   Hàm xây dựng các popup
     *   Popup chọn tỉnh, chọn huyện và popup chọn loại nhà hàng
     */


    private void buildPopup(int type) {
        if (type == POPUP_CHOOSE_PROVINCE) {
            dialogChooseProvince = new ListItemDialog(this);
            choosePronvinceDialogAdapter = new DialogAdapter(this.getApplicationContext(), getDataDialog(type));
            dialogChooseProvince
                    .setMode(ListItemDialog.NO_SEARCH)
                    .setTitle(getString(R.string.TEXT_CHOOSE_PROVINCE))
                    .setLeftButton(getString(R.string.TEXT_ACTION_BOQUA), new OnCancelDialogClick(dialogChooseProvince))
                    .setAdapter(choosePronvinceDialogAdapter)
                    .setOnItemClickListener(new OnItemInDialogClick(POPUP_CHOOSE_PROVINCE, choosePronvinceDialogAdapter));
        } else if (type == POPUP_CHOOSE_DISTRICT) {
            dialogChooseDistrict = new ListItemDialog(this);
            chooseDistrictDialogAdapter = new DialogAdapter(this.getApplicationContext(), getDataDialog(type));
            dialogChooseDistrict
                    .setMode(ListItemDialog.SEARCH)
                    .setTitle(getString(R.string.TEXT_CHOOSE_DISTRICT))
                    .setLeftButton(getString(R.string.TEXT_ACTION_BOQUA), new OnCancelDialogClick(dialogChooseDistrict))
                    .setAdapter(chooseDistrictDialogAdapter)
                    .setOnItemClickListener(new OnItemInDialogClick(POPUP_CHOOSE_DISTRICT, chooseDistrictDialogAdapter));
        } else if (type == POPUP_CHOOSE_RESTYPE) {
            dialogChooseResType = new ListItemDialogTypeRes(this);
            chooseResTypeAdapter = new DialogAdapter(this.getApplicationContext(), getDataDialog(type));
            dialogChooseResType
                    .setTitle("Loại hình")
                    .setLeftButton(getString(R.string.TEXT_ACTION_CANCEL), new OnCancelDialogClick(dialogChooseResType))
                    .setRightButton(getString(R.string.TEXT_ACTION_DONE), new OnDoneChooseResType(chooseResTypeAdapter))
                    .setAdapter(chooseResTypeAdapter)
                    .setOnItemClickListener(new OnItemInDialogClick(POPUP_CHOOSE_RESTYPE, chooseResTypeAdapter));
        }

    }
    //Hàm tạo dữ liệu cho các popup
    private List<MenuBarItemBean> getDataDialog(int type) {
        List<MenuBarItemBean> list = new ArrayList<>();
        try {
            if (type == POPUP_CHOOSE_PROVINCE) {
                List<ProvinceBean> provinceBeanList = provinceController.getListProvince();
                if (provinceBeanList != null && provinceBeanList.size() > 0) {
                    for (ProvinceBean i : provinceBeanList) {
                        list.add(new MenuBarItemBean(i.getId(), i.gettitle(), "", false));
                    }
                    list.get(positionSelectedProvince).setSelected(true);
                }
            } else if (type == POPUP_CHOOSE_DISTRICT) {
                List<DistrictBean> distrctBeanList = districtController.getListDistrict(this.curProvinceID);
                if (distrctBeanList != null && distrctBeanList.size() > 0) {
                    for (DistrictBean i : distrctBeanList) {
                        list.add(new MenuBarItemBean(i.getId(), i.gettitle(), "", false));
                    }
                }
            } else if (type == POPUP_CHOOSE_RESTYPE) {
                List<MenuBarItemBean> menuBarItemBeanList = menuBarItemController.getListMenuBar_WHERE();
                if (menuBarItemBeanList != null && menuBarItemBeanList.size() > 0) {
                    for (int i = 1; i < 16; i++) {
                        list.add(menuBarItemBeanList.get(i));
                    }
                }

            }
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return list;
    }

    @Override
    public void onClickReviewImage(View v, int index) {

    }
    //hàm xử lí sự kiện khi click vào các ảnh được chọn (xóa ảnh)
    @Override
    public void onClickImage(View v, final int index) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Xóa");
        builder.setMessage("Bạn có muốn xóa ảnh?");
        builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                selectedFileAdapter.removeSelectedImage(index);
                selectedImages.remove(index);
                updateNumofPhoto();
            }
        });

        builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }
    //Hàm xử lí sự kiện onLCick
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back_button_add_place:
                finish();
                break;
            case R.id.text_view_done:
                sendData();
                break;
            case R.id.text_view_choose_province:
                dialogChooseProvince.show();
                break;
            case R.id.text_view_choose_district:
                dialogChooseDistrict.show();
                break;
            case R.id.linear_layout_choose_type_res:
                dialogChooseResType.show();
                break;
            case R.id.linear_layout_map_location:
                if (PermissionUtil.isGPSPermission(this)) {
                    Intent intentLocation = new Intent(this, MapsActivity.class);
                    intentLocation.putExtra("lat", lat);
                    intentLocation.putExtra("long", lng);
                    startActivityForResult(intentLocation, 1);
                }
                break;
            case R.id.text_view_open_time:
                this.openTimePiker.show();
                break;
            case R.id.text_view_close_time:
                this.closeTimePiker.show();
                break;
            case R.id.frame_layout_add_image:
                if (PermissionUtil.isReadWritePermission_LowAPI(this)) {
                    Intent intent = new Intent(this, GalleryFolderActivity.class);
                    intent.putExtra("mode", GalleryFolderActivity.MULTI_SELECT);
                    intent.putParcelableArrayListExtra("images", this.selectedFileAdapter.imageSelected);
                    startActivityForResult(intent, 1);
                    break;
                }
                PermissionUtil.isReadWritePermission_HighAPI(this);
                break;
        }
    }
    //Class thực hiện việc khi nhấn vào thêm 1 view addPhoneNumber
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
    //Class thực hiện việc call back lại kết quả từ việc upload nhà hàng
    class CallBackUploadRes implements ICallBackAsynsTask<JsonObject> {
        @Override
        public void onSuccess(JsonObject object) {
            showAlert("Upload thành công",true);

        }

        @Override
        public void onFail(JsonObject object) {
            showAlert("Upload thất bại",false);
        }
    }
    //Class thực hiện việc khi nhấn nút xong trong popup chọn loại nhà hàng
    class OnDoneChooseResType implements View.OnClickListener {

        DialogAdapter adapter;

        public OnDoneChooseResType(DialogAdapter adapter) {
            this.adapter = adapter;
        }

        @Override
        public void onClick(View v) {
            selectedResType.clear();
            for (int i = 0; i < adapter.data.size(); i++) {
                if (adapter.data.get(i).isSelected()) {
                    selectedResType.add(adapter.data.get(i));
                }
            }
            updateTextResType();
            dialogChooseResType.dismiss();
        }
    }
    //Class thực hiện việc nhấn nút hủy trong popup
    class OnCancelDialogClick implements View.OnClickListener {
        Dialog dialog;

        public OnCancelDialogClick(Dialog dialog) {
            this.dialog = dialog;
        }

        @Override
        public void onClick(View v) {
            this.dialog.dismiss();
        }
    }
    //Class thực hiện việc click vào các item trên popup
    class OnItemInDialogClick implements AdapterView.OnItemClickListener {
        int type;
        DialogAdapter adapter;

        public OnItemInDialogClick(int type, DialogAdapter adapter) {
            this.type = type;
            this.adapter = adapter;
        }

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            if (type != POPUP_CHOOSE_RESTYPE) {
                if (adapter != null && adapter.data.size() > 0) {
                    for (int i = 0; i < adapter.data.size(); i++) {
                        adapter.data.get(i).setSelected(false);
                    }
                }
                ((MenuBarItemBean) adapter.getItem(position)).setSelected(true);
                MenuBarItemBean item = ((MenuBarItemBean) adapter.getItem(position));
                if (type == POPUP_CHOOSE_PROVINCE) {
                    positionSelectedProvince = position;
                    dialogChooseProvince.refreshData();
                    dialogChooseProvince.dismiss();
                    curProvinceID = item.getId();
                    text_view_choose_province.setText(item.getTittle());
                    chooseDistrictDialogAdapter.data = getDataDialog(POPUP_CHOOSE_DISTRICT);
                    text_view_choose_district.setText(getString(R.string.TEXT_CHOOSE_DISTRICT));
                    curDistrictID = "";
                    dialogChooseDistrict.refreshData();
                } else if (type == POPUP_CHOOSE_DISTRICT) {
                    positionSelectedDistrict = position;
                    dialogChooseDistrict.refreshData();
                    dialogChooseDistrict.dismiss();
                    curDistrictID = item.getId();
                    text_view_choose_district.setText(item.getTittle());
                }
            } else if (type == POPUP_CHOOSE_RESTYPE) {

                MenuBarItemBean item = ((MenuBarItemBean) adapter.getItem(position));
                if (item.isSelected()) {
                    ((MenuBarItemBean) adapter.getItem(position)).setSelected(false);
                } else {
                    ((MenuBarItemBean) adapter.getItem(position)).setSelected(true);
                }
                dialogChooseResType.refreshData();
            }
        }
    }
    //Adapter cho các Popup
    class DialogAdapter extends BaseAdapter {
        Context context;
        List<MenuBarItemBean> data;
        //Hàm khởi tạo
        public DialogAdapter(Context context, List<MenuBarItemBean> data) {
            super();
            this.context = context;
            this.data = data;
        }
        //Hàm trả về số lượng Item trong adapter
        @Override
        public int getCount() {
            return data.size();
        }
        //Hàm trả về item tương ứng với vị trí
        @Override
        public Object getItem(int position) {
            return data.get(position);
        }
        //hàm trả về id của item
        @Override
        public long getItemId(int position) {
            return position;
        }
        //Hàm hiện dữ liệu lên View
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

            if (item.isSelected()) {
                holder.list_view_item_menu_tab_is_selected.setVisibility(View.VISIBLE);
            } else {
                holder.list_view_item_menu_tab_is_selected.setVisibility(View.GONE);
            }

            return convertView;
        }

        @Override
        public int getViewTypeCount() {
            return getCount() == 0 ? 1 : getCount();
        }

        @Override
        public int getItemViewType(int position) {
            return position;
        }


        //ViewHolder
        class ViewHolder {
            View v;
            TextView list_view_item_menu_tab_text;
            ImageView list_view_item_menu_tab_is_selected;

            public ViewHolder(View v) {
                this.v = v;
                this.list_view_item_menu_tab_text = (TextView) v.findViewById(R.id.list_view_item_menu_tab_text);
                this.list_view_item_menu_tab_is_selected = (ImageView) v.findViewById(R.id.list_view_item_menu_tab_is_selected);
            }
        }
    }
}