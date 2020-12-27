package com.example.test_webview_demo;

import android.nfc.Tag;
import android.os.Environment;
import android.support.v4.app.FragmentActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.test_webview_demo.utils.LogUtil;
import com.tencent.smtt.sdk.TbsReaderView;

import java.io.File;
import java.lang.reflect.Type;

public class FileOpenActivity extends FragmentActivity {

    private TbsReaderView tbsReaderView;
    private boolean result;
    private String url;
    private boolean isOpenFail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_open);
        RelativeLayout root = findViewById(R.id.root_rl);
        tbsReaderView = new TbsReaderView(this,readerCallback);
        root.addView(tbsReaderView, new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT));
        openFile();
    }

    private void openFile() {
         url = Environment.getExternalStorageDirectory().getPath()+"/XueXiPingTai/001.pdf";
        File file = new File(url);
        if (!file.exists()) {
            Toast.makeText(this, "文件不存在", Toast.LENGTH_LONG).show();
        }
        Bundle bundle = new Bundle();
        bundle.putString("filePath", url);
        bundle.putString("tempPath", Environment.getExternalStorageDirectory().getPath());
         result = tbsReaderView.preOpen(parseFormat(parseName(url)), false);
        if (result) {
            tbsReaderView.openFile(bundle);
        }
    }

    private String parseFormat(String fileName) {
        return fileName.substring(fileName.lastIndexOf(".") + 1);
    }

    private String parseName(String url) {
        String fileName = null;
        try {
            fileName = url.substring(url.lastIndexOf("/") + 1);
        } finally {
            if (TextUtils.isEmpty(fileName)) {
                fileName = String.valueOf(System.currentTimeMillis());
            }
        }
        return fileName;
    }

    TbsReaderView.ReaderCallback readerCallback = new TbsReaderView.ReaderCallback() {
        @Override
        public void onCallBackAction(Integer integer, Object o, Object o1) {
            LogUtil.d("xxx","integer "+integer+"  o  "+ o +" o1 "+o1);
            if (o!=null){
                if (30002==(Integer) o){
                    LogUtil.d("xxx","这里可以证明无效了");
                    isOpenFail = true;
                }
            }
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        tbsReaderView.onStop();
        if(isOpenFail){
            File file = new File(url);
            if (file.exists()&&file.isFile()){
                file.delete();
                LogUtil.d("xxx",file.delete()+"");
            }
        }

    }
}
