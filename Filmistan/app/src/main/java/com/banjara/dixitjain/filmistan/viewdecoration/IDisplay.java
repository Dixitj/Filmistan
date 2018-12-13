package com.banjara.dixitjain.filmistan.viewdecoration;

import android.content.Intent;

public interface IDisplay {

    void screenTransition(Intent intent);

    void toastDisplay(String message);

}
