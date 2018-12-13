package com.banjara.dixitjain.filmistan.dependencyinjection;


import com.banjara.dixitjain.filmistan.views.signin.SignIn;
import com.banjara.dixitjain.filmistan.views.signin.SignUp;
import com.banjara.dixitjain.filmistan.views.signin.SignUpVm;
import javax.inject.Singleton;
import dagger.Component;


@Component(modules = {AppModule.class})


public interface Appcomponent {

    void inject(SignIn signIn);
    void inject(SignUp signUp);

}
