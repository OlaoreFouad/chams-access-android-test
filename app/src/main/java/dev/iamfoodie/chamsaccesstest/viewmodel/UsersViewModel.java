package dev.iamfoodie.chamsaccesstest.viewmodel;

import android.app.Application;
import android.content.Context;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.Collections;
import java.util.List;

import dev.iamfoodie.chamsaccesstest.data.UsersDao;
import dev.iamfoodie.chamsaccesstest.data.UsersDatabase;
import dev.iamfoodie.chamsaccesstest.models.User;
import dev.iamfoodie.chamsaccesstest.models.UsersResponse;
import dev.iamfoodie.chamsaccesstest.repository.UsersRepository;

public class UsersViewModel extends ViewModel {

    private UsersRepository repository;
    private UsersDatabase db;
    private UsersDao dao;

    public UsersViewModel(Application application) {
        this.repository = new UsersRepository();
        db = UsersDatabase.getInstance(application);
        dao = db.getDao();
    }

    public void populateUsers(List<User> users) {
        Task t = new Task(dao);
//        for(User u: users) {
//            t.execute(u);
//        }
    }

    public LiveData<UsersResponse> getUsers(Context context) {
        return repository.getUsers(context);
    }

    private static class Task extends AsyncTask<User, Void, Void> {

        private UsersDao dao;

        public Task(UsersDao dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(User... lists) {
            for(User user: lists) {
                dao.addUser(user);
            }

            return null;
        }
    }

}
