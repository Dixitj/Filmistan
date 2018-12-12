package com.banjara.dixitjain.filmistan.views.signin;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import com.banjara.dixitjain.filmistan.views.home.HomeActivity;
import com.google.firebase.auth.FirebaseAuth;

public class SignUpVm implements ISignUpVm{

    // IDisplay
    private Context context;
    private FirebaseAuth auth;
    private IDisplay display;


    public SignUpVm(Context context) {

        this.context = context;
        auth = FirebaseAuth.getInstance();
        display = new Display(context);
    }


    public void signInHandler(String email, String password) {

        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener((Activity) context, task -> {
            if (task.isSuccessful()) {

                display.screenTransition(new Intent(context, HomeActivity.class));

            } else

                display.toastDisplay("Failed");
        });
    }

    public void signUpHandler(String email, String password) {

        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener((Activity) context, task -> {
            if (task.isSuccessful()) {

                display.screenTransition(new Intent(context, HomeActivity.class));

            } else {

                display.toastDisplay("Enter all Details Correctly");

            }
        });
    }

    public void resetPasswordHandler(String email) {

        auth.sendPasswordResetEmail(email).addOnCompleteListener((Activity) context, task -> {

            if (task.isSuccessful()) {

                display.toastDisplay("Email sent");

            } else

                display.toastDisplay("Failed");

        });

    }
    }
