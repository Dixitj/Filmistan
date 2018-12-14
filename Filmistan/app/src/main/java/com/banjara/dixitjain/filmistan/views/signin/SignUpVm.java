package com.banjara.dixitjain.filmistan.views.signin;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import com.banjara.dixitjain.filmistan.viewdecoration.Display;
import com.banjara.dixitjain.filmistan.viewdecoration.IDisplay;
import com.banjara.dixitjain.filmistan.views.home.HomeActivity;
import com.google.firebase.auth.FirebaseAuth;

import io.reactivex.disposables.CompositeDisposable;

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

        auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener((Activity) context, task -> {
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

    @Override
    public void signOutHandler(CompositeDisposable disposableObserver, Intent intent) {

        AlertDialog.Builder aletBox = new AlertDialog.Builder(context);
        IDisplay display1 = new Display(context);

        aletBox.setMessage("Are you sure,\n" + "You wanted to SignOut?").setPositiveButton("Yes", (dialog, which) -> {

            display1.toastDisplay("Successfully Logged Out!");
            //Toast.makeText(context,"Successfully Logged Out!", Toast.LENGTH_LONG).show();
            FirebaseAuth.getInstance().signOut();
            disposableObserver.clear();
           // context.
            display1.screenTransition(intent);
            //HomeActivity.this.finish();

        }).setNegativeButton("No", (dialog, which) -> dialog.cancel());

        AlertDialog display = aletBox.create();
        display.show();
    }


}
