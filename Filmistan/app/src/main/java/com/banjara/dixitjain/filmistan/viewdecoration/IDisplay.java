package com.banjara.dixitjain.filmistan.viewdecoration;

import android.content.Intent;
import android.widget.ImageView;

public interface IDisplay {

    void screenTransition(Intent intent);

    void toastDisplay(String message);

    void ontTransition(Intent intent, ImageView imageView, String transitionName);

}
