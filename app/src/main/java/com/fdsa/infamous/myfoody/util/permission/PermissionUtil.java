package com.fdsa.infamous.myfoody.util.permission;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build.VERSION;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;

import com.fdsa.infamous.myfoody.R;
import com.fdsa.infamous.myfoody.config.AppConfig;
import com.fdsa.infamous.myfoody.config.ManifestConfig;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by apple on 5/2/17.
 */

public class PermissionUtil {

    public static boolean isReadWritePermission(Context context) {
        return VERSION.SDK_INT < 23 || (VERSION.SDK_INT >= 23 &&
                context.checkSelfPermission(android.Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED &&
                context.checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED);
    }

    public static void marshmallowReadWritePermissionCheck(Activity context) {
        if (VERSION.SDK_INT >= 23 && !Settings.System.canWrite(context)) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(context, ManifestConfig.READ_EXTERNAL_STORAGE)
                    && ActivityCompat.shouldShowRequestPermissionRationale(context, ManifestConfig.WRITE_EXTERNAL_STORAGE)) {
                showPopupPermission(context,
                        new ArrayList(Arrays.asList(
                                new String[]{context.getString(R.string.TEXT_PERMISSION_STORAGE)})));
                return;
            }
            ActivityCompat.requestPermissions(context,
                    new String[]{
                            ManifestConfig.WRITE_EXTERNAL_STORAGE,
                            ManifestConfig.READ_EXTERNAL_STORAGE},
                    AppConfig.REQUEST_READ_WRITE_PERMISSION_CODE);
        }
    }

    private static void showPopupPermission(final Activity context, ArrayList<String> listPermissions) {
        String message = context.getString(R.string.TEXT_REQUEST_PERMISSION);
        if (listPermissions != null && listPermissions.size() > 0) {
            if (listPermissions.size() == 1) {
                message = message + "\n";
            }
            for (String i : listPermissions) {
                message += "\n  - " + i;

            }
        }
        new AlertDialog.Builder(context)
                .setMessage(message)
                .setPositiveButton(context.getString(R.string.TEXT_YES), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent("android.settings.APPLICATION_DETAILS_SETTINGS");
                        intent.setData(Uri.fromParts("package", context.getPackageName(), null));
                        context.startActivityForResult(intent, AppConfig.REQUEST_READ_WRITE_PERMISSION_CODE);
                        dialog.dismiss();

                    }
                })
                .setNegativeButton(context.getString(R.string.TEXT_NO), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .setCancelable(true)
                .show();
    }
}
