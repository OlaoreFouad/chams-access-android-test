package dev.iamfoodie.chamsaccesstest.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class MyViewModelFactory implements ViewModelProvider.Factory {

    Application app;

    public MyViewModelFactory(Application app) {
        this.app = app;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(UsersViewModel.class)) {
            return  (T) new UsersViewModel(this.app);
        }

        throw new IllegalStateException("Illegal State reached!");
    }
}
