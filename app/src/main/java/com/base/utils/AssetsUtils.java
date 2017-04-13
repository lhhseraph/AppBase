package com.base.utils;

import android.content.Context;
import android.content.res.AssetManager;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class AssetsUtils {

    private Context context;
    private String databaseName;

    public AssetsUtils(Context context, String databaseName) {
        this.context = context;
        this.databaseName = databaseName;
    }

    /**
     * 获取asset文件下的资源文件信息
     *
     * @param fileName
     * @return
     */
    public static String getFromAssets(Context context, String fileName) {

        try {
            InputStreamReader inputReader = new InputStreamReader(context.getAssets().open(fileName));
            BufferedReader bufReader = new BufferedReader(inputReader);
            String line = "";
            String Result = "";
            while ((line = bufReader.readLine()) != null) {
                Result += line;
            }
            return Result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 把Assets里的文件拷贝到sd卡上
     *
     * @param assetManager    AssetManager
     * @param fileName        Asset文件名
     * @param destinationPath 完整目标路径
     * @return 拷贝成功
     */
    public static boolean copyToSDCard(AssetManager assetManager, String fileName, String destinationPath) {
        try {
            InputStream is = assetManager.open(fileName);
            FileOutputStream os = new FileOutputStream(destinationPath);

            if (is != null && os != null) {
                byte[] data = new byte[1024];
                int len;
                while ((len = is.read(data)) > 0) {
                    os.write(data, 0, len);
                }
                os.close();
                is.close();
            }
        } catch (IOException e) {
            return false;
        }
        return true;
    }
}
