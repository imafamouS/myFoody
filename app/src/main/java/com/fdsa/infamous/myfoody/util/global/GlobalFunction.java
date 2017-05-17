package com.fdsa.infamous.myfoody.util.global;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.View;
import android.view.animation.AnimationUtils;

import com.fdsa.infamous.myfoody.R;
import com.google.gson.JsonObject;

import java.io.ByteArrayOutputStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.UUID;

/**
 * Created by FDSA on 4/2/2017.
 */

public class GlobalFunction {
    //Hàm chuyển từ Dp sang pixel
    public static int dpToPx(float dp) {
        return (int) (dp * Resources.getSystem().getDisplayMetrics().density);
    }

    //Hàm làm tròn số lấy 1 chữ số thập phân
    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

    public static void shakeView(Context context, View v) {
        v.startAnimation(AnimationUtils.loadAnimation(context, R.anim.shake));
    }

    public static Bitmap decodeSampledBitmapFromResource(Resources res, int resId,
                                                         int reqWidth, int reqHeight) {

        // First decode with inJustDecodeBounds=true to check dimensions
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(res, resId, options);

        // Calculate inSampleSize
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);

        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeResource(res, resId, options);
    }

    public static int calculateInSampleSize(
            BitmapFactory.Options options, int reqWidth, int reqHeight) {
        // Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {

            // Calculate ratios of height and width to requested height and width
            final int heightRatio = Math.round((float) height / (float) reqHeight);
            final int widthRatio = Math.round((float) width / (float) reqWidth);

            // Choose the smallest ratio as inSampleSize value, this will guarantee
            // a final image with both dimensions larger than or equal to the
            // requested height and width.
            inSampleSize = heightRatio < widthRatio ? heightRatio : widthRatio;
        }

        return inSampleSize;
    }

    public static JsonObject createImageInputObject(String path) {
        JsonObject outputObject = null;

        Bitmap myBitmap = BitmapFactory.decodeFile(path);
        Bitmap scaledBitmap = Bitmap.createScaledBitmap(myBitmap, 500, 500, true);
        //File directory = new File(path);
        //InputStream inputStream = new FileInputStream(directory);
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        scaledBitmap.compress(Bitmap.CompressFormat.PNG, 0, bos);
        byte[] byteArray = bos.toByteArray();


        String str = Base64.encodeToString(byteArray, Base64.NO_WRAP);


        outputObject = new JsonObject();
        outputObject.addProperty("id", UUID.randomUUID().toString());

        outputObject.addProperty("image", str);


        return outputObject;
    }

    public static String decodeImage2Base64(String path) {
        Bitmap myBitmap = BitmapFactory.decodeFile(path);
        Bitmap scaledBitmap = Bitmap.createScaledBitmap(myBitmap, 300, 300, true);
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        scaledBitmap.compress(Bitmap.CompressFormat.PNG, 0, bos);
        byte[] byteArray = bos.toByteArray();
        String str = Base64.encodeToString(byteArray, Base64.DEFAULT);
        return  str;
    }

    public static JsonObject createImageInputObject_test(String path) {
        JsonObject outputObject = null;

        Bitmap myBitmap = BitmapFactory.decodeFile(path);
        Bitmap scaledBitmap = Bitmap.createScaledBitmap(myBitmap, 500, 500, true);
        //File directory = new File(path);
        //InputStream inputStream = new FileInputStream(directory);
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        scaledBitmap.compress(Bitmap.CompressFormat.PNG, 0, bos);
        byte[] byteArray = bos.toByteArray();


        String str = Base64.encodeToString(byteArray, Base64.DEFAULT);


        outputObject = new JsonObject();
        outputObject.addProperty("id", UUID.randomUUID().toString());

        outputObject.addProperty("image", str);


        return outputObject;
    }

    public static boolean isRestaurantOpening(String opentime, String closetime) {

        try {
            SimpleDateFormat format = new SimpleDateFormat("hh:mm");
            Calendar calendar1 = Calendar.getInstance();
            calendar1.setTime(format.parse("9:0"));
            Calendar calendar2 = Calendar.getInstance();
            calendar2.setTime(format.parse("21:50"));
            Calendar currentTime = Calendar.getInstance();
            currentTime.setTime(format.parse(currentTime.get(Calendar.HOUR_OF_DAY) + ":" + currentTime.get(Calendar.MINUTE)));

            if (currentTime.compareTo(calendar1) > 0 && currentTime.compareTo(calendar2) < 0) {
                return true;
            } else {
                return false;
            }
        } catch (ParseException e) {
            e.printStackTrace();
            return false;
        }
    }

}
