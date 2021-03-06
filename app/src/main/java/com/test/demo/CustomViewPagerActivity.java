package com.test.demo;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.ms.banner.Banner;
import com.ms.banner.BannerConfig;
import com.ms.banner.Transformer;
import com.ms.banner.holder.BannerViewHolder;
import com.ms.banner.holder.HolderCreator;
import com.test.CustomData;
import com.test.R;
import com.test.ui.CustomViewHolder2;
import com.test.ui.CustomViewHolder3;

import java.util.ArrayList;
import java.util.List;

public class CustomViewPagerActivity extends AppCompatActivity {

    Banner banner1;
    Banner banner2;
    Banner banner3;
    LinearLayout indicator;
    private List<ImageView> indicatorImages = new ArrayList<>();
    private int mIndicatorSelectedResId = R.mipmap.indicator;
    private int mIndicatorUnselectedResId = R.mipmap.indicator2;
    private int lastPosition = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_view_pager);

        banner1 = (Banner) findViewById(R.id.banner1);
        banner2 = (Banner) findViewById(R.id.banner2);
        banner3 = (Banner) findViewById(R.id.banner3);
        indicator = (LinearLayout) findViewById(R.id.indicator);
        indicatorImages.clear();

        List<CustomData> list = new ArrayList<>();
        list.add(new CustomData("", "CustomLayout", false));
        list.add(new CustomData("", "Transformer", false));
        list.add(new CustomData("", "Viewpager", false));

        ArrayList<CustomData> arrList = new ArrayList<>();
        CustomData data1 = new CustomData("http://img.zcool.cn/community/01fca557a7f5f90000012e7e9feea8.jpg", "", false);
        CustomData data2 = new CustomData("", "", true);
        CustomData data3 = new CustomData("http://img.zcool.cn/community/01996b57a7f6020000018c1bedef97.jpg", "", false);
        arrList.add(data1);
        arrList.add(data2);
        arrList.add(data3);

        initIndicator();

        banner1.setAutoPlay(true)
                .setOffscreenPageLimit(list.size())
                .setPages(list, new HolderCreator<BannerViewHolder>() {
                    @Override
                    public BannerViewHolder createViewHolder() {
                        return new CustomViewHolder2();
                    }
                })
                .setBannerStyle(BannerConfig.NOT_INDICATOR)
                .setBannerAnimation(Transformer.Scale)
                .start();
        banner1.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                indicatorImages.get((lastPosition + 3) % 3).setImageResource(mIndicatorUnselectedResId);
                indicatorImages.get((position + 3) % 3).setImageResource(mIndicatorSelectedResId);
                lastPosition = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        banner2.setAutoPlay(true)
                .setOffscreenPageLimit(list.size())
                .setPages(arrList, new HolderCreator<BannerViewHolder>() {
                    @Override
                    public BannerViewHolder createViewHolder() {
                        return new CustomViewHolder3();
                    }
                })
                .start();

        banner3.setAutoPlay(true)
                .setOffscreenPageLimit(list.size())
                .setPages(arrList, new HolderCreator<BannerViewHolder>() {
                    @Override
                    public BannerViewHolder createViewHolder() {
                        return new CustomViewHolder3();
                    }
                })
                .start();
    }

    private void initIndicator() {
        for (int i = 0; i < 3; i++) {
            ImageView imageView = new ImageView(this);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            LinearLayout.LayoutParams custom_params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT);
            custom_params.leftMargin = 2;
            custom_params.rightMargin = 2;
            if (i == 0) {
                imageView.setImageResource(mIndicatorSelectedResId);
            } else {
                imageView.setImageResource(mIndicatorUnselectedResId);
            }
            indicatorImages.add(imageView);
            indicator.addView(imageView, custom_params);
        }
    }

    //如果你需要考虑更好的体验，可以这么操作
    @Override
    protected void onStart() {
        super.onStart();
        //开始轮播
        banner1.startAutoPlay();
        banner2.startAutoPlay();
        banner3.startAutoPlay();
    }

    @Override
    protected void onStop() {
        super.onStop();
        //结束轮播
        banner1.stopAutoPlay();
        banner2.stopAutoPlay();
        banner3.stopAutoPlay();
    }
}
