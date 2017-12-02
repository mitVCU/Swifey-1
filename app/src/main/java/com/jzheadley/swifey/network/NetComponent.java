package com.jzheadley.swifey.network;

import com.jzheadley.swifey.base.AppModule;
import com.jzheadley.swifey.ui.MainActivity;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class, NetModule.class})
public interface NetComponent {
    void inject(MainActivity activity);

    // void inject(VideoActivity activity);

}
