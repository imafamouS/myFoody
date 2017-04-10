package com.fdsa.infamous.myfoody.global;

import android.content.res.Resources;

import java.math.BigDecimal;
import java.math.RoundingMode;

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
}
