package com.base.utils.imageloader;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.io.File;

public class GImageLoader {
    static int DEFAULT_EMPTY_PHOTO = 0;

    static int DEFAULT_ERROR_PHOTO = 0;

    public static void loadImage(Context context, String url, int erroImg, int emptyImg, ImageView iv) {
        //原生 API
        Glide.with(context).load(url).placeholder(emptyImg).error(erroImg).into(iv);
    }

    public static void loadImage(Context context, String url, ImageView iv) {
        //原生 API
        Glide.with(context).load(url).crossFade().placeholder(DEFAULT_EMPTY_PHOTO).error(DEFAULT_ERROR_PHOTO).into(iv);
    }

    public static void loadGifImage(Context context, String url, ImageView iv) {
        Glide.with(context).load(url).asGif().diskCacheStrategy(DiskCacheStrategy.SOURCE).placeholder
                (DEFAULT_EMPTY_PHOTO).error(DEFAULT_ERROR_PHOTO).into(iv);
    }


//    public static void loadCircleImage(Context context, String url, ImageView iv) {
//        Glide.with(context).load(url).placeholder(DEFAULT_EMPTY_PHOTO).error(DEFAULT_ERROR_PHOTO).transform(new
//                GlideCircleTransform(context)).into(iv);
//    }
//
//    public static void loadRoundCornerImage(Context context, String url, ImageView iv) {
//        Glide.with(context).load(url).placeholder(DEFAULT_EMPTY_PHOTO).error(DEFAULT_ERROR_PHOTO).transform(new
//                GlideRoundTransform(context, 10)).into(iv);
//    }


    public static void loadImage(Context context, final File file, final ImageView imageView) {
        Glide.with(context).load(file).into(imageView);

    }

    public static void loadImage(Context context, final int resourceId, final ImageView imageView) {
        Glide.with(context).load(resourceId).into(imageView);
    }
}