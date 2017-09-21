package com.example.developlibrary.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.StateListDrawable;
import android.media.ExifInterface;
import android.os.Build;
import android.util.Base64;

import com.orhanobut.logger.Logger;

import java.io.ByteArrayOutputStream;
import java.io.IOException;


/**
 * 作者：wl on 2017/9/13 16:09
 * 邮箱：wangl@ixinyongjia.com
 */
public class BaseBitmapUtil {
    /**
     * 创建一个图片
     *
     * @param contentColor 内部填充颜色
     * @param strokeColor  描边颜色
     * @param radius       圆角
     * @param strokeWidth  描边的宽度
     */
    public static GradientDrawable createDrawable(int contentColor, int strokeColor, int radius, int strokeWidth) {
        GradientDrawable drawable = new GradientDrawable(); // 生成Shape
        drawable.setGradientType(GradientDrawable.LINEAR_GRADIENT); // 设置矩形
        drawable.setColor(contentColor);// 内容区域的颜色
        drawable.setStroke(strokeWidth, strokeColor); // 四周描边,描边后四角真正为圆角，不会出现黑色阴影
        drawable.setCornerRadius(radius); // 设置四角都为圆角
        return drawable;
    }

    /**
     * @param contentColor 图片的颜色
     * @param radius       圆角角度
     * @return
     */
    public static GradientDrawable createDrawable(int contentColor, int radius) {
        return createDrawable(contentColor, contentColor, radius, 1);
    }

    /**
     * 创建一个图片选择器
     *
     * @param normalState  普通状态的图片
     * @param pressedState 按压状态的图片
     */
    public static StateListDrawable createSelector(Drawable normalState, Drawable pressedState) {
        StateListDrawable bg = new StateListDrawable();
        bg.addState(new int[]{android.R.attr.state_pressed, android.R.attr.state_enabled}, pressedState);
        bg.addState(new int[]{android.R.attr.state_enabled}, normalState);
        bg.addState(new int[]{}, normalState);
        return bg;
    }


    public static Bitmap getSmallBitmap(String filePath) {
        return getSmallBitmap(filePath, 720);
    }

    /**
     * 根据路径获得图片并压缩，返回bitmap用于显示
     */
    public static Bitmap getSmallBitmap(String filePath, int reqWidth) {
        //看图片是否有旋转
        Bitmap bitmap = null;

        int degree = readPictureDegree(filePath);

        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(filePath, options);
        int hight = reqWidth * options.outHeight / options.outWidth;
        options.inSampleSize = calculateInSampleSize(options, reqWidth, hight);
        options.inPreferredConfig = Bitmap.Config.RGB_565;

        options.inJustDecodeBounds = false;
        bitmap = BitmapFactory.decodeFile(filePath, options);

        if (degree != 0) {
            bitmap = rotaingImageView(degree, bitmap);

            Logger.d("图片旋转了%d度", degree);
        }

        return bitmap;
    }


    /**
     * 根据路径获得图片并压缩，返回bitmap用于显示
     */
    public static Bitmap zoomBitmapFromBitmap(Bitmap oldBitmap, float rate) {

        // 取得想要缩放的matrix参数
        Matrix matrix = new Matrix();
        matrix.postScale(rate, rate);
        // 得到新的图片
        Bitmap newbm = Bitmap.createBitmap(oldBitmap, 0, 0, oldBitmap.getWidth(), oldBitmap.getHeight(), matrix,
                true);
        return newbm;
    }


    /**
     * 获取Drawable的大小
     *
     * @param drawable
     * @return int 单位 byte
     */
    public static int getDrawableSize(Drawable drawable) {
        if (drawable == null) {
            return 0;
        }
        Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();
        return getBitmapSize(bitmap);
    }

    /**
     * 获取Bitmap图片的大小
     *
     * @param bitmap
     * @return int 单位 byte
     */
    public static int getBitmapSize(Bitmap bitmap) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            return bitmap.getAllocationByteCount();
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
            return bitmap.getByteCount();
        } else {
            return bitmap.getRowBytes() * bitmap.getHeight();
        }
    }

    /**
     * 图片压缩，质量压缩法
     *
     * @param bitmap  //原图
     * @param maxSize //压缩后能够达到的最大尺寸, 单位KB
     */
    public static Bitmap compressBmpToBytes(Bitmap bitmap, int maxSize) {

        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        int options = 100;
        bitmap.compress(Bitmap.CompressFormat.JPEG, options, baos);
        Logger.i("baos.toByteArray().length----%dKB", baos.toByteArray().length / 1024);
        while (baos.toByteArray().length / 1024 > maxSize) {
            baos.reset();
            options -= 5;
            bitmap.compress(Bitmap.CompressFormat.JPEG, options, baos);
            Logger.i("baos.toByteArray().length----%dKB", baos.toByteArray().length / 1024);
        }

        byte[] bytes = baos.toByteArray();
        Bitmap compressBitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
        BaseFileUtil.close(baos);
        return compressBitmap;
    }


    /**
     * @param reqWidth  压缩后的宽
     * @param reqHeight 压缩后的高
     * @return 图片的压缩比
     */
    public static int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {
            final int heightRatio = Math.round((float) height / (float) reqHeight);
            final int widthRatio = Math.round((float) width / (float) reqWidth);
            inSampleSize = heightRatio < widthRatio ? widthRatio : heightRatio;
        }

        Logger.i("width = %d, height = %d, reqWidth = %d, reqHeight = %d, inSampleSize = %d", width, height, reqWidth, reqHeight, inSampleSize);
        return inSampleSize;
    }


    /**
     * 图片转成string
     *
     * @param bitmap
     * @return
     */
    public static String bitmapToString(Bitmap bitmap) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
        byte[] appicon = baos.toByteArray();// 转为byte数组
        return Base64.encodeToString(appicon, Base64.DEFAULT);
    }

    /**
     * 将字符串转换成Bitmap类型
     */
    public static Bitmap stringtoBitmap(String string) {
        Bitmap bitmap = null;
        try {
            byte[] bitmapArray;
            bitmapArray = Base64.decode(string, Base64.DEFAULT);

            bitmap = BitmapFactory.decodeByteArray(bitmapArray, 0, bitmapArray.length);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bitmap;
    }


    /**
     * 获取图片旋转的角度
     */
    public static int readPictureDegree(String path) {
        int degree = 0;
        try {
            ExifInterface exifInterface = new ExifInterface(path);
            int orientation = exifInterface.getAttributeInt(
                    ExifInterface.TAG_ORIENTATION,
                    ExifInterface.ORIENTATION_NORMAL);
            switch (orientation) {
                case ExifInterface.ORIENTATION_ROTATE_90:
                    degree = 90;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_180:
                    degree = 180;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_270:
                    degree = 270;
                    break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return degree;

    }


    /**
     * 图片旋转
     */
    public static Bitmap rotaingImageView(int angle, Bitmap bitmap) {
        if (bitmap != null) {
            Matrix m = new Matrix();
            m.postRotate(angle);
            bitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), m, true);
            return bitmap;
        }
        return bitmap;
    }


    /*
    * bitmapToDrawble
    * */
    public static Drawable bitmapToDrawble(Bitmap bitmap) {
        Drawable drawable = new BitmapDrawable(UiUtil.getContext().getResources(), bitmap);
        return drawable;
    }

    public static Bitmap getLocalBitmap(String url) {
        try {

            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inSampleSize = 2;
            Bitmap bm = BitmapFactory.decodeFile(url, options);
            return bm;  ///把流转化为Bitmap图片

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
