package com.example.orderfood.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;
import com.example.orderfood.Model.ImageSlider;
import com.example.orderfood.R;

import java.util.List;

public class HomeBanner extends PagerAdapter {
    // Xử lý sider image
    private Context homeContext;
    private List<ImageSlider> homeListImage; // đối tượng Image được lấy từ Package Model

    public HomeBanner(Context homeContext, List<ImageSlider> homeListImage) {
        this.homeContext = homeContext;
        this.homeListImage = homeListImage;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view = LayoutInflater.from(container.getContext()).inflate(R.layout.image_slider, container, false);

        ImageView imgPhoto = view.findViewById(R.id.img_sliderImg);

         ImageSlider imageSlider = homeListImage.get(position);
        if(imageSlider != null){
            Glide.with(homeContext).load(imageSlider.getResourceID()).into(imgPhoto);
            //Xét ảnh cho imageView
        }

        //Add vào viewGroup
        container.addView(view);

        return view;
    }

    @Override
    public int getCount() {
        if(homeListImage != null){
            return homeListImage.size();
        }
        return 0;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        //Xoá view từ view Group
        container.removeView((View)object) ;
    }
}
