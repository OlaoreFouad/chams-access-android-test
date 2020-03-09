package dev.iamfoodie.chamsaccesstest.viewmodel;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import dev.iamfoodie.chamsaccesstest.models.User;
import dev.iamfoodie.chamsaccesstest.repository.UsersRepository;

public class RegisterViewModel extends ViewModel {

    public UsersRepository repository;

    public RegisterViewModel() {
        this.repository = new UsersRepository();
    }

    public LiveData<User> register(User user, Context ctx) {
        return repository.register(user, ctx);
    }

}
