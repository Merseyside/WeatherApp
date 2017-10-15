package com.merseyside.admin.weatherapp.presentation;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

/**
 * Created by ivan_ on 13.10.2017.
 */

public class ImageManager {

    private static int getIconIdBySummary(Context context, String name) {
        name = name.replace("-", "_");
        Resources resources = context.getResources();
        return resources.getIdentifier(name, "drawable",
                context.getPackageName());
    }

    public static void setIconInImageView(final Context context, ImageView iv, final String name) {
        ViewTreeObserver vto = iv.getViewTreeObserver();
        vto.addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            @Override
            public boolean onPreDraw() {
                float finalHeight = iv.getMeasuredHeight();
                float finalWidth = iv.getMeasuredWidth();
                iv.setImageBitmap(decodeSampledBitmapFromResource(context.getResources(), getIconIdBySummary(context, name), (int)finalWidth, (int)finalHeight));
                iv.getViewTreeObserver().removeOnPreDrawListener(this);
                Log.e("hilength","Height: " + finalHeight + " Width: " + finalWidth);

                iv.getViewTreeObserver().removeOnPreDrawListener(this);
                return true;
            }
        });
    }

    public static Bitmap decodeSampledBitmapFromResource(Resources res, int resId, int reqWidth, int reqHeight){
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(res, resId, options);
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeResource(res, resId, options);
    }

    public static Bitmap decodeSampledBitmapFromData(byte[] res, int width, int height, int lenght) {
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeByteArray(res, 0, lenght, options);
        options.inSampleSize = calculateInSampleSize(options, width, height);

        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeByteArray(res, 0,  lenght, options);
    }

    public static int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight){
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;
        if (height > reqHeight || width > reqWidth) {

            final int halfHeight = height / 2;
            final int halfWidth = width / 2;

            while ((halfHeight / inSampleSize) >= reqHeight
                    && (halfWidth / inSampleSize) >= reqWidth) {
                inSampleSize *= 2;
            }
        }
        return inSampleSize;
    }

    private static int getBackgroundIdBySummary(Context context, String name) {
        name = name.replace("-", "_") + "_background";
        Resources resources = context.getResources();
        return resources.getIdentifier(name, "drawable",
                context.getPackageName());
    }

    public static void setViewBackground(View view, String name, boolean isAnimate) {
        Context context = view.getContext();
        int id = getBackgroundIdBySummary(context, name);
        Point dimensions = WeatherApplication.getDimensions();
        Bitmap bitmap = decodeSampledBitmapFromResource(context.getResources(), id, dimensions.x, dimensions.y);
        setViewBackground(view, bitmap, isAnimate);
    }

    public static void setViewBackground(View view, Bitmap bitmap, boolean isAnimate) {
        view.setBackground(new BitmapDrawable(view.getContext().getResources(), bitmap));
        if (isAnimate) animateView(view);
    }

    public static void animateView(final View v) {
        Context context = v.getContext();
        final Animation anim_in  = AnimationUtils.loadAnimation(context, android.R.anim.fade_in);
        anim_in.setAnimationListener(new Animation.AnimationListener() {
            @Override public void onAnimationStart(Animation animation) {}
            @Override public void onAnimationRepeat(Animation animation) {}
            @Override public void onAnimationEnd(Animation animation) {}
        });
        v.startAnimation(anim_in);
    }

}
