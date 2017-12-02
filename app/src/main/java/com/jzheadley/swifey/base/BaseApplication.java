package com.jzheadley.swifey.base;

import android.app.Application;
import android.content.Context;

import com.jzheadley.swifey.network.DaggerNetComponent;
import com.jzheadley.swifey.network.NetComponent;
import com.jzheadley.swifey.network.NetModule;


public class BaseApplication extends Application {
    private AppComponent appComponent;
    private NetComponent netComponent;

    public static BaseApplication get(Context context) {
        return (BaseApplication) context.getApplicationContext();
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
    }

    @Override
    public void onCreate() {
        super.onCreate();

        initAppComponent();
        initNetComponent();

    }

    private void initNetComponent() {
        netComponent = DaggerNetComponent.builder()
                .appModule(new AppModule(this))
//                .netModule(new NetModule("http://jzheadley.com:8192/api/"))
                .netModule(new NetModule("http://192.168.0.106:8080/api/"))
                .build();
    }

    private void initAppComponent() {
        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
    }


    public AppComponent getAppComponent() {
        return appComponent;
    }


    public NetComponent getNetComponent() {
        return netComponent;
    }

}
