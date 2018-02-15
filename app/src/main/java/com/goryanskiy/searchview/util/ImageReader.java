package com.goryanskiy.searchview.util;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.goryanskiy.searchview.R;

/**
 *
 * Created by goryanskiy on 13.02.18.
 */

public class ImageReader {

    public static void setCircleImage(Context context, ImageView view, String uri){
        Glide.with(context)
                .load(uri)
                .apply(new RequestOptions()
                        .placeholder(R.drawable.no_photo)
                        .error(R.drawable.no_photo)
                        .circleCrop())
                .into(view);
    }
}