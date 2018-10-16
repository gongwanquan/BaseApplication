package com.dms.base.baseapplication.utils;

import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.support.v4.content.FileProvider;
import android.text.TextUtils;
import android.webkit.MimeTypeMap;

import com.dms.base.baseapplication.BuildConfig;

import java.io.File;

public class DownloadUtils {
    private Context context;
    private String url;
    private String notificationTitle;
    private String notificationDescription;
    private DownloadManager downLoadManager;
    public static final String DOWNLOAD_FOLDER_NAME = "app/apk/download";
    public static final String DOWNLOAD_FILE_NAME = "test.apk";

    public String getNotificationTitle() {
        return notificationTitle;
    }

    public void setNotificationTitle(String notificationTitle) {
        this.notificationTitle = notificationTitle;
    }

    public String getNotificationDescription() {
        return notificationDescription;
    }

    public void setNotificationDescription(String notificationDescription) {
        this.notificationDescription = notificationDescription;
    }

    public DownloadUtils(Context context) {
        this.context = context;
        downLoadManager = (DownloadManager) this.context.getSystemService(Context.DOWNLOAD_SERVICE);
    }

    //得到当前应用的版本号
    public int getVersionName() throws Exception {
        //getPackageName()是你当前类的包名，0代表是获取版本信息
        PackageManager packageManager = context.getPackageManager();
        PackageInfo packInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
        return packInfo.versionCode;
    }

    /**
     * 服务端的版本号与客户端的版本号对比	 * @param localVersion  本地版本号	 * @param serverVersion  服务器版本号	 * @return  true 可以下载更新  false 不能下载更新
     */
    public boolean canUpdate(int localVersion, int serverVersion) {
        if (localVersion <= 0 || serverVersion <= 0) return false;
        if (localVersion >= serverVersion) {
            return false;
        }
        return true;
    }

    public void downLoad(String url) {
        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(url));
        //设置状态栏中显示Notification
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        if (!TextUtils.isEmpty(getNotificationTitle())) {
            request.setTitle(getNotificationTitle());
        }
        if (!TextUtils.isEmpty(getNotificationDescription())) {
            request.setDescription(getNotificationDescription());
        }
        //设置可用的网络类型
        request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_MOBILE | DownloadManager.Request.NETWORK_WIFI);
        //不显示下载界面
        request.setVisibleInDownloadsUi(false);
        //创建文件的下载路径
        File folder = Environment.getExternalStoragePublicDirectory(DOWNLOAD_FOLDER_NAME);
        if (!folder.exists() || !folder.isDirectory()) {
            folder.mkdirs();
        }
        //指定下载的路径为和上面创建的路径相同
        request.setDestinationInExternalPublicDir(DOWNLOAD_FOLDER_NAME, DOWNLOAD_FILE_NAME);
        //设置文件类型
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        String mimeString = mimeTypeMap.getMimeTypeFromExtension(MimeTypeMap.getFileExtensionFromUrl(url));
        request.setMimeType(mimeString);
        //将请求加入请求队列会 downLoadManager会自动调用对应的服务执行者个请求
        downLoadManager.enqueue(request);
    }


    public static void installApk(Context context, String filePath) {
        File file = new File(filePath);
        Uri uri = Uri.fromFile(file);
        Intent intent = new Intent(Intent.ACTION_VIEW);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            Uri contentUri = FileProvider.getUriForFile(context, BuildConfig.APPLICATION_ID + ".fileProvider", file);
            intent.setDataAndType(contentUri, "application/vnd.android.package-archive");
        } else {
            intent.setDataAndType(uri, "application/vnd.android.package-archive");
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        }
        context.startActivity(intent);
    }

}
