package com.dms.base.baseapplication.broadcast;

import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Environment;

import com.dms.base.baseapplication.utils.DownloadUtils;

import java.io.File;

public class DownloadReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(DownloadManager.ACTION_DOWNLOAD_COMPLETE)) {
            String filePath = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + DownloadUtils.DOWNLOAD_FOLDER_NAME + File.separator + DownloadUtils.DOWNLOAD_FILE_NAME;
            DownloadUtils.installApk(context.getApplicationContext(), filePath);
        }
    }
}
