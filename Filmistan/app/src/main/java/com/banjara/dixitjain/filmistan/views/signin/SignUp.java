package com.banjara.dixitjain.filmistan.views.signin;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import com.banjara.dixitjain.filmistan.R;
import com.banjara.dixitjain.filmistan.databinding.ActivitySignUpBinding;
import com.banjara.dixitjain.filmistan.dependencyinjection.AppModule;
import com.banjara.dixitjain.filmistan.dependencyinjection.Appcomponent;
import com.banjara.dixitjain.filmistan.dependencyinjection.DaggerAppcomponent;
import com.banjara.dixitjain.filmistan.viewdecoration.IDisplay;

import javax.inject.Inject;

public class SignUp extends AppCompatActivity {

    private ActivitySignUpBinding signUpBinding;
    private Appcomponent appcomponent;


    @Inject
    public ISignUpVm signUpVm;

    @Inject
    public IDisplay display;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        signUpBinding = DataBindingUtil.setContentView(this, R.layout.activity_sign_up);
        signUpBinding.setSignUp(this);

        buildComponent().inject(this);

    }


    private Appcomponent buildComponent() {

        if (appcomponent == null) {
            appcomponent = DaggerAppcomponent
                    .builder()
                    .appModule(new AppModule(this))
                    .build();
        }

        return appcomponent;
    }

    public void onSignUpClick(View view){


        if(signUpBinding.emailText.getText().toString().length() != 0 &&
                signUpBinding.passwordText.getText().toString().length() != 0 &&
                signUpBinding.passwordText.getText().toString().matches(signUpBinding.confirmPasswordText.getText().toString()))

            signUpVm.signUpHandler(signUpBinding.emailText.getText().toString(),
                                   signUpBinding.confirmPasswordText.getText().toString());

        else

            display.toastDisplay(getString(R.string.MDetail));

    }


    public void onSignInClick(View view){

        display.screenTransition(new Intent(this, SignIn.class));

    }



}
