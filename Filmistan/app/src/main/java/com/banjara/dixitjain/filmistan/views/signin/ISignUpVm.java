package com.banjara.dixitjain.filmistan.views.signin;


import android.content.Intent;

import io.reactivex.disposables.CompositeDisposable;

public interface ISignUpVm {

    void signInHandler(String email, String password);

    void signUpHandler(String email, String password);

    void resetPasswordHandler(String email);

    void signOutHandler(CompositeDisposable disposableObserver, Intent intent);

}
