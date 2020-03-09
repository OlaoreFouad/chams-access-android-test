package dev.iamfoodie.chamsaccesstest.repository;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import dev.iamfoodie.chamsaccesstest.models.LoginCred;
import dev.iamfoodie.chamsaccesstest.models.LoginResponse;
import dev.iamfoodie.chamsaccesstest.models.User;
import dev.iamfoodie.chamsaccesstest.models.UsersResponse;
import dev.iamfoodie.chamsaccesstest.retrofit.UsersService;
import dev.iamfoodie.chamsaccesstest.utils.Prefs;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UsersRepository {

    public static UsersService RETROFIT_INSTANCE;

    public UsersRepository() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.1.201:4000/api/v1/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RETROFIT_INSTANCE = retrofit.create(UsersService.class);
    }

    public LiveData<User> register(User user, Context context) {
        Call<LoginResponse> registerObject = RETROFIT_INSTANCE.register(user);
        MutableLiveData<User> registerLiveData = new MutableLiveData<>();

        registerObject.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                System.out.println("Status Code: " + response.code());

                LoginResponse res = response.body();
                String token = res.get_meta().getToken();
                Prefs prefs = new Prefs(context);
                prefs.setToken(token);
                User currentUser = res.getData();
                registerLiveData.setValue(currentUser);
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Log.d("TAG", "Error: " + t.getMessage());
            }
        });

        return registerLiveData;
    }

    public LiveData<User> login(String email, String password, Context context) {
        LoginCred loginCred = new LoginCred(email, password);
        Call<LoginResponse> loginObject = RETROFIT_INSTANCE.login(loginCred);

        System.out.println("Email: " + loginCred.getEmail() + " Password: " + loginCred.getPassword());
        MutableLiveData<User> userLiveData = new MutableLiveData<>();

        loginObject.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {

                System.out.println("Status Code: " + response.code());

                LoginResponse res = response.body();
                String token = res.get_meta().getToken();
                Prefs prefs = new Prefs(context);
                prefs.setToken(token);
                User currentUser = res.getData();
                userLiveData.setValue(currentUser);

            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Log.d("TAG", "Error: " + t.getMessage());
            }
        });

        return userLiveData;
    }

    public LiveData<UsersResponse> getUsers(Context ctx) {
        MutableLiveData usersLiveData = new MutableLiveData();
        Call<UsersResponse> usersResponseCall = RETROFIT_INSTANCE.getUsers(new Prefs(ctx).getToken());

        usersResponseCall.enqueue(new Callback<UsersResponse>() {
            @Override
            public void onResponse(Call<UsersResponse> call, Response<UsersResponse> response) {
                System.out.println("Response Code: " + response.code());

                UsersResponse usersResponse = response.body();
                usersLiveData.setValue(usersResponse);

            }

            @Override
            public void onFailure(Call<UsersResponse> call, Throwable t) {

            }
        });

        return usersLiveData;
    }

}
