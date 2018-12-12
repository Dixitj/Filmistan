package com.banjara.dixitjain.filmistan.dependencyinjection;

import android.content.Context;

import com.banjara.dixitjain.filmistan.views.signin.Display;
import com.banjara.dixitjain.filmistan.views.signin.IDisplay;
import com.banjara.dixitjain.filmistan.views.signin.ISignUpVm;
import com.banjara.dixitjain.filmistan.views.signin.SignUpVm;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

    private Context context;

    public AppModule(Context context){

        this.context = context;
    }


    @Provides
    Context priovidesContext(){

        return  context;
    }

    @Provides
    public ISignUpVm provideSignUpVm(){

        return new SignUpVm(context);
    }

    @Provides
    public IDisplay provideDisplay (){

        return  new Display(context);
    }
}
