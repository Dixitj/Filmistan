package com.banjara.dixitjain.filmistan.views.signin;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.banjara.dixitjain.filmistan.R;
import com.banjara.dixitjain.filmistan.databinding.ActivitySignUpBinding;

public class SignUp extends AppCompatActivity {

    private ActivitySignUpBinding signUpBinding;
    private SignUpVm signUpVm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        signUpBinding = DataBindingUtil.setContentView(this, R.layout.activity_sign_up);
        signUpBinding.setSignUp(this);

        signUpVm = new SignUpVm(this);

    }


    public void onSignUpClick(View view){

        String email = signUpBinding.emailText.getText().toString();
        String password = signUpBinding.passwordText.getText().toString();
        String confPass = signUpBinding.confirmPasswordText.getText().toString();

        if(email.length() != 0 && password.length() != 0 && password.matches(confPass))
            signUpVm.signUpHandler(email,password);
        else
            Toast.makeText(this,"Enter Detials correctly",Toast.LENGTH_LONG).show();

    }


    public void onSignInClick(View view){

        signUpVm.screenTransition(new Intent(this, SignIn.class));
    }



}
