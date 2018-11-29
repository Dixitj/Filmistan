package com.banjara.dixitjain.filmistan.views.signin;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.banjara.dixitjain.filmistan.R;
import com.banjara.dixitjain.filmistan.databinding.ActivitySignInBinding;

public class SignIn extends AppCompatActivity {

    private ActivitySignInBinding signInBinding;
    private SignUpVm signUpVm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        signInBinding = DataBindingUtil.setContentView(this, R.layout.activity_sign_in);
        signInBinding.setLogIn(this);
        signUpVm = new SignUpVm(this);

    }


    public void onSignInClick(View view) {

        String email = signInBinding.emailAddress.getText().toString();
        String password = signInBinding.passwordText.getText().toString();

        if (email.length() != 0 && password.length() != 0)
            signUpVm.signInHandler(email, password);

        else
            toastDiaplay("Please Enter Email address and Passowrd");

    }


    public void onResetClick(View view) {

        String email = signInBinding.emailAddress.getText().toString();

        if (email.length() != 0)
            signUpVm.resetPasswordHandler(email);

        else
            toastDiaplay("Enter Email Address");

    }


    public void onSignUpClick(View view) {


        signUpVm.screenTransition(new Intent(this, SignUp.class));

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    private void toastDiaplay(String message) {

        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

}

