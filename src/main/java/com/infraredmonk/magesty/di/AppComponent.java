package com.infraredmonk.magesty.di;

import com.infraredmonk.magesty.di.module.DatabaseModule;
import com.infraredmonk.magesty.resources.UserResource;
import dagger.Component;

@Component(modules = {DatabaseModule.class})
public interface AppComponent {
    UserResource getUserResource();
}
