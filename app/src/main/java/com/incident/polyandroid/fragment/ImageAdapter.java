package com.incident.polyandroid.fragment;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.incident.polyandroid.R;

/**
 * Created by Polytech on 14/05/2018.
 */

public class ImageAdapter extends PagerAdapter {
    private Context context;
    private int[] GalImages = new int[] {
            R.drawable.common_full_open_on_phone, R.drawable.common_google_signin_btn_icon_dark, R.drawable.common_google_signin_btn_icon_dark_normal, R.drawable.common_google_signin_btn_icon_light_normal_background,R.drawable.common_google_signin_btn_text_light_focused,
    };
    public ImageAdapter(Context context)
    {
        this.context=context;
    }

    @Override
    public int getCount() {
        return GalImages.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ImageView imageView = new ImageView(context);
        int padding = context.getResources().getDimensionPixelSize(R.dimen.activity_horizontal_margin);
        imageView.setPadding(padding, padding, padding, padding);
        imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
        imageView.setImageResource(GalImages[position]);
        container.addView(imageView, 0);
        return imageView;
    }
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((ImageView) object);
    }
}

