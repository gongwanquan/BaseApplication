package com.dms.base.baseapplication.ui.activity;


import android.Manifest;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.widget.ImageView;
import com.dms.base.baseapplication.BuildConfig;
import com.dms.base.baseapplication.R;
import com.dms.base.baseapplication.img.Glide4Engine;
import com.dms.base.baseapplication.img.ImgLoadHelper;
import com.dms.base.baseproject.ui.activity.BaseUIActivity;
import com.tbruyelle.rxpermissions2.RxPermissions;
import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;
import com.zhihu.matisse.internal.entity.CaptureStrategy;
import java.io.File;
import java.io.IOException;
import java.util.List;
import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.functions.Consumer;

public class GetPhotoActivity extends BaseUIActivity {

    private final int REQUEST_CODE_CHOOSE = 10001;

    private final int REQUEST_PICKER_AND_CROP = 10002;

    @BindView(R.id.result_iv)
    ImageView resultIv;

    @Override
    public int getLayoutId() {
        return R.layout.activity_get_photo;
    }

    @OnClick(R.id.get_photo_btn)
    public void onViewClicked() {
        getPhoto();
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        super.initView(savedInstanceState);
        mTitleBar.setTitle("照片");
    }

    private void getPhoto() {
        RxPermissions rxPermissions = new RxPermissions(this);
        rxPermissions.request(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean aBoolean) throws Exception {
                        if (aBoolean) {
                            Matisse.from(GetPhotoActivity.this)
                                    .choose(MimeType.ofImage())
                                    .capture(true)
                                    .captureStrategy(new CaptureStrategy(true, BuildConfig.APPLICATION_ID + ".fileProvider"))
                                    .theme(R.style.Matisse_Zhihu)
                                    .countable(true)
                                    .maxSelectable(1)
//                .addFilter(new GifSizeFilter(320, 320, 5 * Filter.K * Filter.K))
//                .gridExpectedSize(getResources().getDimensionPixelSize(R.dimen.grid_expected_size))
                                    .restrictOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED)
                                    .thumbnailScale(0.85f)
                                    .imageEngine(new Glide4Engine())
                                    .forResult(REQUEST_CODE_CHOOSE);
                        } else {
                            showMessage("图片选择需要相机以及文件选择权限！");
                        }
                    }
                });

    }

    List<Uri> mSelected;
    Uri mOutPutUri;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if(requestCode == REQUEST_CODE_CHOOSE ) {
                mSelected = Matisse.obtainResult(data);
                if (null != mSelected && !mSelected.isEmpty()) {

                    File outputFile = new File(Environment.getExternalStorageDirectory().getPath() + File.separator + "temp.jpg");
                    if(!outputFile.exists()) {
                        try {
                            outputFile.createNewFile();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    mOutPutUri = Uri.fromFile(outputFile);


                    Intent intent = new Intent("com.android.camera.action.CROP");
                    intent.setDataAndType(mSelected.get(0), "image/*");
                    intent.putExtra("crop", "true");
                    intent.putExtra("aspectX", 1);
                    intent.putExtra("aspectY", 1);
                    intent.putExtra("outputX", 300);
                    intent.putExtra("outputY", 300);
                    intent.putExtra("scale", true);
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, mOutPutUri);
                    intent.putExtra("return-data", false);
                    intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
                    intent.putExtra("noFaceDetection", true); // no face detection
                    intent = Intent.createChooser(intent, "裁剪图片");
                    startActivityForResult(intent, REQUEST_PICKER_AND_CROP);

                }
            } else if(requestCode == REQUEST_PICKER_AND_CROP) {
                ImgLoadHelper.loadCircleImage(this, mOutPutUri, resultIv);
            }

        }
    }


}
