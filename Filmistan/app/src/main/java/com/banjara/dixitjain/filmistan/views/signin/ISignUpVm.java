package com.banjara.dixitjain.filmistan.views.signin;


public interface ISignUpVm {

    void signInHandler(String email, String password);

    void signUpHandler(String email, String password);

    void resetPasswordHandler(String email);


}
