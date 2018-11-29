package com.banjara.dixitjain.filmistan.views.signin;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;
import com.banjara.dixitjain.filmistan.views.home.HomeActivity;
import com.google.firebase.auth.FirebaseAuth;

public class SignUpVm {

    private Context context;
    private FirebaseAuth auth;


    SignUpVm(Context context) {

        this.context = context;
        auth = FirebaseAuth.getInstance();
    }


    protected void signInHandler(String email, String password) {

        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener((Activity) context, task -> {
            if (task.isSuccessful()) {

                screenTransition(new Intent(context, HomeActivity.class));

            } else toastDisplay("Failed");
        });
    }

    protected void signUpHandler(String email, String password) {

        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener((Activity) context, task -> {
            if (task.isSuccessful()) {

                screenTransition(new Intent(context, HomeActivity.class));

            } else {

                toastDisplay("Enter all Details Correctly");

            }
        });
    }

    protected void resetPasswordHandler(String email) {

        auth.sendPasswordResetEmail(email).addOnCompleteListener((Activity) context, task -> {

            if (task.isSuccessful()) {

                toastDisplay("Email sent");

            } else

                toastDisplay("Failed");
        });

    }

    protected void screenTransition(Intent intent) {

        /*
        slide.setSlideEdge(Gravity.END);
        slide.setDuration(3000);
        context.startActivity(intent, options.toBundle());*/

        context.startActivity(intent);

    }

    private void toastDisplay(String message) {

        Toast.makeText(context, message, Toast.LENGTH_LONG).show();

    }
}
