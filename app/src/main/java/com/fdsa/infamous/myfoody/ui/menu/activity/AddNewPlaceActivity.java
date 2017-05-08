package com.fdsa.infamous.myfoody.ui.menu.activity;

import android.app.TimePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.fdsa.infamous.myfoody.R;
import com.fdsa.infamous.myfoody.util.global.GlobalStaticData;

import java.util.Calendar;

/**
 * Created by apple on 5/8/17.
 */

public class AddNewPlaceActivity extends AppCompatActivity implements View.OnClickListener {
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
    EditText  edit_text_min_cash;
    EditText edit_text_short_descr;

    FrameLayout frame_layout_add_image;

    TimePickerDialog openTimePiker;
    TimePickerDialog closeTimePiker;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_place_layout);
        initView();
        initEvent();
        setDefaultDisplay();
    }
    private void initView(){
        text_view_choose_province=(TextView)findViewById(R.id.text_view_choose_province);
        text_view_choose_district=(TextView)findViewById(R.id.text_view_choose_district);

        edit_text_name_res=(EditText)findViewById(R.id.edit_text_name_res);
        linear_layout_choose_type_res=(LinearLayout)findViewById(R.id.linear_layout_choose_type_res);
        edit_text_address_res=(EditText)findViewById(R.id.edit_text_address_res);

        linear_layout_map_location=(LinearLayout)findViewById(R.id.linear_layout_map_location);
        linear_layout_phone_number=(LinearLayout)findViewById(R.id.linear_layout_phone_number);
        text_view_open_time=(TextView)findViewById(R.id.text_view_open_time);
        text_view_close_time=(TextView)findViewById(R.id.text_view_close_time);
        edit_text_max_cash=(EditText)findViewById(R.id.edit_text_max_cash);
        edit_text_min_cash=(EditText)findViewById(R.id.edit_text_min_cash);
        edit_text_short_descr=(EditText)findViewById(R.id.edit_text_short_descr);

        frame_layout_add_image=(FrameLayout)findViewById(R.id.frame_layout_add_image);

        final Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);

        openTimePiker=new TimePickerDialog(this,onOpenTimeSet,hour,minute,true);
        closeTimePiker=new TimePickerDialog(this,onCloseTimeSet,hour,minute,true);

    }
    TimePickerDialog.OnTimeSetListener onOpenTimeSet=new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            AddNewPlaceActivity.this.text_view_open_time.setText(hourOfDay+":"+minute);
        }
    };
    TimePickerDialog.OnTimeSetListener onCloseTimeSet=new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            AddNewPlaceActivity.this.text_view_close_time.setText(hourOfDay+":"+minute);
        }
    };
    private void initEvent(){
        text_view_choose_province.setOnClickListener(this);
        text_view_choose_district.setOnClickListener(this);
        linear_layout_choose_type_res.setOnClickListener(this);
        linear_layout_map_location.setOnClickListener(this);
        text_view_open_time.setOnClickListener(this);
        text_view_close_time.setOnClickListener(this);

        frame_layout_add_image.setOnClickListener(this);
    }

    private void setDefaultDisplay(){
        addLayoutNewPhone();
        text_view_choose_province.setText(GlobalStaticData.getCurrentProvinceBean().gettitle());
    }


    private void addLayoutNewPhone(){
        View v=View.inflate(this, R.layout.item_phone_layout, null);
        ImageView image_view_add_phone_number=(ImageView)v.findViewById(R.id.image_view_add_phone_number);
        image_view_add_phone_number.setImageDrawable(ContextCompat.getDrawable(this.getApplicationContext(),R.drawable.icon_plus_blue));
        image_view_add_phone_number.setTag(10);
        image_view_add_phone_number.setOnClickListener(new OnClickAddNumber(this.getApplicationContext(),image_view_add_phone_number,v));
        this.linear_layout_phone_number.addView(v);
    }
    class OnClickAddNumber implements View.OnClickListener{
        Context context;
        ImageView image_view_add_phone_number;
        View layout;
        public OnClickAddNumber(Context context,ImageView image_view_add_phone_number,View layout){
            this.context=context;
            this.image_view_add_phone_number=image_view_add_phone_number;
            this.layout=layout;
        }

        @Override
        public void onClick(View v) {
            if((Integer)image_view_add_phone_number.getTag()==10){
                addLayoutNewPhone();
                image_view_add_phone_number.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.icon_minus_gray));
                image_view_add_phone_number.setTag(100);
                return;
            }
            AddNewPlaceActivity.this.linear_layout_phone_number.removeView(layout);
            ImageView image_view_add_phone_number_last = (ImageView) AddNewPlaceActivity.this.linear_layout_phone_number
                    .getChildAt(AddNewPlaceActivity.this.linear_layout_phone_number.getChildCount() - 1)
                    .findViewById(R.id.image_view_add_phone_number);
            image_view_add_phone_number_last.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.icon_plus_blue));
            image_view_add_phone_number_last.setTag(10);

        }
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.text_view_choose_province:
                Toast.makeText(this, "Choose province", Toast.LENGTH_SHORT).show();
                break;
            case R.id.text_view_choose_district:
                Toast.makeText(this, "Choose district", Toast.LENGTH_SHORT).show();
                break;
            case R.id.linear_layout_choose_type_res:
                Toast.makeText(this, "Choose restype", Toast.LENGTH_SHORT).show();
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
                break;
        }
    }

}
