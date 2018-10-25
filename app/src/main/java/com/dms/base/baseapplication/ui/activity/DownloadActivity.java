package com.dms.base.baseapplication.ui.activity;

import android.Manifest;
import android.app.DownloadManager;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;

import com.dms.base.baseapplication.R;
import com.dms.base.baseapplication.broadcast.DownloadReceiver;
import com.dms.base.baseapplication.net.ApiManager;
import com.dms.base.baseapplication.ui.widget.LoadingDialog;
import com.dms.base.baseapplication.utils.DownloadUtils;
import com.dms.base.baseproject.net.transformer.SchedulerTransformer;
import com.dms.base.baseproject.ui.activity.BaseUIActivity;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import me.jessyan.progressmanager.ProgressListener;
import me.jessyan.progressmanager.ProgressManager;
import me.jessyan.progressmanager.body.ProgressInfo;
import okhttp3.ResponseBody;

public class DownloadActivity extends BaseUIActivity {

    private DownloadReceiver mDownloadReceiver;

    @Override
    public int getLayoutId() {
        return R.layout.activity_download;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mDownloadReceiver = new DownloadReceiver();
        IntentFilter itFilter = new IntentFilter();
        itFilter.addAction(DownloadManager.ACTION_DOWNLOAD_COMPLETE);
        registerReceiver(mDownloadReceiver, itFilter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(mDownloadReceiver);
    }

    @OnClick(R.id.download_btn)
    public void onViewClicked() {
        String url = "https://raw.githubusercontent.com/getlantern/lantern-binaries/master/lantern-installer.apk";
//        downloadWithNotification(url);
        downloadWithDialog(url);
    }

    private void downloadWithDialog(String url) {
        final String filePath = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + DownloadUtils.DOWNLOAD_FOLDER_NAME + File.separator + DownloadUtils.DOWNLOAD_FILE_NAME;

        Uri uri = Uri.parse(url);
        final LoadingDialog loadingDialog = new LoadingDialog();

        ProgressManager.getInstance().addResponseListener("https://raw.githubusercontent.com/getlantern/lantern-binaries/master/lantern-installer.apk", new ProgressListener() {

            @Override
            public void onProgress(ProgressInfo progressInfo) {
                int percent = progressInfo.getPercent();
                loadingDialog.setLoadingTxt(percent + "%");
            }

            @Override
            public void onError(long id, Exception e) {

            }
        });

        ApiManager.getFileService().downloadFile(uri)
                .compose(new SchedulerTransformer<ResponseBody>())
                .flatMap(new Function<ResponseBody, ObservableSource<Boolean>>() {
                    @Override
                    public ObservableSource<Boolean> apply(ResponseBody responseBody) throws Exception {
                        return Observable.just(writeFileToSDCard(responseBody, filePath));
                    }
                }).subscribe(new Observer<Boolean>() {
            @Override
            public void onSubscribe(Disposable d) {
                loadingDialog.show(getSupportFragmentManager(), "loading");
            }

            @Override
            public void onNext(Boolean aBoolean) {
                if (aBoolean) {
                    DownloadUtils.installApk(DownloadActivity.this, filePath);
                }
            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
            }

            @Override
            public void onComplete() {
                loadingDialog.dismiss();
            }
        });
    }

    private boolean writeFileToSDCard(ResponseBody body, String filePath) {
        try {
            File futureStudioIconFile = new File(filePath);
            InputStream inputStream = null;
            OutputStream outputStream = null;
            try {
                byte[] fileReader = new byte[4096];
                long fileSize = body.contentLength();
                long fileSizeDownloaded = 0;
                inputStream = body.byteStream();
                outputStream = new FileOutputStream(futureStudioIconFile);
                while (true) {
                    int read = inputStream.read(fileReader);
                    if (read == -1) {
                        break;
                    }
                    outputStream.write(fileReader, 0, read);
                    fileSizeDownloaded += read;
                }
                outputStream.flush();
                return true;
            } catch (IOException e) {
                return false;
            } finally {
                if (inputStream != null) {
                    inputStream.close();
                }
                if (outputStream != null) {
                    outputStream.close();
                }
            }
        } catch (IOException e) {
            return false;
        }
    }

    private void downloadWithNotification(final String url) {
        RxPermissions rxPermissions = new RxPermissions(this);
        rxPermissions.request(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean aBoolean) throws Exception {
                        if (aBoolean) {
                            DownloadUtils downloadUtils = new DownloadUtils(DownloadActivity.this);
                            downloadUtils.setNotificationTitle("蓝灯");
                            downloadUtils.setNotificationDescription("正在下载...");
                            downloadUtils.downLoad(url);
                        } else {
                            showMessage("需要文件权限才能下载！");
                        }
                    }
                });
    }
}
