package dev.iamfoodie.chamsaccesstest.retrofit;

import org.json.JSONObject;

import java.util.List;

import dev.iamfoodie.chamsaccesstest.models.LoginCred;
import dev.iamfoodie.chamsaccesstest.models.LoginResponse;
import dev.iamfoodie.chamsaccesstest.models.User;
import dev.iamfoodie.chamsaccesstest.models.UsersResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface UsersService {

    @GET("users")
    @Headers({
            "x-api-key: web"
    })
    Call<UsersResponse> getUsers(@Header("Authorization") String token);

    @POST("login")
    @Headers("x-api-key: web")
    Call<LoginResponse> login(@Body LoginCred cred);

    @POST("register")
    @Headers("x-api-key: web")
    Call<LoginResponse> register(@Body User user);

}
