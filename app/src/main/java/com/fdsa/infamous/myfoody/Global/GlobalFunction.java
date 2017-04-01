package com.fdsa.infamous.myfoody.Global;

import android.content.res.Resources;

/**
 * Created by FDSA on 4/2/2017.
 */

public class GlobalFunction {
    public static int dpToPx(float dp) {
        return (int) (dp * Resources.getSystem().getDisplayMetrics().density);
    }
}
