package dev.iamfoodie.chamsaccesstest.viewmodel;

import android.app.Application;
import android.content.Context;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import dev.iamfoodie.chamsaccesstest.models.User;
import dev.iamfoodie.chamsaccesstest.repository.UsersRepository;

public class LoginViewModel extends ViewModel {

    private UsersRepository repository;

    public LoginViewModel() {
        repository = new UsersRepository();
    }

    public LiveData<User> login(String email, String password, Context context) {
        return repository.login("funmiayinde1@gmail.com", "password", context);
    }



}

