package br.com.aluno.etec.appmovie;

import android.app.Application;
import android.util.Log;

import com.squareup.otto.Bus;

/**
 * Created by Jose on 14/03/2017.
 */

public class FilmeApplication extends Application {

    private static final String TAG = "FilmeApplication";
    private static FilmeApplication mInstance = null;
    private Bus bus = new Bus();

    public static FilmeApplication getmInstance(){
        return mInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate");
        mInstance = this;

    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        Log.d(TAG, "onTerminate");
    }

    public Bus getBus(){
        return bus;
    }
}
