package com.zhiw.mooc.utils;

import android.content.Intent;
import android.net.Uri;
import android.os.Environment;

import java.io.File;

/**
 * ClassName: FileUtil
 * Desc:
 * Created by zhiw on 16/4/7.
 */
public class FileUtil {


    public static String getRootPath(){
        return Environment.getExternalStorageDirectory().getPath();
    }

    public static Intent getFileIntent(String path){
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.addCategory(Intent.CATEGORY_DEFAULT);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        Uri uri = Uri.fromFile(new File(path));
        if (path.endsWith(".ppt") || path.endsWith(".pptx")){
            intent.setDataAndType(uri,"application/vnd.ms-powerpoint");
        }else if (path.endsWith(".doc") || path.endsWith(".docx")){
            intent.setDataAndType(uri,"application/msword");
        }
        return intent;

    }
}
