package com.banjara.dixitjain.filmistan.views.signin;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import com.banjara.dixitjain.filmistan.R;
import com.banjara.dixitjain.filmistan.databinding.ActivitySignInBinding;
import com.banjara.dixitjain.filmistan.dependencyinjection.AppModule;
import com.banjara.dixitjain.filmistan.dependencyinjection.Appcomponent;
import com.banjara.dixitjain.filmistan.dependencyinjection.DaggerAppcomponent;

import javax.inject.Inject;

public class SignIn extends AppCompatActivity {

    private ActivitySignInBinding signInBinding;
    private Appcomponent appcomponent;

    @Inject
    public ISignUpVm signUpVm;

    @Inject
    public IDisplay display;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        signInBinding = DataBindingUtil.setContentView(this, R.layout.activity_sign_in);
        signInBinding.setLogIn(this);

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

    public void onSignInClick(View view) {


        if (signInBinding.emailAddress.getText().toString().length() != 0 &&
                signInBinding.passwordText.getText().toString().length() != 0)

            signUpVm.signInHandler(signInBinding.emailAddress.getText().toString(),
                                   signInBinding.passwordText.getText().toString());

        else

            display.toastDisplay(getString(R.string.MEmailandPassword));

    }


    public void onResetClick(View view) {


        if (signInBinding.emailAddress.getText().toString().length() != 0)
            signUpVm.resetPasswordHandler(signInBinding.emailAddress.getText().toString());

        else

            display.toastDisplay(getString(R.string.MEmailAddress));

    }


    public void onSignUpClick(View view) {

        display.screenTransition(new Intent(this, SignUp.class));

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
}

