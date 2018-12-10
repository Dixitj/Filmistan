package com.banjara.dixitjain.filmistan.views.signin;

import android.content.Intent;

public interface IDisplay {

    void screenTransition(Intent intent);

    void toastDisplay(String message);
}
