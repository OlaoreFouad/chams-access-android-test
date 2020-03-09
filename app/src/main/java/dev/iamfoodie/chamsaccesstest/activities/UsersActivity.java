package dev.iamfoodie.chamsaccesstest.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.view.View;

import java.util.List;

import dev.iamfoodie.chamsaccesstest.R;
import dev.iamfoodie.chamsaccesstest.adapters.UsersAdapter;
import dev.iamfoodie.chamsaccesstest.databinding.ActivityUsersBinding;
import dev.iamfoodie.chamsaccesstest.models.User;
import dev.iamfoodie.chamsaccesstest.models.UsersResponse;
import dev.iamfoodie.chamsaccesstest.viewmodel.MyViewModelFactory;
import dev.iamfoodie.chamsaccesstest.viewmodel.UsersViewModel;

public class UsersActivity extends AppCompatActivity {

    User user;
    private ActivityUsersBinding binding;
    private UsersViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_users);
        MyViewModelFactory factory = new MyViewModelFactory(getApplication());
        viewModel = ViewModelProviders.of(this, factory).get(UsersViewModel.class);

        Bundle extras = getIntent().getExtras();
        user = (User) extras.getSerializable("currentUser");
        binding.welcomeUserText.setText("Welcome, " + user.getFirst_name() + " " + user.getLast_name());

        binding.progressBar.setVisibility(View.VISIBLE);
        viewModel.getUsers(getApplicationContext()).observe(this, new Observer<UsersResponse>() {
            @Override
            public void onChanged(UsersResponse usersResponse) {
                if (usersResponse != null) {
                    List<User> users = usersResponse.getData();
                    if (users != null){
                        UsersAdapter adapter = new UsersAdapter(users, getApplicationContext());
                        binding.usersList.setHasFixedSize(true);
                        binding.usersList.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                        binding.usersList.setAdapter(adapter);
                        binding.progressBar.setVisibility(View.GONE);

                        viewModel.populateUsers(users);
                    }
                }
            }
        });


    }
}
