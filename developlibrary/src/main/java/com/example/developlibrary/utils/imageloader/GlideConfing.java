package com.example.developlibrary.utils.imageloader;

import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.example.developlibrary.utils.UiUtil;

/**
 * 作者：wl on 2017/9/14 11:33
 * 邮箱：wangl@ixinyongjia.com
 */
public class GlideConfing {
    private RequestBuilder<Drawable> requestBuilder;
    private RequestOptions requestOptions;

    public GlideConfing(RequestBuilder<Drawable> builder) {
        requestBuilder = builder;
        requestOptions = new RequestOptions();
    }

    public GlideConfing placeholder(int resourceId) {
        requestOptions.placeholder(resourceId);

        return this;
    }

    public GlideConfing error(int resourceId) {
        requestOptions.error(resourceId);

        return this;
    }


    public GlideConfing skipMemoryCache(boolean noMemoryCach) {
        requestOptions.skipMemoryCache(noMemoryCach);

        return this;
    }

    public GlideConfing skipDiskCache(DiskCacheStrategy valuse) {
        requestOptions.diskCacheStrategy(valuse);

        return this;
    }


    public GlideConfing thumbnail(float sizeMultiplier) {
        requestBuilder.thumbnail(sizeMultiplier);

        return this;
    }

    public GlideConfing setCircleTransform() {
        requestOptions.transform(new GlideCircleTransform(UiUtil.getContext()));
        return this;
    }


    public void into(ImageView imageView) {
        requestBuilder.apply(requestOptions);
        requestBuilder.into(imageView);
    }

    public void into(ImageView imageView, RequestOptions options) {
        requestBuilder.apply(options);
        requestBuilder.into(imageView);
    }

    public void into(Target target) {
        requestBuilder.apply(requestOptions);
        requestBuilder.into(target);
    }


}
