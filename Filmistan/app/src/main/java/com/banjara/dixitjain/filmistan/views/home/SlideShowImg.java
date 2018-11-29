package com.banjara.dixitjain.filmistan.views.home;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.banjara.dixitjain.filmistan.R;
import com.banjara.dixitjain.filmistan.model.Result;
import com.squareup.picasso.Picasso;
import java.util.List;

public class SlideShowImg extends PagerAdapter {

   private List<Result> slideImg ;
   private Activity activity;

   public SlideShowImg(){}

   SlideShowImg(List<Result> slideImg, Activity activity){

       this.slideImg = slideImg;
      // this.context = context;
       this.activity = activity;
      // inflater = LayoutInflater.from(context);

   }

   @NonNull
   @Override
   public Object instantiateItem(@NonNull ViewGroup view, int position) {

       final String imageURl = "https://image.tmdb.org/t/p/w342";

       LayoutInflater inflater = activity.getLayoutInflater();
       View imageLayout = inflater.inflate(R.layout.activity_slide_show_img,view,false);
       final ImageView imageView = imageLayout.findViewById(R.id.image);

       Picasso.get()
               .load(imageURl+slideImg.get(position)
               .getPosterPath())
               .resize(800,200)
               .into(imageView);


       view.addView(imageLayout);
       return imageLayout;

   }

    @Override
    public int getCount() {
        return slideImg.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view.equals(o);
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
