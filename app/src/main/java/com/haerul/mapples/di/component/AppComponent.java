package com.haerul.mapples.di.component;

import com.haerul.mapples.App;
import com.haerul.mapples.di.builder.ActivityBuilder;
import com.haerul.mapples.di.module.AppDatabaseModule;
import com.haerul.mapples.di.module.AppModule;

import javax.inject.Singleton;
import dagger.BindsInstance;
import dagger.Component;
import dagger.android.support.AndroidSupportInjectionModule;

@Singleton
@Component(modules = {AndroidSupportInjectionModule.class, AppModule.class, AppDatabaseModule.class, ActivityBuilder.class})
public interface AppComponent {
    
    void inject(App app);
    
    @Component.Builder interface Builder {
        @BindsInstance
        Builder application(App app);
        AppComponent build();
    }
    
    
}
